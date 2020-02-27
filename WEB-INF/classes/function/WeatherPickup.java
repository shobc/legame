package function;

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

import bean.WeatherBean;

public class WeatherPickup{
    private static Map weather_icon = new HashMap();
    private static Map weather_rss = new HashMap();
    private static Map weather_location = new HashMap();
    static{
        weather_location.put("1","�����s");
        weather_location.put("2","�_�ސ쌧");
        weather_location.put("3","��ʌ�");
        weather_location.put("4","��t��");
        weather_location.put("5","��錧");
        weather_location.put("6","�Ȗ،�");
        weather_location.put("7","�Q�n��");
        weather_location.put("8","�R����");

        weather_icon.put("����","/100_day.png");
        weather_icon.put("�����X��","/101_day.png");
        weather_icon.put("���ꎞ�J","/102_day.png");
        weather_icon.put("�����X�J","/103_day.png");
        weather_icon.put("���ꎞ��","/104_day.png");
        weather_icon.put("�����X��","/105_day.png");
        weather_icon.put("���̂���","/111_day.png");
        weather_icon.put("���̂��J","/114_day.png");
        weather_icon.put("���̂���","/117_day.png");
        weather_icon.put("���̂��J�i���𔺂��j","/119_day.png");
        weather_icon.put("�����X�J�i���𔺂��j","/140_day.png");
        weather_icon.put("���̂���i���𔺂��j","/149_day.png");
        weather_icon.put("�����X��i���𔺂��j","/150_day.png");
        weather_icon.put("�����X�܁i�J�̉\������j","/156_day.png");
        weather_icon.put("�����X�܂�i���𔺂��j","/158_day.png");
        weather_icon.put("���̂��܁i�J�̉\������j","/166_day.png");
        weather_icon.put("���̂��܂�i���𔺂��j","/168_day.png");
        weather_icon.put("�܂�","/200_day.png");
        weather_icon.put("�܎��X��","/201_day.png");
        weather_icon.put("�܂̂���","/201_day.png");
        weather_icon.put("�܈ꎞ�J","/202_day.png");
        weather_icon.put("�܎��X�J","/203_day.png");
        weather_icon.put("�܈ꎞ��","/204_day.png");
        weather_icon.put("�܎��X��","/205_day.png");
        weather_icon.put("�܁i�J�͍~��Ȃ��j�̂���","/211_day.png");
        weather_icon.put("�܂̂��J","/214_day.png");
        weather_icon.put("�܂̂���","/217_day.png");
        weather_icon.put("�܂̂��J�i���𔺂��j","/219_day.png");
        weather_icon.put("�܎��X�J�i���𔺂��j","/240_day.png");
        weather_icon.put("�܂̂���i���𔺂��j","/249_day.png");
        weather_icon.put("�܎��X��i���𔺂��j","/250_day.png");
        weather_icon.put("�܂�i�J�̉\������j","/255_day.png");
        weather_icon.put("�܁i�J�̉\������j���X��","/256_day.png");
        weather_icon.put("�܂�i���𔺂��j","/257_day.png");
        weather_icon.put("�܁i���𔺂��j���X��","/258_day.png");
        weather_icon.put("�܁i�J�̉\������j�̂���","/266_day.png");
        weather_icon.put("�܁i���𔺂��j�̂���","/268_day.png");
        weather_icon.put("�J","/300_day.png");
        weather_icon.put("�J���X��","/301_day.png");
        weather_icon.put("�J���X��","/302_day.png");
        weather_icon.put("�J�̂���","/311_day.png");
        weather_icon.put("�J�̂���","/313_day.png");
        weather_icon.put("�J�̂���","/315_day.png");
        weather_icon.put("�J���X��","/330_day.png");
        weather_icon.put("�J�i���𔺂��j�̂���","/341_day.png");
        weather_icon.put("�J�i���𔺂��j�̂���","/343_day.png");
        weather_icon.put("�J�i���𔺂��j�̂���","/345_day.png");
        weather_icon.put("�J�i���𔺂��j","/350_day.png");
        weather_icon.put("��","/400_day.png");
        weather_icon.put("�᎞�X��","/401_day.png");
        weather_icon.put("�᎞�X��","/402_day.png");
        weather_icon.put("��̂���","/411_day.png");
        weather_icon.put("��̂���","/413_day.png");
        weather_icon.put("��̂��J","/414_day.png");
        weather_icon.put("��i���𔺂��j�̂���","/441_day.png");
        weather_icon.put("��i���𔺂��j�̂���","/443_day.png");
        weather_icon.put("��i���𔺂��j�̂��J","/444_day.png");
        weather_icon.put("��i���𔺂��j","/450_day.png");
        weather_icon.put("��J","/500_day.png");
        weather_icon.put("��J�i���𔺂��j","/550_day.png");
        weather_icon.put("���","/600_day.png");
        weather_icon.put("���i���𔺂��j","/650_day.png");
        weather_icon.put("�\���J","/700_day.png");
        weather_icon.put("�\���J�i���𔺂��j","/750_day.png");
        weather_icon.put("�\����","/800_day.png");
        weather_icon.put("�\����i���𔺂��j","/850_day.png");

        //�����s
        weather_rss.put("1","https://rss-weather.yahoo.co.jp/rss/days/13.xml");
        //�_�ސ쌧
        weather_rss.put("2","https://rss-weather.yahoo.co.jp/rss/days/14.xml");
        //��ʌ�
        weather_rss.put("3","https://rss-weather.yahoo.co.jp/rss/days/11.xml");
        //��t��
        weather_rss.put("4","https://rss-weather.yahoo.co.jp/rss/days/12.xml");
        //��錧
        weather_rss.put("5","https://rss-weather.yahoo.co.jp/rss/days/8.xml");
        //�Ȗ،�
        weather_rss.put("6","https://rss-weather.yahoo.co.jp/rss/days/9.xml");
        //�Q�n��
        weather_rss.put("7","https://rss-weather.yahoo.co.jp/rss/days/10.xml");
        //�R����
        weather_rss.put("8","https://rss-weather.yahoo.co.jp/rss/days/19.xml");
    }
    public static WeatherBean getWeather(String count) {
        WeatherBean wb = new WeatherBean();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse((String)weather_rss.get(count));
            Element root = document.getDocumentElement();
            NodeList item_list = root.getElementsByTagName("item");
            Element element = (Element)item_list.item(0);
            NodeList item_time = element.getElementsByTagName("description");
            String weather_date = item_time.item(0).getFirstChild().getNodeValue();
            wb.setWeather(weather_date);
            String weather = weather_date.substring(0,weather_date.indexOf(" "));
            String weather_image = (String)weather_icon.get(weather);
            wb.setImage_path(weather_image);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    public static String getLocation(String num){
        return (String)weather_location.get(num);
    }
}