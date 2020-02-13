package function;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import javax.imageio.ImageIO;


import org.apache.commons.lang.RandomStringUtils;

public class CreateQRCode{
    public static String randomString;
    public static String filePathImage;
    public static CreateQRCode getQRCode(String URL){
        CreateQRCode createQRCode = new CreateQRCode();
        randomString = RandomString.getString(30);
        filePathImage = RandomString.getString(10);
        try{
            System.out.println(randomString);
            //QR�R�[�h�������̃G���R�[�f�B���O
            String encoding = "UTF-8";
            //�T�C�Y(�s�N�Z��)
            int size = 500;
            //�摜�t�@�C���̕ۑ���
            String filePath =PathHolder.pathName+"QRImage/"+filePathImage+".png";
            System.out.println("filePath"+filePath);

            //��������
            ConcurrentHashMap hints = new ConcurrentHashMap();
            //�G���[�������x���w��
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            //�G���R�[�f�B���O�w��
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
            //�}�[�W���w��
            hints.put(EncodeHintType.MARGIN, 0);
            QRCodeWriter writer = new QRCodeWriter();
//            BitMatrix bitMatrix = writer.encode(randomString, BarcodeFormat.QR_CODE, size, size, hints);
            BitMatrix bitMatrix = writer.encode("http://172.19.1.152:8080/legame/"+URL+randomString, BarcodeFormat.QR_CODE, size, size, hints);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            //�t�@�C���ւ̕ۑ�����
            ImageIO.write(image, "png", new File(filePath));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (WriterException e){
            System.out.println(e.getMessage());
        }
        return createQRCode;
    }
}