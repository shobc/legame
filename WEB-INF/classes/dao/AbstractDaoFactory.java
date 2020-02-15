package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//Oracle�p��DaoDaoFactory���Ăяo�����߂̃N���X
public abstract class AbstractDaoFactory{
    public static AbstractDaoFactory getFactory(){
        AbstractDaoFactory factory = null;
        Properties prop = new Properties();

        try{
            prop.load(new FileInputStream("c:\\legame\\dao.properties"));
            String name = prop.getProperty("dao");
            Class c = Class.forName(name);
            factory = (AbstractDaoFactory)c.newInstance();
        }catch(FileNotFoundException e){
            throw new RuntimeException(e.getMessage(),e);
        }catch(IOException e){
            throw new RuntimeException(e.getMessage(),e);
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e.getMessage(),e);
        }catch(InstantiationException e){
            throw new RuntimeException(e.getMessage(),e);
        }catch(IllegalAccessException e){
            throw new RuntimeException(e.getMessage(),e);
        }
        //��ۃN���X�̃C���X�^���X��Ԃ�
        return factory;
    }
    //�����֘A��Dao�N���X���i�[���钊�ۃ��\�b�h
    public abstract PropertyDao getOraPropertyDao();
    //���[�U�[�֘A��Dao�N���X���i�[���钊�ۃ��\�b�h
    public abstract UserDao getOraUserDao();
    //�����̏��֘A��Dao�N���X���i�[���钊�ۃ��\�b�h
    public abstract ProfileDao getOraProfileDao();
    //�^�C�����C���֘A��Dao�N���X���i�[���钊�ۃ��\�b�h
    public abstract TimeLineDao getOraTimeLineDao();
    //�^�C�����C���ɑ΂���R�����g�֘A��Dao�N���X���i�[���钊�ۃ��\�b�h
    public abstract CommentDao getOraCommentDao();
    //�`���b�g�֘A��Dao�N���X���i�[���钊�ۃ��\�b�h
    public abstract ChatDao getOraChatDao();
    //�g�[�N�֘A��Dao�N���X���i�[���钊�ۃ��\�b�h
    public abstract TalkDao getOraTalkDao();
    //�F�B�֘A��Dao�N���X���i�[���钊�ۃ��\�b�h
    public abstract FriendDao getOraFriendDao();
}