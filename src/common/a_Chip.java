package common;

import java.awt.Color;
import java.awt.Cursor;
import styles.Colors;
import styles.Fonts;

/**
 *
 * @author a_lpha
 */
public class a_Chip extends RoundedButton {

    private final Color activeTextColor = Colors.accentColor;
    private final Color activeBackgroundColor = Colors.blueBackgroundColor;
    private final Color inactiveTextColor = Colors.greyTextColor;
    private final Color inactiveBackgroundColor = Colors.primaryColor;
    private final int[] insets = {9, 12, 9, 12};

    public a_Chip(String text) {
        super(32);
        setFont(Fonts.ROBOTO_MEDIUM.deriveFont(10f));
        setBackground(isSelected() ? activeBackgroundColor : inactiveBackgroundColor);
        setForeground(isSelected() ? activeTextColor : inactiveTextColor);
        setText(text.toUpperCase());
        setBorder(new RoundedBorder(32, insets, isSelected() ? activeBackgroundColor : Colors.borderColor));
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);
    }
}
