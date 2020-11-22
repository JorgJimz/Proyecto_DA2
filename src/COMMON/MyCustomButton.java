package COMMON;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MyCustomButton extends JButton {

    public MyCustomButton(String url, String text, boolean alt) {
        super(text, new ImageIcon(url));        
        setBorder(null);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setFocusable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setFont(new Font("Century Gothic", Font.BOLD, 12));
        setForeground(alt? Color.WHITE : Color.BLACK);
    }
}
