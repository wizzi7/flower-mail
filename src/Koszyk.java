import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Koszyk
{
    private double wartoscKoszyka;
    public List<Bukiet> bukiety = new LinkedList<>();
    Koszyk(){}
    public void dodajBukiet(Bukiet bukiet)
    {
        bukiety.add(bukiet);
        this.wartoscKoszyka += bukiet.getCenaZaBukiet();
    }
    public void usunBukiet(Bukiet bukiet)
    {
        bukiety.remove(bukiet);
        this.wartoscKoszyka -= bukiet.getCenaZaBukiet();
        for(Kwiat kwiat: bukiet.kwiatyWBukiecie.keySet()) {
            int ile = bukiet.kwiatyWBukiecie.get(kwiat);
            kwiat.setLiczbaSztukNaMagazynie(kwiat.getLiczbaSztukNaMagazynie() + ile);
        }
    }
    public double getWartoscKoszyka()
    {
        return this.wartoscKoszyka;
    }
    public String wypiszZawartoscKoszyka()
    {
        String wyjscie = "";
        for(Bukiet item : bukiety)
        {
            wyjscie += item.wypiszBukiet();
        }
        return "***KOSZYK***\n" + wyjscie;
    }
    public void wyczyscKoszyk()
    {
        for(Bukiet bukiet: this.bukiety)
        {
            this.usunBukiet(bukiet);
        }
        this.bukiety = new LinkedList<>();
        this.wartoscKoszyka = 0;
    }
}
