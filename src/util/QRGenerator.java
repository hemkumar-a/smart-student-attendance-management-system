package util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRGenerator {

    public static BufferedImage generateQRCode(
            String text,
            int width,
            int height) throws WriterException {

        BitMatrix bitMatrix =
                new MultiFormatWriter().encode(
                        text,
                        BarcodeFormat.QR_CODE,
                        width,
                        height
                );

        BufferedImage image =
                new BufferedImage(
                        width,
                        height,
                        BufferedImage.TYPE_INT_RGB
                );

        for (int x = 0; x < width; x++) {

            for (int y = 0; y < height; y++) {

                image.setRGB(
                        x,
                        y,
                        bitMatrix.get(x, y)
                                ? 0xFF000000
                                : 0xFFFFFFFF
                );
            }
        }

        return image;
    }

    public static void saveQRCode(
            String text,
            String filePath,
            int width,
            int height)
            throws WriterException, IOException {

        BufferedImage image =
                generateQRCode(
                        text,
                        width,
                        height
                );

        File file = new File(filePath);

        ImageIO.write(
                image,
                "PNG",
                file
        );
    }
}