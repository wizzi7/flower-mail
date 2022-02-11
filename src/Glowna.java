import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class Glowna extends JFrame implements WindowListener
{
    private CardLayout layout ;
    private JPanel cards ;
    public Koszyk koszyk = new Koszyk();
    private JPanel panelGlowny;
    private Kwiaciarnia kwiaciarnia = new Kwiaciarnia();

    public void initComponents() throws IOException
    {

        panelGlowny = new JPanel(new BorderLayout(0, 0));
        panelGlowny.setBorder(new EmptyBorder(7,7,7,0));
        panelGlowny.setBackground(new Color(255,228,225));
        this.setTitle("Poczta Kwiatowa");
        // inicjalizacja kwiaciarni z pliku
        if(new File("magazyn.txt").length() == 0)
            this.kwiaciarnia.przyjmijDostawe("dostawa.csv", ",");
        else
            this.kwiaciarnia.odczytajZawartoscMagazynuZPliku("magazyn.txt", ",");

        // ustawienie głownego okna
        setSize(1000,700);
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - this.getWidth()) / 2;
        int iCoordY = (objDimension.height - this.getHeight()) / 2;
        this.setLocation(iCoordX, iCoordY);

        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(255,228,225));


        JPanel panel = new JPanel();
        panel.setBackground(new Color(255,228,225));
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);


        JButton button2 = new JButton("Zatwierdź bukiet");
        panel.add(button2);
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(!Kwiat.bukiet.isEmpty())
                {
                    koszyk.dodajBukiet(Kwiat.bukiet);
                    Kwiat.bukiet = new Bukiet();
                }
            }
        });
        // koszyk gorny prawy rog
        JButton button1 = new JButton("Koszyk");
        panel.add(button1);

        // panel do wyswietlania paneli z produktami
        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new GridLayout(0, 3, 3, 3));

        // inicjalizacja paneli produktow
        for(Kwiat kwiat : kwiaciarnia.magazyn)
        {
            scrollPanel.add(new PanelProduktu(kwiat));
        }
        // ustawienia scrolla
        JScrollPane scroll = new JScrollPane(scrollPanel);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        panelGlowny.add(scroll, BorderLayout.CENTER);
        panelGlowny.add(panel, BorderLayout.NORTH);


        layout = new CardLayout();
        cards = new JPanel();
        cards.setLayout(layout);
        cards.add(panelGlowny, "C1");
        KoszykWidok koszykWidok  = new KoszykWidok(this);
        cards.add(koszykWidok, "C2");
        add(cards, BorderLayout.CENTER);


        // koszyk prawy gorny rog
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                koszykWidok.initialize();
                layout.show(cards,"C2");
            }
        });
        setVisible(true);
    }

    public Glowna() throws IOException {
        addWindowListener(this);
        initComponents();
    }
    public void wrocDoListyProduktow()
    {
        this.panelGlowny.revalidate();
        this.panelGlowny.repaint();
        this.layout.show(cards,"C1");
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            this.kwiaciarnia.zapiszZawartoscMagazynuDoPliku("magazyn.txt", ",");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
