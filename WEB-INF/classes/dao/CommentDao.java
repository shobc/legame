package dao;

//�R�����g�Ɋւ���f�[�^�������̂��邽�߂�Bean�t�@�C��
import bean.CommentBean;
import java.util.ArrayList;

//�^�C�����C���̃R�����g�p��Dao�̃C���^�[�t�F�C�X
public interface CommentDao{
    //�^�C�����C���̃R�����g��ǉ�����
    void addComment(CommentBean cb);
    //�^�C�����C���̃R�����g�ꗗ��\������
    ArrayList getComment(String timeline_id);
}