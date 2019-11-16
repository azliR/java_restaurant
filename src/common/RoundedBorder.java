package common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.Border;
import styles.Colors;

/**
 *
 * @author a_lpha
 */
public class RoundedBorder implements Border {

    private final int borderRadius;
    private int[] borderInsets = {0, 0, 0, 0};
    private final Color borderColor;

    public RoundedBorder(int borderRadius, int[] insets, Color borderColor) {
        super();
        this.borderRadius = borderRadius;
        this.borderInsets = insets;
        this.borderColor = borderColor;
    }

    public RoundedBorder(int borderRadius) {
        super();
        this.borderRadius = borderRadius;
        this.borderColor = Colors.borderColor;
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(borderInsets[0], borderInsets[1], borderInsets[2], borderInsets[3]);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Dimension arcs = new Dimension(borderRadius, borderRadius);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(borderColor);
        graphics.drawRoundRect(x, y, width - 1, height - 1, arcs.width, arcs.height);
    }
}
