package socket;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import java.util.Base64;
import function.PathHolder;
import function.RandomString;
import java.awt.image.BufferedImage;


public class Base64Decode{
    public String getFilePath(String base64,String extension){
        String filePath = PathHolder.pathName+"WEB-INF/talkImage/"+RandomString.getString(30)+"."+extension;
        try{
            byte[] imageBinary =  Base64.getDecoder().decode(base64);
            OutputStream out=new FileOutputStream(filePath);
            BinaryToBufferedImage BToBI = new BinaryToBufferedImage();
            BufferedImage bimage = BToBI.aaa(imageBinary);
            ImageIO.write(bimage,extension, out);
        }catch(Exception e){
            e.printStackTrace();
        }
        return filePath;
    }
}