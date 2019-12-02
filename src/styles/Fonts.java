package styles;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rizal
 */
public class Fonts {

    public static Font GOOGLE_SANS;
    public static Font PRODUCT_SANS_REGULAR;
    public static Font ROBOTO_MEDIUM;

    public static void registerFont(Class c) {
        try {
            GOOGLE_SANS = Font.createFont(Font.TRUETYPE_FONT, c.getResourceAsStream("/fonts/GoogleSans-Regular.ttf"));
            PRODUCT_SANS_REGULAR = Font.createFont(Font.TRUETYPE_FONT, c.getResourceAsStream("/fonts/ProductSans-Medium.ttf"));
            ROBOTO_MEDIUM = Font.createFont(Font.TRUETYPE_FONT, c.getResourceAsStream("/fonts/Roboto-Medium.ttf"));

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(GOOGLE_SANS);
            ge.registerFont(PRODUCT_SANS_REGULAR);
            ge.registerFont(ROBOTO_MEDIUM);

        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Fonts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
