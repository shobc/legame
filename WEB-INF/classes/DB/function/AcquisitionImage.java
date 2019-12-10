package DB.function;

import java.io.BufferedInputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import function.PathHolder;


public class AcquisitionImage{
    private PreparedStatement st = null;
    private ResultSet rs = null;
    private FileOutputStream output = null;

    public String getImagePath(String user_id,String search_id,Blob blob) {
        String pathImage = null;
        try {
            File file = new File(PathHolder.pathName+"image/"+user_id+search_id+".jpg");
            pathImage = "image/"+user_id+search_id+".jpg";

            output = new FileOutputStream(file);

            BufferedInputStream binput = new BufferedInputStream(blob.getBinaryStream());

            byte[] buff = new byte[4*1024];
            while(true){
                int size = binput.read(buff,0,buff.length);
                if(size==-1)break;
                output.write(buff,0,size);
            }
            binput.close();
        } catch (IOException e) {

        } catch (SQLException e) {

        } catch (Exception e) {

        }
        return pathImage;
    }
}