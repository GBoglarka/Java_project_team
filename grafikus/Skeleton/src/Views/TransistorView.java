package Views;

import javax.swing.*;
import java.awt.*;

public class TransistorView extends ObjectView{

    /**
     * TransistorView osztály konstruktora
     * @param image Ezzel hívja meg az ObjectView konstruktorát
     */
    public TransistorView(String image){
        super(image);
    }

    /**
     * Kirajzolja a tranzisztort, és Active felirattal jelzi, ha éppen aktív
     * @param inventory Az inventoryt ábrázoló JPanel
     */
    @Override
    public void Draw(JPanel inventory){
        JLabel object =new JLabel(new ImageIcon("./"+ GetImage()));
        JLabel fake =new JLabel("Active");
        fake.setFont(new Font("Tahoma", Font.BOLD, 20));
        fake.setForeground(new Color(238, 238, 238));
        fake.setHorizontalAlignment(SwingConstants.CENTER);
        fake.setVerticalAlignment(SwingConstants.CENTER);

        inventory.setLayout(new BorderLayout());
        inventory.add(object, BorderLayout.CENTER);
        if(GetPair().IsUsed()){
            object.setLayout(new BorderLayout());
            object.add(fake, BorderLayout.CENTER);
        }
    }

}
