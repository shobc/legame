package dao;

//���O�C�����Ă��郆�[�U�[�̃f�[�^��ێ�����Bean
import bean.UserBean;

//���O�C�����[�U�[�p��Dao�̃C���^�[�t�F�C�X
public interface ProfileDao{
    //���O�C�����Ƀ��[�U�[�̃��[���ƃp�X�Ńv���t�B�[�����擾����
    UserBean getProfile(String mail,String pass);
    //�V�K�o�^���Ă��郆�[�U�[�̃v���t�B�[����ǉ�����
    void addProfile(UserBean ub);
    //���O�C�����[�U�[�̃v���t�B�[�����擾����
    UserBean getProfile(UserBean ub);
    //
    UserBean getProfile(String chat_id);
    //�g�b�v�摜��ύX
    void updateTopPictureProfile(UserBean ub);
    //�ꌾ��ύX
    void updateSingleWordProfile(UserBean ub);
    //���O��ύX
    void updateNameProfile(UserBean ub);
    //���[�U�[����ID��ύX
    void updateSearchIdProfile(UserBean ub);
    //�v���t�B�[����ύX
    void UpdateUserTopPicture(UserBean ub);

    void UpdateFriendQRCode(String user_id,String QRCode);

    boolean sufferSearchId(String search_id);

}