package socket;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import function.ChatFactory;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.TalkDao;
import dao.ChatDao;
import dao.ProfileDao;
import bean.TalkBean;
import bean.UserBean;
import bean.ChatBean;

@ServerEndpoint(value="/websocketendpoint",
        configurator = socket.GetHttpSessionConfigurator.class)
public class WebSocketEndPoint {
    private static Map<String,Session> sessionMap = new HashMap<String,Session>();
    private HttpSession httpSession;
    private String sender_chat_id;
    private String receiver_chat_id;
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        sessionMap.put(session.getId(),session);
        session.setMaxTextMessageBufferSize(200000);
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        receiver_chat_id = (String)httpSession.getAttribute("receiver_chat_id");
        sender_chat_id = (String)httpSession.getAttribute("sender_chat_id");
        ChatFactory.load(sender_chat_id,session.getId());
        System.out.println("sender_chat_id="+sender_chat_id);
        System.out.println("session.getId()="+session.getId());
        System.out.println("Open Connection ...");
    }

    @OnClose
    public void onClose(Session session){
        sessionMap.remove(session.getId());
        ChatFactory.load(sender_chat_id,"null");
        httpSession.removeAttribute("sender_chat_id");
        httpSession.removeAttribute("receiver_chat_id");
        httpSession.removeAttribute("read");
        System.out.println("httpSession.getAttribute(sender_chat_id)"+httpSession.getAttribute("sender_chat_id"));
        System.out.println("httpSession.getAttribute(receiver_chat_id)"+httpSession.getAttribute("receiver_chat_id"));
        System.out.println("Close Connection ...");

    }

    @OnMessage
    public void onMessage(String message) throws IOException{
        TalkBean tb = new TalkBean();
        ChatBean cb = new ChatBean();
        UserBean ub = (UserBean)httpSession.getAttribute("ub");
        tb.setChat_id(sender_chat_id);
        tb.setUser_id(ub.getUser_id());
        tb.setContent(message);
        cb.setChat_id(sender_chat_id);
        cb.setUser_id(ub.getUser_id());
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TalkDao dao = factory.getOraTalkDao();
        ChatDao Cdao = factory.getOraChatDao();
        ProfileDao Pdao = factory.getOraProfileDao();
        String id = null;
        if(Cdao.getJudge(cb)){
            Cdao.addChat(cb);
            receiver_chat_id = Cdao.getReceiverChatId(sender_chat_id);
        }
        System.out.println("receiver_chat_id"+receiver_chat_id);
        if(receiver_chat_id!=null){
//            receiver_chat_id = Cdao.getReceiverChatId(sender_chat_id);
            id = ChatFactory.read(receiver_chat_id);
        }
        if(Cdao.deleteJudge(cb)){
            Cdao.updateDeleteFlag(cb);
        }
        cb.setFriend_id(ub.getUser_id());
        if(Cdao.blockJudge(cb)){
            if(Cdao.deleteJudge(cb)){
                Cdao.updateDeleteFlag(cb);
            }
        }
        String block_flag ="0";
        if(dao.blockJudge(sender_chat_id)){
            block_flag = "1";
        }
        tb.setBlock_flag(block_flag);
        if(id==null||id.equals("null")){
            System.out.println("データベースに入れるだけ");
            System.out.println("httpSession.getAttribute(read)="+httpSession.getAttribute("read"));
            httpSession.removeAttribute("read");
            System.out.println("httpSession.getAttribute(read)"+httpSession.getAttribute("read"));
            id = dao.addTalk(tb);
        }else{
            Session session = sessionMap.get(id);
            id = dao.addTalk(tb);
            if(block_flag.equals("0")){
                httpSession.setAttribute("read","既読");
                session.getBasicRemote().sendText(message);
            }
            System.out.println("httpSession.getAttribute(read)"+httpSession.getAttribute("read"));
        }
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();
    }

    @OnError
    public void onError(Throwable e){
        e.printStackTrace();
    }

}