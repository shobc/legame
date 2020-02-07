package dao;

//�`���b�g�Ɋւ���f�[�^�������̂��邽�߂�Bean�t�@�C��
import bean.ChatBean;
import java.util.ArrayList;

//�`���b�g�p��Dao�̃C���^�[�t�F�C�X
public interface ChatDao{
    //�`���b�g��ǉ�����
    void addChat(ChatBean cb);
    //�`���b�g������Ă��邩���肷��
    boolean getJudge(ChatBean cb);
    //�`���b�g��Id���擾����
    String getChatId(ChatBean cb);
    //�`���b�g�ꗗ���擾����
    ArrayList getChat(String user_id);

    void deleteChat(String chat_id);

    boolean deleteJudge(ChatBean cb);

    void updateDeleteFlag(ChatBean cb);

    boolean blockJudge(ChatBean cb);

}