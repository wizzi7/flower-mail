import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OknoBrakTowaru extends JDialog {

    private final JPanel contentPanel = new JPanel();

    public static void display(Kwiat p){
        OknoBrakTowaru dialog = new OknoBrakTowaru(p);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - dialog.getWidth()) / 2;
        int iCoordY = (objDimension.height - dialog.getHeight()-100) / 2;
        dialog.setLocation(iCoordX, iCoordY);
        dialog.setVisible(true);
    }

    public OknoBrakTowaru(Kwiat p)
    {
        setBounds(100, 100, 480, 100);
        JPanel panel = new JPanel(new FlowLayout());

        JLabel et = new JLabel("Aktualnie nie posiadamy kwiatu " + p.getNazwaKwiatu() + "\n w magazynie, przepraszamy" );
        panel.add(et);

        JButton ok = new JButton("OK");
        ok.setSize(50,20);
        panel.add(ok);
        getContentPane().add(panel);
        JDialog me = this;
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                me.dispose();
            }
        });
    }
}