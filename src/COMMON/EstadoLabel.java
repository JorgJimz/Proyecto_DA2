package COMMON;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class EstadoLabel extends JLabel {

    public EstadoLabel() {
    }

    public EstadoLabel(String estado) {
        this.Colorear(estado);
    }

    public void Colorear(String estado) {
        Color color = null;

        switch (estado) {
            case "GENERADA":
                color = new Color(15, 157, 88);
                break;
            case "PROCESADA":
                color = new Color(244, 180, 0);
                break;
            case "ANULADA":
                color = new Color(219, 68, 55);
                break;
            case "ENTREGADA":
                color = new Color(66, 133, 244);
                break;
            default:
                color = Color.BLACK;
        }
        setBackground(color);
        setFont(new Font("Century Gothic", Font.BOLD, 14));
        setForeground(Color.WHITE);
        setOpaque(true);
        setText(estado);
    }

}
