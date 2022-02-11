import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PanelProduktu extends JPanel {

    private JLabel sztuki;
    private Kwiat kwiat;
    public PanelProduktu(Kwiat p)
    {
        this.kwiat = p;
        // ustawienia głownego panela
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255,228,225));

        // panel na nazwe produktu
        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel title = new JLabel(p.getNazwaKwiatu());
        title.setBackground(new Color(255,228,225));
        title.setOpaque(true);
        panel.add(title);
        title.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // panel na obrazek
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createLineBorder(new Color(255,228,225),5));
        add(panel2);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        JLabel imgLabel = new JLabel(new ImageIcon(p.getImage()));
        panel2.add(imgLabel);

        //etykieta pod obrazkiem - cena i liczba sztuk
        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createLineBorder(new Color(255,228,225),5));
        add(panel3);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        JLabel cena = new JLabel("Cena: " + p.getCenaZaSztuke()+ "zł");
        cena.setBackground(new Color(255,228,225));

        cena.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        sztuki = new JLabel("  Liczba dostępnych sztuk: " +  p.getLiczbaSztukNaMagazynie());
        sztuki.setBackground(new Color(255,228,225));
        sztuki.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        cena.setOpaque(true);
        sztuki.setOpaque(true);
        panel3.add(cena);
        panel3.add(sztuki);


        // panel na dwa buttony na dole
        JPanel panel1 = new JPanel();
        add(panel1);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

        JButton view = new JButton("Zobacz więcej");
        panel1.add(view);
        view.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(p.getLiczbaSztukNaMagazynie() != 0)
                {
                    OknoProduktu.display(p,getInstance() );
                }
                else
                {
                    OknoBrakTowaru.display(p);
                }

            }
        });

        JButton addOne = new JButton("Dodaj 1 do bukietu");
        panel1.add(addOne);
        addOne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(p.getLiczbaSztukNaMagazynie() > 0)
                {
                    Kwiat.bukiet.dodajKwiat(p,1);
                    p.setLiczbaSztukNaMagazynie(p.getLiczbaSztukNaMagazynie() - 1);
                    sztuki.setText("  Liczba dostępnych sztuk: " + p.getLiczbaSztukNaMagazynie());
                }

            }
        });
    }
    public PanelProduktu getInstance()
    {
        return this;
    }
    public void ustawIlosc()
    {
        this.sztuki.setText("  Liczba dostępnych sztuk: " + (this.kwiat.getLiczbaSztukNaMagazynie()));
    }

}