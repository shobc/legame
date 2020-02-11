package dao.function;

import java.sql.Blob;
import java.sql.SQLException;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.io.InputStream;
import java.io.IOException;
import dao.OracleConnectionManager;

public class Base64Image{
    public String getBase64(Blob blob) throws SQLException{
        String base64Image = null;
        try{

            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            base64Image = Base64.getEncoder().encodeToString(imageBytes);
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            OracleConnectionManager.getInstance().rollback();
        }
        return base64Image;
    }
}