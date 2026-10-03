package test;

import com.github.sarxos.webcam.Webcam;

public class CameraTest {

    public static void main(String[] args) {

        Webcam webcam = Webcam.getDefault();

        if (webcam == null) {
            System.out.println("No webcam detected.");
            return;
        }

        System.out.println(
                "Camera found: "
                + webcam.getName()
        );

        webcam.open();

        System.out.println(
                "Camera opened successfully."
        );

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        webcam.close();

        System.out.println(
                "Camera closed."
        );
    }
}