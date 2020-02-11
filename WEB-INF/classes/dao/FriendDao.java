package dao;

//�F�B�Ɋւ���f�[�^�������̂��邽�߂�Bean�t�@�C��
import bean.FriendBean;
import java.util.ArrayList;

//�F�B�p��Dao�̃C���^�[�t�F�C�X
public interface FriendDao{
    //���葤���F�B�ǉ��������J�E���g����
    String getNewFriendCount(String user_id);
    //�F�B�ǉ�����������擾����
    ArrayList getNewFriend(String user_id);
    //�F�B���u���b�N���Ă����̂���������
    void releaseBlockFriend(FriendBean fb);
    //�F�B���u���b�N����
    void blockFriend(FriendBean fb);
    //�u���b�N�����F�B���擾����
    ArrayList getBlockFriend(String user_id);
    //�F�B���폜����
    void deleteFriend(FriendBean fb);
    //�F�B��ǉ�����
    void addFriend(FriendBean fb);
    //�F�B��search_id�Ō����������ăv���t�B�[�����擾����
    FriendBean getSearchFriend(FriendBean fb);
    //�F�B���X�g���擾����
    ArrayList getFriend(String user_id);

    FriendBean getFriendQRUser_id(String QRCode);

    FriendBean getFriendInfo(String friend_id);

    boolean getFriendAddJudge(String chat_id);

    boolean getFriendDeleteOrBlockJudge(String chat_id);

    void addFriend(String chat_id);

    void releaseFriend(String chat_id);

}