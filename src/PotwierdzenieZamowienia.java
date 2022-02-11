import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PotwierdzenieZamowienia extends JDialog {

    private final JPanel contentPanel = new JPanel();

    public static void display(Glowna glowna){
        PotwierdzenieZamowienia dialog = new PotwierdzenieZamowienia(glowna);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - dialog.getWidth()) / 2;
        int iCoordY = (objDimension.height - dialog.getHeight()-100) / 2;
        dialog.setLocation(iCoordX, iCoordY);
        dialog.setTitle("Potwierdzenie zamówienia");
        dialog.setVisible(true);
    }


    public PotwierdzenieZamowienia(Glowna glowna) {
        setBounds(100, 100, 400, 150);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout());
        JLabel napis = new JLabel(
                String.format("<html><body style=\"text-align: justify;  text-justify: inter-word;\"" +
                        ">%s</body></html>","Dziękujemy za zakupy, szczegóły zamówienia zostaną przesłane na adres e-mail :)"));
        napis.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        contentPanel.add(napis,BorderLayout.CENTER);

        JPanel paneldol = new JPanel(new GridLayout(1,3));
        JButton ok = new JButton("OK");
        JPanel pusty= new JPanel();
        paneldol.add(pusty);
        paneldol.add(ok);
        contentPanel.add(paneldol, BorderLayout.SOUTH);
        JDialog me = this;
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                me.dispose();
                glowna.wrocDoListyProduktow();
                glowna.koszyk.wyczyscKoszyk();
            }
        });
    }
}