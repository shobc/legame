package dao;

import java.util.List;
import bean.PropertyBean;
import bean.BalanceBean;

//�����֘A�p��Dao�̃C���^�[�t�F�C�X
public interface PropertyDao{
    //�������`���[�W����
    void addPropery(PropertyBean p);
    //���v�c���ƍ��v�|�C���g���擾����
    BalanceBean getBalanceProperty(String id);
    //�������g��
    void employMoney(PropertyBean p);
    //���[�U�[��ID�ɂ����闚�����擾����
    List getAllProperty(String id);
    //QR�̕����������������
    void updateQRCode(String user_id,String randomString);
}