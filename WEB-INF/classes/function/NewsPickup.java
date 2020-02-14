package function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import bean.NewsBean;

public class NewsPickup{
    private static Map url = new HashMap();
    private static Map title = new HashMap();
    static{
        //��v��RSS
        url.put("1","https://news.yahoo.co.jp/pickup/rss.xml");
        //������RSS
        url.put("2","https://news.yahoo.co.jp/pickup/domestic/rss.xml");
        //���ۂ�RSS
        url.put("3","https://news.yahoo.co.jp/pickup/world/rss.xml");
        //�o�ς�RSS
        url.put("4","https://news.yahoo.co.jp/pickup/economy/rss.xml");
        //�G���^����RSS
        url.put("5","https://news.yahoo.co.jp/pickup/entertainment/rss.xml");
        //�X�|�[�c��RSS
        url.put("6","https://news.yahoo.co.jp/pickup/sports/rss.xml");
        //IT��RSS
        url.put("7","https://news.yahoo.co.jp/pickup/computer/rss.xml");
        //�Ȋw��RSS
        url.put("8","https://news.yahoo.co.jp/pickup/science/rss.xml");
        //�n���RSS
        url.put("9","https://news.yahoo.co.jp/pickup/local/rss.xml");
        //��v��RSS
        title.put("1","��v");
        //������RSS
        title.put("2","����");
        //���ۂ�RSS
        title.put("3","����");
        //�o�ς�RSS
        title.put("4","�o��");
        //�G���^����RSS
        title.put("5","�G���^��");
        //�X�|�[�c��RSS
        title.put("6","�X�|�[�c");
        //IT��RSS
        title.put("7","IT");
        //�Ȋw��RSS
        title.put("8","�Ȋw");
        //�n���RSS
        title.put("9","�n��");
    }
    public static String getTitle(String count){
        return (String)title.get(count);
    }
    public static ArrayList getNews(String count){
        ArrayList list = new ArrayList();
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse((String)url.get(count));

            Element root = document.getDocumentElement();

            NodeList channel = root.getElementsByTagName("channel");

            NodeList title = ((Element)channel.item(0)).getElementsByTagName("title");

            System.out.println("�^�C�g���F" + title.item(0).getFirstChild().getNodeValue());

            NodeList item_list = root.getElementsByTagName("item");

            for(int i = 0; i < item_list.getLength(); i++) {

                Element element = (Element)item_list.item(i);

                NodeList item_title = element.getElementsByTagName("title");
                NodeList item_description = element.getElementsByTagName("link");
                NodeList item_time = element.getElementsByTagName("pubDate");

                NewsBean nb = new NewsBean();
                nb.setTitle(item_title.item(0).getFirstChild().getNodeValue());
                nb.setLink(item_description.item(0).getFirstChild().getNodeValue());
                nb.setTime(item_time.item(0).getFirstChild().getNodeValue());
                list.add(nb);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}