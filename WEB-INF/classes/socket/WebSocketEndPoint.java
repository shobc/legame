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
    private String user_id;
    private static final String TEN =",";
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        sessionMap.put(session.getId(),session);
        session.setMaxTextMessageBufferSize(1000000);
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        receiver_chat_id = (String)httpSession.getAttribute("receiver_chat_id");
        sender_chat_id = (String)httpSession.getAttribute("sender_chat_id");
        UserBean ub = (UserBean)httpSession.getAttribute("ub");
        user_id = ub.getUser_id();
        ChatFactory.load(sender_chat_id,session.getId());
        System.out.println("sender_chat_id="+sender_chat_id);
        System.out.println("session.getId()="+session.getId());
        System.out.println("user_id="+user_id);
        System.out.println("Open Connection ...");
    }

    @OnClose
    public void onClose(Session session){
        sessionMap.remove(session.getId());
        ChatFactory.load(sender_chat_id,"null");
        httpSession.removeAttribute("sender_chat_id");
        httpSession.removeAttribute("receiver_chat_id");
        System.out.println("Close Connection ...");
    }

    @OnMessage
    public void onMessage(String message) throws IOException{
        TalkBean tb = new TalkBean();
        ChatBean cb = new ChatBean();
        UserBean ub = (UserBean)httpSession.getAttribute("ub");
        tb.setChat_id(sender_chat_id);
        tb.setUser_id(ub.getUser_id());
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
        if(receiver_chat_id==null){
            receiver_chat_id = Cdao.getReceiverChatId(sender_chat_id);
        }
        id = ChatFactory.read(receiver_chat_id);

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
        String read = null;
        tb.setContent(message);
        if(id==null||id.equals("null")){
            System.out.println("データベースに入れるだけ");
            if(message.length()>=2000){
                String str = message.substring(message.indexOf(TEN) + TEN.length());
                String extension = message.substring(message.indexOf("/")+1,message.indexOf(";"));
                System.out.println(str.length());
                System.out.println("base64");
                Base64Decode bd = new Base64Decode();
                tb.setContent(bd.getFilePath(str,extension));
                dao.addTalkPicture(tb);
            }else{
                dao.addTalk(tb);
            }
        }else{
            Session session = sessionMap.get(id);
            dao.addRead_flag(sender_chat_id,user_id);
            if(message.length()>=2000){
                String str = message.substring(message.indexOf(TEN) + TEN.length());
                String extension = message.substring(message.indexOf("/")+1,message.indexOf(";"));
                System.out.println(str.length());
                System.out.println("base64");
                Base64Decode bd = new Base64Decode();
                tb.setContent(bd.getFilePath(str,extension));
                dao.addTalkPicture(tb);
                if(block_flag.equals("0")){
                    session.getBasicRemote().sendText(message);
                }
            }else{
                dao.addTalk(tb);

                if(block_flag.equals("0")){
                    session.getBasicRemote().sendText(message);
                }
            }
            read = "既読";
        }
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();
    }

    @OnError
    public void onError(Throwable e){
        e.printStackTrace();
    }

}