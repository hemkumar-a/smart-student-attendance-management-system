package test;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import util.QRGenerator;

public class QRTest {

    public static void main(String[] args) {
        
        try {

           BufferedImage qrImage =
        QRGenerator.generateQRCode(
                "STUDENT-23AD001",
                300,
                300
        );

// Save QR image for scanner testing
String filePath = "student-qr.png";

QRGenerator.saveQRCode(
        "STUDENT-23AD001",
        filePath,
        300,
        300
);

System.out.println(
        "QR saved at: "
        + new java.io.File(filePath).getAbsolutePath()
);

            JFrame frame = new JFrame("QR Test");

            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            frame.add(
                    new JLabel(
                            new ImageIcon(qrImage)
                    )
            );

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}