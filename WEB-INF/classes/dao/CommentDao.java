package dao;

//�R�����g�Ɋւ���f�[�^�������̂��邽�߂�Bean�t�@�C��
import bean.CommentBean;
import java.util.ArrayList;

//�^�C�����C���̃R�����g�p��Dao�̃C���^�[�t�F�C�X
public interface CommentDao{
    //�R�����g�ɑ΂��邢����
    void addCommentLike(CommentBean cb);
    //�R�����g�ɑ΂��邢���˂��폜����
    void deleteCommentLike(CommentBean cb);
    //�^�C�����C���̃R�����g��ǉ�����
    void addComment(CommentBean cb);
    //�^�C�����C���̃R�����g�ꗗ��\������
    ArrayList getComment(CommentBean cb);
}