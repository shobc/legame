package function;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Enumeration;

public class ChatFactory{
    //propertiesをインスタンス化
    private static Properties prop = new Properties();
    private static String path = "C:\\legame\\WEB-INF\\file\\chat.properties";
    //publicな引数のないコンストラクタ
    private ChatFactory(){}
    //propertiesファイルを読み込むためのメソッド
    public static String read(String key){
        String value = null;
        System.out.println(path);
        try{
            //pathからpropertiesファイルをロードする
            prop.load(new FileInputStream(path));
            //keyを頼りに文字列を取得する
            value = prop.getProperty(key);
        }catch(IOException e){
            e.printStackTrace();
        }
        //取得した配列を返す
        return value;
    }
    //propertiesファイルに値を入れるメソッド
    public static void load(String key,String sessionId){
        try{
            //keyと文字列を入れる
            prop.setProperty(key,sessionId);
            //pathからpropertiesファイルを取得し保存する
            prop.store(new FileOutputStream(path), "new chat commit.");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}