import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;



public class Zamowienie extends JDialog {

    private final JPanel contentPanel = new JPanel();
    PanelProduktu pp;
    JSpinner spinner;

    public static void display(Glowna glowna){
        Zamowienie dialog = new Zamowienie(glowna);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - dialog.getWidth()) / 2;
        int iCoordY = (objDimension.height - dialog.getHeight()-40) / 2;
        dialog.setLocation(iCoordX, iCoordY);
        dialog.setVisible(true);
    }

    public Zamowienie (Glowna glowna) {
        this.setResizable(false);
        setBounds(100, 100, 650, 650);
        this.setTitle("Podsumowanie");
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.NORTH);
        {
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            contentPanel.add(panel);
            JLabel dane = new JLabel("Podaj dane adresowe");
            dane.setFont(new Font("Lucida Grande", Font.BOLD, 25));
            panel.add(dane);
            // obraz kwiatu
        }
        JPanel panelgrid = new JPanel(new GridLayout(1,2));
        getContentPane().add(panelgrid, BorderLayout.CENTER);

        JPanel panel1 = new JPanel(new GridLayout(7,1,30,30));
        panel1.setBorder(new EmptyBorder(15, 15, 15, 50));
        JPanel panel2 = new JPanel(new GridLayout(1,2));
        panelgrid.add(panel1);
        panelgrid.add(panel2);

        //imie
        JTextField imie = new JTextField("Imię");
        imie.setForeground(new Color(128, 128, 128));
        imie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(imie.getText().equals("Imię"))
                {
                    imie.setText("");
                    imie.setForeground(Color.BLACK);
                }
            }
        });

        imie.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel1.add(imie);

        //nazwisko
        JTextField nazwisko = new JTextField("Nazwisko");
        nazwisko.setForeground(new Color(128, 128, 128));
        nazwisko.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(nazwisko.getText().equals("Nazwisko"))
                {
                    nazwisko.setText("");
                    nazwisko.setForeground(Color.BLACK);
                }
            }
        });
        nazwisko.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel1.add(nazwisko);

        //mail
        JTextField mail = new JTextField("e-mail");
        mail.setForeground(new Color(128, 128, 128));
        mail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(mail.getText().equals("e-mail"))
                {
                    mail.setText("");
                    mail.setForeground(Color.BLACK);
                }
            }
        });

        mail.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel1.add(mail);
        //ulica
        JTextField ulica = new JTextField("Ulica");
        ulica.setForeground(new Color(128, 128, 128));
        ulica.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(ulica.getText().equals("Ulica"))
                {
                    ulica.setText("");
                    ulica.setForeground(Color.BLACK);
                }
            }

        });
        ulica.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel1.add(ulica);

        //nr ulicy
        JTextField nrul = new JTextField("Nr. ulicy");
        nrul.setForeground(new Color(128, 128, 128));
        nrul.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(nrul.getText().equals("Nr. ulicy"))
                {
                    nrul.setText("");
                    nrul.setForeground(Color.BLACK);
                }
            }

        });
        nrul.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel1.add(nrul);

        //kod pocztowy
        JTextField kod = new JTextField("Kod pocztowy");
        kod.setForeground(new Color(128, 128, 128));
        kod.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(kod.getText().equals("Kod pocztowy"))
                {
                    kod.setText("");
                    kod.setForeground(Color.BLACK);
                }
            }

        });
        kod.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel1.add(kod);

        //miejscowosc
        JTextField miasto = new JTextField("Miejscowość");
        miasto.setForeground(new Color(128, 128, 128));
        miasto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(miasto.getText().equals("Miejscowość"))
                {
                    miasto.setText("");
                    miasto.setForeground(Color.BLACK);
                }
            }

        });
        miasto.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel1.add(miasto);
        JDialog me = this;
        JButton zatwierdz = new JButton("Zatwierdź");
        zatwierdz.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Adres adres = new Adres(ulica.getText(),nrul.getText(),kod.getText(),miasto.getText());
                try {
                    new Klient(imie.getText(), nazwisko.getText(),mail.getText(), adres);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                me.dispose();
                PotwierdzenieZamowienia.display(glowna);
            }
        });
        JButton anuluj = new JButton("Anuluj");

        anuluj.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                me.dispose();
            }
        });
        JPanel paneldol = new JPanel();
        getContentPane().add(paneldol,BorderLayout.SOUTH);
        paneldol.setLayout(new FlowLayout(FlowLayout.RIGHT));
        paneldol.setBorder(new EmptyBorder(10, 10, 10, 10));
        paneldol.add(zatwierdz);
        paneldol.add(anuluj);



    }

}
