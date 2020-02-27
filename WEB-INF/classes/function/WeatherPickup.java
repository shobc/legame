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
        weather_location.put("1","東京都");
        weather_location.put("2","神奈川県");
        weather_location.put("3","埼玉県");
        weather_location.put("4","千葉県");
        weather_location.put("5","茨城県");
        weather_location.put("6","栃木県");
        weather_location.put("7","群馬県");
        weather_location.put("8","山梨県");

        weather_icon.put("晴れ","/100_day.png");
        weather_icon.put("晴時々曇","/101_day.png");
        weather_icon.put("晴一時雨","/102_day.png");
        weather_icon.put("晴時々雨","/103_day.png");
        weather_icon.put("晴一時雪","/104_day.png");
        weather_icon.put("晴時々雪","/105_day.png");
        weather_icon.put("晴のち曇","/111_day.png");
        weather_icon.put("晴のち雨","/114_day.png");
        weather_icon.put("晴のち雪","/117_day.png");
        weather_icon.put("晴のち雨（雷を伴う）","/119_day.png");
        weather_icon.put("晴時々雨（雷を伴う）","/140_day.png");
        weather_icon.put("晴のち雪（雷を伴う）","/149_day.png");
        weather_icon.put("晴時々雪（雷を伴う）","/150_day.png");
        weather_icon.put("晴時々曇（雨の可能性あり）","/156_day.png");
        weather_icon.put("晴時々曇り（雷を伴う）","/158_day.png");
        weather_icon.put("晴のち曇（雨の可能性あり）","/166_day.png");
        weather_icon.put("晴のち曇り（雷を伴う）","/168_day.png");
        weather_icon.put("曇り","/200_day.png");
        weather_icon.put("曇時々晴","/201_day.png");
        weather_icon.put("曇のち晴","/201_day.png");
        weather_icon.put("曇一時雨","/202_day.png");
        weather_icon.put("曇時々雨","/203_day.png");
        weather_icon.put("曇一時雪","/204_day.png");
        weather_icon.put("曇時々雪","/205_day.png");
        weather_icon.put("曇（雨は降らない）のち晴","/211_day.png");
        weather_icon.put("曇のち雨","/214_day.png");
        weather_icon.put("曇のち雪","/217_day.png");
        weather_icon.put("曇のち雨（雷を伴う）","/219_day.png");
        weather_icon.put("曇時々雨（雷を伴う）","/240_day.png");
        weather_icon.put("曇のち雪（雷を伴う）","/249_day.png");
        weather_icon.put("曇時々雪（雷を伴う）","/250_day.png");
        weather_icon.put("曇り（雨の可能性あり）","/255_day.png");
        weather_icon.put("曇（雨の可能性あり）時々晴","/256_day.png");
        weather_icon.put("曇り（雷を伴う）","/257_day.png");
        weather_icon.put("曇（雷を伴う）時々晴","/258_day.png");
        weather_icon.put("曇（雨の可能性あり）のち晴","/266_day.png");
        weather_icon.put("曇（雷を伴う）のち晴","/268_day.png");
        weather_icon.put("雨","/300_day.png");
        weather_icon.put("雨時々晴","/301_day.png");
        weather_icon.put("雨時々曇","/302_day.png");
        weather_icon.put("雨のち晴","/311_day.png");
        weather_icon.put("雨のち曇","/313_day.png");
        weather_icon.put("雨のち雪","/315_day.png");
        weather_icon.put("雨時々雪","/330_day.png");
        weather_icon.put("雨（雷を伴う）のち晴","/341_day.png");
        weather_icon.put("雨（雷を伴う）のち曇","/343_day.png");
        weather_icon.put("雨（雷を伴う）のち雪","/345_day.png");
        weather_icon.put("雨（雷を伴う）","/350_day.png");
        weather_icon.put("雪","/400_day.png");
        weather_icon.put("雪時々晴","/401_day.png");
        weather_icon.put("雪時々曇","/402_day.png");
        weather_icon.put("雪のち晴","/411_day.png");
        weather_icon.put("雪のち曇","/413_day.png");
        weather_icon.put("雪のち雨","/414_day.png");
        weather_icon.put("雪（雷を伴う）のち晴","/441_day.png");
        weather_icon.put("雪（雷を伴う）のち曇","/443_day.png");
        weather_icon.put("雪（雷を伴う）のち雨","/444_day.png");
        weather_icon.put("雪（雷を伴う）","/450_day.png");
        weather_icon.put("大雨","/500_day.png");
        weather_icon.put("大雨（雷を伴う）","/550_day.png");
        weather_icon.put("大雪","/600_day.png");
        weather_icon.put("大雪（雷を伴う）","/650_day.png");
        weather_icon.put("暴風雨","/700_day.png");
        weather_icon.put("暴風雨（雷を伴う）","/750_day.png");
        weather_icon.put("暴風雪","/800_day.png");
        weather_icon.put("暴風雪（雷を伴う）","/850_day.png");

        //東京都
        weather_rss.put("1","https://rss-weather.yahoo.co.jp/rss/days/13.xml");
        //神奈川県
        weather_rss.put("2","https://rss-weather.yahoo.co.jp/rss/days/14.xml");
        //埼玉県
        weather_rss.put("3","https://rss-weather.yahoo.co.jp/rss/days/11.xml");
        //千葉県
        weather_rss.put("4","https://rss-weather.yahoo.co.jp/rss/days/12.xml");
        //茨城県
        weather_rss.put("5","https://rss-weather.yahoo.co.jp/rss/days/8.xml");
        //栃木県
        weather_rss.put("6","https://rss-weather.yahoo.co.jp/rss/days/9.xml");
        //群馬県
        weather_rss.put("7","https://rss-weather.yahoo.co.jp/rss/days/10.xml");
        //山梨県
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