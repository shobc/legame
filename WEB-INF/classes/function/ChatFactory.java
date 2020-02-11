package function;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Enumeration;

public class ChatFactory{
    //properties���C���X�^���X��
    private static Properties prop = new Properties();
    private static String path = "C:\\legame\\WEB-INF\\file\\chat.properties";
    //public�Ȉ����̂Ȃ��R���X�g���N�^
    private ChatFactory(){}
    //properties�t�@�C����ǂݍ��ނ��߂̃��\�b�h
    public static String read(String key){
        String value = null;
        System.out.println(path);
        try{
            //path����properties�t�@�C�������[�h����
            prop.load(new FileInputStream(path));
            //key�𗊂�ɕ�������擾����
            value = prop.getProperty(key);
        }catch(IOException e){
            e.printStackTrace();
        }
        //�擾�����z���Ԃ�
        return value;
    }
    //properties�t�@�C���ɒl�����郁�\�b�h
    public static void load(String key,String sessionId){
        try{
            //key�ƕ����������
            prop.setProperty(key,sessionId);
            //path����properties�t�@�C�����擾���ۑ�����
            prop.store(new FileOutputStream(path), "new chat commit.");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}