package dao;

//AbstractDaoFactory���p�����Ă���
public class OraDaoFactory extends AbstractDaoFactory{
    //�����֘A��Dao�N���X���i�[�����ۃ��\�b�h
    public PropertyDao getOraPropertyDao(){
        return new OraPropertyDao();
    }
    //���[�U�[�֘A��Dao�N���X���i�[�����ۃ��\�b�h
    public UserDao getOraUserDao(){
        return new OraUserDao();
    }
    //�����̏��֘A��Dao�N���X���i�[�����ۃ��\�b�h
    public ProfileDao getOraProfileDao(){
        return new OraProfileDao();
    }
    //�^�C�����C���֘A��Dao�N���X���i�[�����ۃ��\�b�h
    public TimeLineDao getOraTimeLineDao(){return new OraTimeLineDao();}
    //�^�C�����C���ɑ΂���R�����g�֘A��Dao�N���X���i�[�����ۃ��\�b�h
    public CommentDao getOraCommentDao(){return new OraCommentDao();}
    //�`���b�g�֘A��Dao�N���X���i�[�����ۃ��\�b�h
    public ChatDao getOraChatDao(){return new OraChatDao();}
    //�g�[�N�֘A��Dao�N���X���i�[�����ۃ��\�b�h
    public TalkDao getOraTalkDao(){return new OraTalkDao();}
    //�F�B�֘A��Dao�N���X���i�[�����ۃ��\�b�h
    public FriendDao getOraFriendDao(){return new OraFriendDao();}
}