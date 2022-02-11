import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



public class OknoProduktu extends JDialog {

    private final JPanel contentPanel = new JPanel();
    PanelProduktu pp;
    JSpinner spinner;

    public static void display(Kwiat p,PanelProduktu pp){
        OknoProduktu dialog = new OknoProduktu(p, pp);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - dialog.getWidth()) / 2;
        int iCoordY = (objDimension.height - dialog.getHeight()-100) / 2;
        dialog.setLocation(iCoordX, iCoordY);
        dialog.setTitle(p.getNazwaKwiatu());
        dialog.setVisible(true);
    }


    public OknoProduktu(Kwiat p,PanelProduktu pp) {
        this.pp = pp;

        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.NORTH);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(255,228,225));
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            contentPanel.add(panel);
            // obraz kwiatu
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            {
                JLabel lblNewLabel3 = new JLabel(new ImageIcon(p.getImageToOknoProduktu()));
                panel.add(lblNewLabel3);
            }
        }
        {
            contentPanel.setBackground(new Color(255,228,225));
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            {
                JPanel panel1 = new JPanel();
                panel.add(panel1);
                panel1.setBorder(null);
                panel1.setBackground(new Color(255,228,225));
                FlowLayout flpanel1 = (FlowLayout) panel1.getLayout();
                flpanel1.setAlignment(FlowLayout.LEFT);
                {
                    JLabel lblNewLabel1 = new JLabel(p.getNazwaKwiatu());
                    lblNewLabel1.setBackground(new Color(255,228,225));
                    panel1.add(lblNewLabel1);
                    lblNewLabel1.setHorizontalAlignment(SwingConstants.LEFT);
                    lblNewLabel1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
                }
            }
            {
                // panel obok obrazka
                JPanel panel1 = new JPanel();
                panel.setBackground(new Color(255,228,225));
                panel.add(panel1);
                FlowLayout flpanel1 = (FlowLayout) panel1.getLayout();
                flpanel1.setAlignment(FlowLayout.LEFT);
                panel1.setBackground(new Color(255,228,225));
                panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
                {
                    JPanel panel2 = new JPanel();
                    panel1.add(panel2);
                    panel2.setBackground(new Color(255,228,225));
                    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
                    JLabel lblNewLabel2 = new JLabel("Liczba sztuk na magazynie: " + p.getLiczbaSztukNaMagazynie() );
                    lblNewLabel2.setBackground(new Color(255,228,225));
                    panel2.add(lblNewLabel2);
                    lblNewLabel2.setHorizontalAlignment(SwingConstants.LEFT);
                    lblNewLabel2.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
                    if(p.getLiczbaSztukNaMagazynie() == 0)
                    {
                        JLabel lblNewLabel3 = new JLabel("Brak sztuk na magazynie!");
                        lblNewLabel3.setBackground(new Color(255,228,225));
                        panel2.add(lblNewLabel3);
                        lblNewLabel3.setHorizontalAlignment(SwingConstants.LEFT);
                        lblNewLabel3.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
                        lblNewLabel3.setForeground(Color.RED);
                    }
                }
            }
        }
        {
            getContentPane().setBackground(new Color(255,228,225));
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(new Color(255,228,225));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JLabel lblNewLabel = new JLabel("Liczba sztuk:");
                lblNewLabel.setBackground(new Color(255,228,225));
                buttonPane.add(lblNewLabel);
            }
            {
                spinner = new JSpinner();
                spinner.setModel(new SpinnerNumberModel(1, 0, p.getLiczbaSztukNaMagazynie(), 1));
                spinner.setPreferredSize(new Dimension(100, 30));
                ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);
                buttonPane.add(spinner);
            }
            {
                final JDialog me = this;
                JButton okButton = new JButton("Dodaj do koszyka");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                okButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        if(Integer.parseInt(spinner.getValue() + "") > 0) {
                            Kwiat.bukiet.dodajKwiat(p, Integer.parseInt(spinner.getValue() + ""));
                            p.setLiczbaSztukNaMagazynie(p.getLiczbaSztukNaMagazynie() - Integer.parseInt(spinner.getValue() + ""));
                            pp.ustawIlosc();
                            me.dispose();
                        }
                    }
                });
                getRootPane().setDefaultButton(okButton);
            }
            {
                final JDialog me = this;
                JButton cancelButton = new JButton("Wyjdź");
                cancelButton.setActionCommand("Wyjdź");
                cancelButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        me.dispose();
                    }
                });
                buttonPane.add(cancelButton);
            }
        }
    }
}