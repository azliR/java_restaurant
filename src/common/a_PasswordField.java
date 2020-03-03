package common;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import styles.Colors;
import styles.Fonts;

/**
 *
 * @author a_lpha
 */
public class a_PasswordField extends JPasswordField {

    private final Color focusGainedColor = Colors.accentColor;
    private final Color focusLostColor = Colors.borderColor;
    private final Color focusLostTextColor = new Color(128, 134, 139);
    private final int borderRadius = 8;
    private final int inset[] = {2, 8, 2, 8};

    public a_PasswordField(String name) {
        super();
        changeBorderColor(name, focusLostColor, focusLostTextColor);
        setFont(new Font("Times New Roman", 0, 16));
        setForeground(new Color(0, 0, 0));
        setCaretColor(new Color(0, 0, 0));
        addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent fe) {
                changeBorderColor(name, focusGainedColor, focusGainedColor);
            }

            @Override
            public void focusLost(FocusEvent fe) {
                changeBorderColor(name, focusLostColor, focusLostTextColor);
            }
        });
    }

    private void changeBorderColor(String name, Color borderColor,
            Color textColor) {
        setBorder(BorderFactory.createTitledBorder(new RoundedBorder(
                borderRadius, inset, borderColor),
                name, TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, Fonts.ROBOTO_REGULAR.deriveFont(
                        12f), textColor));
    }
}
