package dao.function;

import java.sql.Blob;
import java.sql.SQLException;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.io.InputStream;
import java.io.IOException;

public class Base64Image{
    public String getBase64(Blob blob) throws SQLException,IOException{
        String base64Image = null;

        InputStream inputStream = blob.getBinaryStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        byte[] imageBytes = outputStream.toByteArray();
        base64Image = Base64.getEncoder().encodeToString(imageBytes);
        return base64Image;
    }
}