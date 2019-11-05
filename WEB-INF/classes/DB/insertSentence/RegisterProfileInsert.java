package DB.insertSentence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class RegisterProfileInsert{
    public static PreparedStatement insertProfile(Connection cn,String user_id,String name,String id,String comment,String filePath){
        PreparedStatement st = null;
        FileInputStream fip = null;
        try {
            File file = new File(filePath);
            fip = new FileInputStream(file);

            st = cn.prepareStatement("insert into user_information_table values(?,?,?,?,?)");
            st.setString(1,user_id);
            st.setString(2,id);
            st.setString(3,name);
            st.setString(4,comment);
            st.setBinaryStream(5, fip,(int)file.length());

        } catch (IOException e) {
        } catch (SQLException e) {
        } catch (Exception e) {
        }
        return st;
    }
}