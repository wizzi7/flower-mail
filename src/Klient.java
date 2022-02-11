import java.io.FileWriter;
import java.io.IOException;

public class Klient

{

    private String imie;
    private String nazwisko;
    private String email;
    private Adres adres;

    public Klient(String imie, String nazwisko, String email, Adres adres) throws IOException {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.adres = adres;
        this.zapiszKlientaDoPliku("klienci.csv",";");
    }

    public void zapiszKlientaDoPliku(String filename, String sep) throws IOException {
        FileWriter out = new FileWriter(filename,true);
        String[] line = new String[7];
        line[0] = this.imie;
        line[1] = this.nazwisko;
        line[2] = this.email;
        line[3] = this.adres.getUlica();
        line[4] = this.adres.getNumerUlicy();
        line[5] = this.adres.getKodPocztowy();
        line[6] = this.adres.getMiasto();

        for(int i = 0 ; i < line.length-1 ;i++)
        {
            try {
                out.write(line[i]+sep);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        try {
            out.write(line[line.length-1]+'\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }
}