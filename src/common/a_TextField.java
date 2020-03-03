package common;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import styles.Colors;
import styles.Fonts;

/**
 *
 * @author a_lpha
 */
public class a_TextField extends JTextField {

    private final Color focusGainedColor = Colors.accentColor;
    private final Color focusLostColor = Colors.borderColor;
    private final Color focusLostTextColor = new Color(128, 134, 139);
    private final int borderRadius = 8;
    private final int inset[] = {2, 8, 2, 8};

    public a_TextField() {
        super();
        setFont(Fonts.ROBOTO_REGULAR.deriveFont(16f));
        setForeground(new Color(0, 0, 0));
        setCaretColor(new Color(0, 0, 0));
        addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent fe) {
                borderFocusGained("");
            }

            @Override
            public void focusLost(FocusEvent fe) {
                borderFocusLost("");
            }
        });
    }

    public a_TextField(String name) {
        super();
        borderFocusLost(name);
        setFont(new Font("Roboto", 0, 16));
        setForeground(new Color(0, 0, 0));
        setCaretColor(new Color(0, 0, 0));
        addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent fe) {
                borderFocusGained(name);
            }

            @Override
            public void focusLost(FocusEvent fe) {
                borderFocusLost(name);
            }
        });
    }

    private void borderFocusGained(String name) {
        setBorder(BorderFactory.createTitledBorder(new RoundedBorder(
                borderRadius, inset, focusGainedColor),
                name, TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, Fonts.ROBOTO_REGULAR.deriveFont(
                        12f), focusGainedColor));
    }

    private void borderFocusLost(String name) {
        setBorder(BorderFactory.createTitledBorder(new RoundedBorder(
                borderRadius, inset, focusLostColor),
                name, TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, Fonts.ROBOTO_REGULAR.deriveFont(
                        12f), focusLostTextColor));
    }
}
