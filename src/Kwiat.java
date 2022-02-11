import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

public class Kwiat extends Frame implements Serializable
{
    private String nazwaKwiatu;
    private double cenaZaSztuke;
    private int liczbaSztukNaMagazynie;
    private BufferedImage img;
    private URL url;
    public static Bukiet bukiet = new Bukiet();
    public Kwiat(){}

    /**
     * @param nazwaKwiatu nazwa danego kwiatu
     * @param cenaZaSztuke cena za 1 sztuke kwiatu
     * @param liczbaSztukNaMagazynie liczba dostepnych sztuk na magazynie danego kwiatu
     */
    public Kwiat( String nazwaKwiatu, double cenaZaSztuke, int liczbaSztukNaMagazynie, URL url )  {
        this.nazwaKwiatu = nazwaKwiatu;
        this.cenaZaSztuke = cenaZaSztuke;
        this.liczbaSztukNaMagazynie = liczbaSztukNaMagazynie;
        this.url=url;
        this.wczytajImage(url );
    }
    public void wczytajImage(URL url )
    {
        try {
            this.img = ImageIO.read(url);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public BufferedImage getImage()
    {
        BufferedImage resized = resize(this.img, 210, 210);
        return resized;
    }
    public BufferedImage getImageToOknoProduktu()
    {
        BufferedImage resized = resize(this.img, 180, 180);
        return resized;
    }
    public String getUrl()
    {
        return this.url+"";
    }

    public String getNazwaKwiatu()
    {
        return this.nazwaKwiatu;
    }
    public double getCenaZaSztuke()
    {
        return this.cenaZaSztuke;
    }
    public int getLiczbaSztukNaMagazynie()
    {
        return this.liczbaSztukNaMagazynie;
    }
    public void setLiczbaSztukNaMagazynie(int liczbaSztukNaMagazynie)
    {
        this.liczbaSztukNaMagazynie = liczbaSztukNaMagazynie;
    }
}
