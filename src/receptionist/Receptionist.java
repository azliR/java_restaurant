package receptionist;

import pages.LoginPage;
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
        new LoginPage().setVisible(true);
    }
}
