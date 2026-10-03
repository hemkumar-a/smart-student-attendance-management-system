package test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import util.QRScanner;

public class QRScanTest {

    public static void main(String[] args) {

        try {

            // Change this path to your QR image
            File file = new File("student-qr.png");

            BufferedImage image =
                    ImageIO.read(file);

            String result =
                    QRScanner.decodeQRCode(image);

            System.out.println(
                    "QR Result: " + result
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}