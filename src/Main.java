import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class Main
{
    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Glowna();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

    }
}
