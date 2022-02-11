import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KoszykWidok extends JPanel
{
    private JPanel scrollPanel;
    private JButton btnClear, btnCheckout;
    private JLabel lblNetTotal;
    private Koszyk koszyk;
    private JLabel lblTotal;

    public KoszykWidok(Glowna glowna) {
        this.koszyk = glowna.koszyk;
        setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(panel, BorderLayout.NORTH);

        JButton btnBack = new JButton("Powrót");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                glowna.wrocDoListyProduktow();
            }
        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(btnBack);

        btnClear = new JButton("Usuń całą zawartość koszyka");
        panel.add(btnClear);

        Component horizontalGlue = Box.createHorizontalGlue();
        panel.add(horizontalGlue);

        lblTotal = new JLabel("Razem: " + this.koszyk.getWartoscKoszyka() + "zł  ");
        panel.add(lblTotal);

        lblNetTotal = new JLabel();
        panel.add(lblNetTotal);

        btnCheckout = new JButton("Zapłać");
        panel.add(btnCheckout);
        btnCheckout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Zamowienie.display(glowna);
            }
        });

        scrollPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(scrollPanel);
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(scroll, BorderLayout.CENTER);

        this.setVisible(true);
    }
    public void update()
    {
        this.initialize();
        this.revalidate();
        this.repaint();
    }
    public void initialize() {
        scrollPanel.removeAll();
        lblTotal.setText("Razem: " + this.koszyk.getWartoscKoszyka() + "zł  ");
        for (Bukiet bukiet : this.koszyk.bukiety) {
            JPanel itemPanel = new JPanel();
            itemPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            itemPanel.setAlignmentX(LEFT_ALIGNMENT);
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
            JPanel titlePanel = new JPanel();
            itemPanel.add(titlePanel);
            titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

            Component horizontalGlue_1 = Box.createHorizontalGlue();
            titlePanel.add(horizontalGlue_1);
            JPanel propertiesPanel = new JPanel();
            itemPanel.add(propertiesPanel);
            propertiesPanel.setLayout(new BoxLayout(propertiesPanel, BoxLayout.X_AXIS));
            JPanel quantityPanel = new JPanel();
            propertiesPanel.add(quantityPanel);
            quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.X_AXIS));
            JLabel lblQuantuty = new JLabel( bukiet.wypiszBukiet());
            quantityPanel.add(lblQuantuty);
            JButton btnRemove = new JButton("Usuń");
            btnRemove.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    koszyk.usunBukiet(bukiet);
                    update();
                }
            });
            propertiesPanel.add(btnRemove);

            JLabel spacer_1 = new JLabel("          ");
            quantityPanel.add(spacer_1);
            JLabel lblNewLabel = new JLabel("Cena:   " + bukiet.getCenaZaBukiet() + "zl  ");
            quantityPanel.add(lblNewLabel);

            Component horizontalGlue = Box.createHorizontalGlue();
            propertiesPanel.add(horizontalGlue);
            JSeparator separator = new JSeparator();
            itemPanel.add(separator);
            scrollPanel.add(itemPanel);
        }
        btnClear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                koszyk.wyczyscKoszyk();
                update();
            }
        });
    }
}
