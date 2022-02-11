import java.util.HashMap;
import java.util.Map;


public class Bukiet
{
    private double cenaZaBukiet;
    /**
     * mapa reprezentujaca nazwe kwiatu i ilosc sztuk dostepnych na magazynie
     */
    public Map<Kwiat, Integer> kwiatyWBukiecie = new HashMap<>();

    public Bukiet() {}

    public void dodajKwiat(Kwiat kwiat, int liczbaSztuk)
    {
        if(!kwiatyWBukiecie.containsKey(kwiat))
        {
            this.kwiatyWBukiecie.put(kwiat, liczbaSztuk);
            this.cenaZaBukiet += kwiat.getCenaZaSztuke() * liczbaSztuk;
        }
        else
        {
            int liczba = this.kwiatyWBukiecie.get(kwiat);
            this.kwiatyWBukiecie.put(kwiat, liczbaSztuk + liczba);
            this.cenaZaBukiet += kwiat.getCenaZaSztuke() * liczbaSztuk;
        }

    }
    public void usunKwiat(Kwiat kwiat, int liczbaSztuk)
    {
        if(kwiatyWBukiecie.get(kwiat) > liczbaSztuk)
        {
            int value = kwiatyWBukiecie.get(kwiat) - liczbaSztuk;
            kwiatyWBukiecie.put(kwiat, value);
        }
        else kwiatyWBukiecie.remove(kwiat);
    }
    public boolean isEmpty()
    {
        return this.kwiatyWBukiecie.isEmpty();
    }
    public double getCenaZaBukiet()
    {
        return this.cenaZaBukiet;
    }

    /**
     * wypisuje kwiaty oraz ich ilosc znajdujace sie w bukiecie
     */
    public String wypiszBukiet()
    {
        String wyjscie="";
        for(Kwiat key : kwiatyWBukiecie.keySet())
        {
            int value = kwiatyWBukiecie.get(key);
            wyjscie += value + "x " + key.getNazwaKwiatu() + " \n";
        }
        return "Bukiet zawierajacy:   \n" + wyjscie;
    }
}
