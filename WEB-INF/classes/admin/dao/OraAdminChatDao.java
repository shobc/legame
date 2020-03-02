package admin.dao;


import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

import dao.function.Base64Image;
import admin.bean.ChatBean;
import java.util.ArrayList;

public class OraAdminChatDao implements AdminChatDao{
    public ArrayList getReportChatList(){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList chatList = new ArrayList();
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{

            cn = aoc.getConnection();
            String sql = "select c.CHAT_ID,u.search_id,u.top_picture\n" +
                    "from CHAT_TABLE c left join USER_INFORMATION_TABLE u on c.USER_CHAT1_ID = u.USER_ID where c.REPORT_FLAG = 1\n" +
                    "order by c.DELETE_TIME desc";
            st = cn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ChatBean cb = new ChatBean();
                cb.setChat_id(rs.getString(1));
                cb.setName(rs.getString(2));
                Blob blob = rs.getBlob(3);
                Base64Image bi = new Base64Image();
                cb.setTop_picture(bi.getBase64(blob));
                chatList.add(cb);
            }
            aoc.closeConnection();
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
    public boolean getJudge(int flag,String user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        boolean judge = false;
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            if(flag==1){
                String sql = "select count(chat_id) from chat_table\n" +
                        "where user_chat_id= 0 and user_chat1_id = ?";
                st = cn.prepareStatement(sql);
                st.setString(1,user_id);
            }else{
                String sql = "select count(chat_id) from chat_table\n" +
                        "where user_chat_id= ? and user_chat1_id = 0";
                st = cn.prepareStatement(sql);
                st.setString(1,user_id);
            }
            rs = st.executeQuery();
            rs.next();
            if(rs.getInt(1)==0){
                judge = true;
            }
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
    public void addChat(int flag,String user_id){
        PreparedStatement st = null;
        Connection cn = null;
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            if(flag==1){
                String sql="insert into chat_table(chat_id,user_chat_id,user_chat1_id) values(chat_sequesnce.nextval,0,?)";
                st = cn.prepareStatement(sql);
                st.setString(1,user_id);
            }else{
                String sql="insert into chat_table(chat_id,user_chat_id,user_chat1_id) values(chat_sequesnce.nextval,?,0)";
                st = cn.prepareStatement(sql);
                st.setString(1,user_id);
            }
            int count = st.executeUpdate();
            System.out.println(count+"åèèàóùÇµÇ‹ÇµÇΩ");
            st.close();
            aoc.commit();
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            aoc.rollback();
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
    public void releaseChat(String chat_id){
        PreparedStatement st = null;
        Connection cn = null;
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql="update chat_table set report_flag = 0 where chat_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            int count = st.executeUpdate();
            System.out.println(count+"åèèàóùÇµÇ‹ÇµÇΩ");
            st.close();
            aoc.commit();
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            aoc.rollback();
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