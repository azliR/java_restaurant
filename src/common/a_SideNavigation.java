package common;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JRadioButton;
import styles.Colors;

/**
 *
 * @author a_lpha
 */
public class a_SideNavigation extends JRadioButton {

    private int borderRadius = 15;

    public a_SideNavigation(int borderRadius) {
        super();
        this.borderRadius = borderRadius;
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!isSelected()) {
                    setBackground(new java.awt.Color(245, 245, 245));
                    setBorder(BorderFactory.createMatteBorder(0, 36, 0, 0, new java.awt.Color(245, 245, 245)));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!isSelected()) {
                    setBackground(new java.awt.Color(255, 255, 255));
                    setBorder(BorderFactory.createMatteBorder(0, 36, 0, 0, Colors.primaryColor));
                }
            }
        });
    }

    @Override
    public void setContentAreaFilled(boolean b) {
        super.setContentAreaFilled(false);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension arcs = new Dimension(borderRadius, borderRadius);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        super.paintComponent(g);
    }
}
