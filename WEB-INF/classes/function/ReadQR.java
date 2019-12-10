package function;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.servlet.http.Part;

public class ReadQR {
    public static String getRandomString(Part imagePart){
        String result = null;
        try{
            String imagePath = ProfileImageName.getProfileImageName(imagePart);
            System.out.println("imagePath"+imagePath);
            //読み取り処理
            BufferedImage image = ImageIO.read(new File(PathHolder.pathName+"QRImage/"+imagePath));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new MultiFormatReader();
            Result decodeResult = reader.decode(bitmap);

            //デコード処理
            result = decodeResult.getText();
            System.out.println(result);
            //標準出力
        }catch (FormatException e){
            System.out.println(e.getMessage());
        }catch (IOException  e){
            System.out.println(e.getMessage());
        }catch (NotFoundException e){
            System.out.println(e.getMessage());
        }catch (ChecksumException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
}