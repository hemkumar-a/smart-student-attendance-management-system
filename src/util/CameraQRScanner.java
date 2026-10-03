package util;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public class CameraQRScanner {

    private Webcam webcam;
    private WebcamPanel webcamPanel;
    private JFrame frame;

    private volatile boolean scanning = false;

    public synchronized void startScanner(
            Consumer<String> qrCallback) {

        // Prevent multiple scanner windows
        if (scanning) {
            System.out.println(
                    "QR scanner is already running."
            );
            return;
        }

        // Make sure any old webcam is completely closed
        closeScanner();

        webcam = Webcam.getDefault();

        if (webcam == null) {

            System.out.println(
                    "No webcam detected."
            );

            return;
        }

        webcam.setViewSize(
                new Dimension(640, 480)
        );

        try {

            webcam.open();

        } catch (Exception e) {

            System.out.println(
                    "Unable to open webcam: "
                    + e.getMessage()
            );

            webcam = null;

            return;
        }

        webcamPanel =
                new WebcamPanel(webcam);

        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setMirrored(true);

        frame =
                new JFrame("QR Code Scanner");

        frame.add(webcamPanel);

        frame.setSize(
                700,
                550
        );

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        // Important:
        // Close the actual webcam when the user
        // manually closes the scanner window.
        frame.addWindowListener(
                new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(
                            java.awt.event.WindowEvent e) {

                        closeScanner();
                    }
                }
        );

        frame.setVisible(true);

        scanning = true;

        startScanning(qrCallback);
    }

    private void startScanning(
            Consumer<String> qrCallback) {

        Thread scannerThread =
                new Thread(() -> {

            while (scanning) {

                Webcam currentWebcam;

                synchronized (this) {
                    currentWebcam = webcam;
                }

                if (currentWebcam == null
                        || !currentWebcam.isOpen()) {

                    break;
                }

                BufferedImage image;

                try {

                    image =
                            currentWebcam.getImage();

                } catch (Exception e) {

                    System.out.println(
                            "Camera read error: "
                            + e.getMessage()
                    );

                    break;
                }

                if (image == null) {
                    continue;
                }

                String qrResult =
                        decodeQRCode(image);

                if (qrResult != null
                        && !qrResult.isEmpty()) {

                    System.out.println(
                            "QR DETECTED: "
                            + qrResult
                    );

                    scanning = false;

                    SwingUtilities.invokeLater(() -> {

                        closeScanner();

                        if (qrCallback != null) {

                            qrCallback.accept(
                                    qrResult
                            );
                        }
                    });

                    break;
                }
            }

        }, "QR-Scanner-Thread");

        scannerThread.setDaemon(true);

        scannerThread.start();
    }

    private String decodeQRCode(
            BufferedImage image) {

        try {

            BufferedImageLuminanceSource source =
                    new BufferedImageLuminanceSource(
                            image
                    );

            BinaryBitmap bitmap =
                    new BinaryBitmap(
                            new HybridBinarizer(
                                    source
                            )
                    );

            Result result =
                    new MultiFormatReader()
                            .decode(bitmap);

            return result.getText();

        } catch (Exception e) {

            return null;
        }
    }

    public synchronized void closeScanner() {

        scanning = false;

        if (webcamPanel != null) {

            try {

                webcamPanel.stop();

            } catch (Exception e) {

                System.out.println(
                        "Error stopping webcam panel: "
                        + e.getMessage()
                );
            }

            webcamPanel = null;
        }

        if (webcam != null) {

            try {

                if (webcam.isOpen()) {
                    webcam.close();
                }

            } catch (Exception e) {

                System.out.println(
                        "Error closing webcam: "
                        + e.getMessage()
                );
            }

            webcam = null;
        }

        if (frame != null) {

            frame.dispose();
            frame = null;
        }
    }
}