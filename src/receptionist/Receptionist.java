package receptionist;

import pages.MainPage;

/**
 *
 * @author a_lpha
 */
public class Receptionist {

    public static void main(String[] args) {
        MainPage mainPage = new MainPage(2);
        mainPage.setVisible(true);
        mainPage.run();
    }
}
