package COMMON;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JIconTextField extends JTextField {

    private Icon icon;
    private int orientation;

    public JIconTextField() {
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        this.icon = null;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return this.icon;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon != null) {
            if (orientation == SwingConstants.RIGHT) {
                icon.paintIcon(this, g, this.getWidth() - 160, this.getHeight() / 4);
            } else {
                icon.paintIcon(this, g, this.getWidth() - 30, this.getHeight() / 8);
            }
        }
    }
}
