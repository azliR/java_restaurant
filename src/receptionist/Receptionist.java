package receptionist;

import javax.swing.UIManager;
import pages.MainPage;
import styles.Fonts;

/**
 *
 * @author a_lpha
 */
public class Receptionist {

    public static void main(String[] args) {
        Fonts.registerFont(Receptionist.class);
//        MainPage mainPage = new MainPage(2);
//        mainPage.setVisible(true);
//        mainPage.run();
        new MainPage(1).setVisible(true);
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
