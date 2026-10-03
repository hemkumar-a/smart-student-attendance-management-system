package util;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;

public class QRScanner {

    public static String decodeQRCode(BufferedImage image)
            throws NotFoundException {

        BufferedImageLuminanceSource source =
                new BufferedImageLuminanceSource(image);

        BinaryBitmap bitmap =
                new BinaryBitmap(
                        new HybridBinarizer(source)
                );

        Result result =
                new MultiFormatReader().decode(bitmap);

        return result.getText();
    }
}