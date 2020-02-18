package function;

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
    private String base64;
    private String extension;
    private static final String TEN =",";
    public String getFilePath(String path){
        String filePath = PathHolder.pathName+path+RandomString.getString(30)+"."+extension;
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
    public void setImagePath(String message){
        base64 = message.substring(message.indexOf(TEN) + TEN.length());
        extension = message.substring(message.indexOf("/")+1,message.indexOf(";"));
    }
}