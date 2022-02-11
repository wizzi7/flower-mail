import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Kwiaciarnia {
    private String idKwiaciarni;
    public List<Kwiat> magazyn = new LinkedList<>();
    private Adres adres;

    public Kwiaciarnia() {}

    public String getIdKwiaciarni() {
        return this.idKwiaciarni;
    }

    public Adres getAdres() {
        return this.adres;
    }

    public void setAdres(String ulica, String numerUlicy, String kodPocztowy, String miasto) {
        this.adres = new Adres(ulica, numerUlicy, kodPocztowy, miasto);
    }

    public void dodajKwiatDoMagazynu(Kwiat kwiat) {
        magazyn.add(kwiat);
    }

    public void zapiszZawartoscMagazynuDoPliku(String filename, String sep) throws IOException {
        FileWriter out = new FileWriter(filename);
        for(Kwiat kwiat : magazyn)
        {
            String[] line = new String[4];
            line[0] = kwiat.getNazwaKwiatu();
            line[1] = Double.toString(kwiat.getCenaZaSztuke());
            line[2] = Integer.toString(kwiat.getLiczbaSztukNaMagazynie());
            line[3] = kwiat.getUrl();

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
        }
        out.close();

    }

    public void odczytajZawartoscMagazynuZPliku(String filename, String sep) throws IOException {
        Scanner in = new Scanner(new File(filename));
        String line;
        String [] mag;
        while (in.hasNext()) {
            line = in.nextLine();
            mag = line.split(sep);
            magazyn.add(new Kwiat(mag[0], Double.parseDouble(mag[1]), Integer.parseInt(mag[2]), new URL(mag[3])));
        }
    }



    public void przyjmijDostawe(String filename, String sep) throws FileNotFoundException, MalformedURLException {
        Scanner in = new Scanner(new File(filename));
        String line;
        String [] mag;
        while (in.hasNext()) {
            line = in.nextLine();
            mag = line.split(sep);
            magazyn.add(new Kwiat(mag[0], Double.parseDouble(mag[1]), Integer.parseInt(mag[2]), new URL(mag[3])));
        }
    }
}
