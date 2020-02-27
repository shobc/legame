package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

import dao.function.Base64Image;

import bean.ChatBean;
import java.util.ArrayList;

//�`���b�g�̃f�[�^����舵�����߂�Dao�N���X
public class OraChatDao implements ChatDao{
    //�`���b�g��ǉ�����
    public void addChat(ChatBean cb){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();

            if(cb.getFriend_id()!=null){
                String sql="insert into chat_table(chat_id,user_chat_id,user_chat1_id) values(chat_sequesnce.nextval,?,?)";
                st = cn.prepareStatement(sql);
                st.setString(1,cb.getUser_id());
                st.setString(2,cb.getFriend_id());
            }else{
                String sql="insert into chat_table(chat_id,user_chat_id,user_chat1_id) " +
                        "values(chat_sequesnce.nextval,(select user_chat1_id from chat_table where chat_id = ? and user_chat_id = ?),?)";
                st = cn.prepareStatement(sql);
                st.setString(1,cb.getChat_id());
                st.setString(2,cb.getUser_id());
                st.setString(3,cb.getUser_id());
            }
            int count = st.executeUpdate();
            System.out.println(count+"���������܂���");
            st.close();
            oc.commit();
            oc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            oc.rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    //�`���b�g������Ă��邩���肷��
    public boolean getJudge(ChatBean cb){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        boolean judge = false;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            if(cb.getFriend_id()!=null){
                String sql = "select count(chat_id) from chat_table " +
                        "where user_chat_id= ? and user_chat1_id = ?";
                st = cn.prepareStatement(sql);
                st.setString(1,cb.getUser_id());
                st.setString(2,cb.getFriend_id());
            }else{
                String sql = "select count(chat_id) from chat_table " +
                        "where user_chat1_id = ? and user_chat_id = " +
                        "(select user_chat1_id from chat_table where chat_id = ?)";
                st = cn.prepareStatement(sql);
                st.setString(1,cb.getUser_id());
                st.setString(2,cb.getChat_id());
            }
            rs = st.executeQuery();
            rs.next();
            String j = rs.getString(1);
            System.out.println("j="+j);
            //�O�������ꍇ�`���b�g���[�����쐬����
            if(j.equals("0")){
                judge = true;
            }
            rs.close();
            st.close();
            oc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return judge;
    }
    //�`���b�g��Id���擾����
    public String getChatId(ChatBean cb){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        String chat_id = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select chat_id from chat_table " +
                    "where user_chat_id= ?  and user_chat1_id = ?";

            st = cn.prepareStatement(sql);
            st.setString(1,cb.getUser_id());
            st.setString(2,cb.getFriend_id());
            rs = st.executeQuery();
            rs.next();
            chat_id = rs.getString(1);
            rs.close();
            st.close();
            oc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return chat_id;
    }
    //�`���b�g�ꗗ���擾����
    public ArrayList getChat(String user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList chatList = new ArrayList();
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select u.user_id,u.nickname,u.top_picture,c.chat_id\n" +
                    "    ,(select NVL(CONTENT,'�摜������܂�') from TALK_TABLE where TALK_ID = (select MAX(TALK_ID) from TALK_TABLE where (CHAT_ID = c.chat_id and CHAT1_ID =\n" +
                    "    (select CHAT_ID from CHAT_TABLE where USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID) and USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID)))\n" +
                    "    or  (CHAT_ID = (select CHAT_ID from CHAT_TABLE where USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID) and USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID)) and  CHAT1_ID = c.chat_id))\n" +
                    "    and BLOCK_FLAG = 0 )\n" +
                    "    ,(select count(TALK_TABLE.TALK_ID) from TALK_TABLE where ALREADY_READ_FLAG = 0 and BLOCK_FLAG = 0 and\n" +
                    "    (CHAT_ID = (select CHAT_ID from CHAT_TABLE where USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID) and USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID)) and  CHAT1_ID = c.chat_id))\n" +
                    "    from CHAT_TABLE c left join USER_INFORMATION_TABLE u\n" +
                    "    on c.USER_CHAT1_ID = u.USER_ID where c.USER_CHAT_ID = ? and delete_flag = 0\n" +
                    "    order by (select MESS_TIME from TALK_TABLE where TALK_ID = (select MAX(TALK_ID) from TALK_TABLE where (CHAT_ID = c.chat_id and CHAT1_ID =\n" +
                    "    (select CHAT_ID from CHAT_TABLE where USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID) and USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID)))\n" +
                    "    or  (CHAT_ID = (select CHAT_ID from CHAT_TABLE where USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID) and USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID)) and  CHAT1_ID = c.chat_id))\n" +
                    "    and BLOCK_FLAG = 0 ) desc";
//�������̂��Ƃ���������
//                    "select u.user_id,u.nickname,u.top_picture,c.chat_id\n" +
//                    ",(select NVL(CONTENT,'�摜������܂�') from TALK_TABLE where TALK_ID = (select MAX(TALK_ID) from TALK_TABLE where (CHAT_ID = c.chat_id and CHAT1_ID =\n" +
//                    "(select CHAT_ID from CHAT_TABLE where USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID) and USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID)))\n" +
//                    "or  (CHAT_ID = (select CHAT_ID from CHAT_TABLE where USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID) and USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID)) and  CHAT1_ID = c.chat_id))\n" +
//                    "and BLOCK_FLAG = 0 )\n" +
//                    ",(select count(TALK_TABLE.TALK_ID) from TALK_TABLE where ALREADY_READ_FLAG = 0 and BLOCK_FLAG = 0 and \n" +
//                    "(CHAT_ID = (select CHAT_ID from CHAT_TABLE where USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID) and USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID)) and  CHAT1_ID = c.chat_id))\n" +
//                    "from CHAT_TABLE c left join USER_INFORMATION_TABLE u\n" +
//                    "on c.USER_CHAT1_ID = u.USER_ID where c.USER_CHAT_ID = ? and delete_flag = 0"

            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            rs = st.executeQuery();
            while(rs.next()){
                ChatBean cb = new ChatBean();
                cb.setUser_id(rs.getString(1));
                cb.setName(rs.getString(2));
                Blob blob = rs.getBlob(3);
                Base64Image bi = new Base64Image();
                cb.setTop_picture(bi.getBase64(blob));
                cb.setChat_id(rs.getString(4));
                String content = rs.getString(5);
                if(content!=null){
                    if(content.length()>=12){
                        content = content.substring(0,12)+"...";
                    }
                }
                cb.setContent(content);
                cb.setNot_read_count(rs.getString(6));
                chatList.add(cb);
            }
            oc.closeConnection();
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return chatList;
    }
    public void deleteChat(String chat_id){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="update chat_table set delete_flag = 1,delete_time = sysdate where chat_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            int count = st.executeUpdate();
            System.out.println(count+"���������܂���");
            st.close();
            oc.commit();
            oc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            oc.rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public boolean deleteJudge(ChatBean cb){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        boolean judge = false;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
           if(cb.getFriend_id()!=null){
               String sql = "select count(chat_id) from chat_table\n" +
                       "where chat_id = (select CHAT_ID from CHAT_TABLE where USER_CHAT_ID =(select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = ?) and USER_CHAT1_ID =(select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = ?))  and user_chat1_id = ? and delete_flag = 1";
               st = cn.prepareStatement(sql);
               st.setString(1,cb.getChat_id());
               st.setString(2,cb.getChat_id());
               st.setString(3,cb.getFriend_id());
           }else{
               String sql = "select count(chat_id) from chat_table\n" +
                       "where chat_id = ? and user_chat_id = ? and delete_flag = 1";
               st = cn.prepareStatement(sql);
               st.setString(1,cb.getChat_id());
               st.setString(2,cb.getUser_id());
           }
            rs = st.executeQuery();
            rs.next();
            if(rs.getInt(1)==1){
                judge = true;
            }

            rs.close();
            st.close();
            oc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return judge;
    }

    public void updateDeleteFlag(ChatBean cb){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();

            if(cb.getFriend_id()!=null){
                String sql="update chat_table set delete_flag = 0 ,delete_time = sysdate where chat_id = (select CHAT_ID " +
                    "from CHAT_TABLE where USER_CHAT_ID =(select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = ?) " +
                    "and USER_CHAT1_ID =(select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = ?))  and user_chat1_id = ?";
                st = cn.prepareStatement(sql);
                st.setString(1,cb.getChat_id());
                st.setString(2,cb.getChat_id());
                st.setString(3,cb.getFriend_id());
            }else{
                String sql="update chat_table set delete_flag = 0 ,delete_time = sysdate where chat_id = ? and user_chat_id = ?";
                st = cn.prepareStatement(sql);
                st.setString(1,cb.getChat_id());
                st.setString(2,cb.getUser_id());
            }
            int count = st.executeUpdate();
            System.out.println(count+"���������܂���");
            st.close();
            oc.commit();
            oc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            oc.rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public boolean blockJudge(ChatBean cb){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        boolean judge = true;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select count(friend_table.friend_id) from friend_table where friend_flag = 1 " +
                         "and friend_id = ? and user_id = (select CHAT_TABLE.USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = ?)";

            st = cn.prepareStatement(sql);
            st.setString(1,cb.getFriend_id());
            st.setString(2,cb.getChat_id());
            rs = st.executeQuery();
            rs.next();
            if(rs.getInt(1)==1){
                judge = false;
            }
            rs.close();
            st.close();
            oc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return judge;
    }
    public String getReceiverChatId(String chat_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        String receiver_chat_id = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select chat_id from CHAT_TABLE where CHAT_ID = (select CHAT_ID from CHAT_TABLE \n" +
                    "where USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = ?) \n" +
                    "and USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = ?))";

            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            st.setString(2,chat_id);
            rs = st.executeQuery();
            rs.next();
            receiver_chat_id = rs.getString(1);
            rs.close();
            st.close();
            oc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return receiver_chat_id;
    }
    public void reportFriend(ChatBean cb){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="update chat_table set report_flag = 1 where user_chat_id = ? and user_chat1_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,cb.getUser_id());
            System.out.println("cb.getUser_id()"+cb.getUser_id());
            st.setString(2,cb.getFriend_id());
            System.out.println("cb.getFriend_id()"+cb.getFriend_id());

            int count = st.executeUpdate();
            System.out.println(count+"���������܂���");
            st.close();
            oc.commit();
            oc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}