package common;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import pages.MainPage;

/**
 *
 * @author a_lpha
 */
public class a_ {

    public static enum DialogType {
        EMPTY_FIELD, INSERT_ERROR, INSERT_SUCCESS
    }

    public static void showDialog(DialogType dialogType) {
        String title;
        String message;
        int icon;

        if (null == dialogType) {
            return;
        } else {
            switch (dialogType) {
                case EMPTY_FIELD:
                    title = "Masukan tidak valid";
                    message = "Harap isi semua kolom yang dibutuhkan";
                    icon = JOptionPane.ERROR_MESSAGE;
                    break;

                case INSERT_ERROR:
                    title = "Terjadi kesalahan";
                    message = "Kesalahan saat menyimpan data ke database";
                    icon = JOptionPane.ERROR_MESSAGE;
                    break;

                case INSERT_SUCCESS:
                    title = "Berhasil";
                    message = "Data berhasil disimpan!";
                    icon = JOptionPane.INFORMATION_MESSAGE;
                    break;

                default:
                    title = "Terjadi kesalahan";
                    message = "Terjadi kesalahan saat memunculkan dialog ini. Periksa log segera!";
                    icon = JOptionPane.ERROR_MESSAGE;
            }
        }
        JOptionPane.showMessageDialog(MainPage.parent, message, title, icon);
    }

    public static String convertCurrency(int currency) {
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();

        dfs.setCurrencySymbol("Rp. ");
        dfs.setMonetaryDecimalSeparator(',');
        dfs.setGroupingSeparator('.');

        df.setDecimalFormatSymbols(dfs);

        return df.format(currency);
    }

    public static String convertTimestamp(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(dateString);
        Date currentDate = new Date();

        long minutes = 1000 * 60;
        long different = currentDate.getTime() - date.getTime();
        long elapsedMinutes = different / minutes;

        if (elapsedMinutes == 0) {
            return "Baru saja";

        } else if (elapsedMinutes < 60) {
            return elapsedMinutes + " menit lalu";

        } else {
            long hours = minutes * 60;
            long elapsedHour = different / hours;

            if (elapsedHour < 24) {
                return elapsedHour + " jam lalu";
            } else {
                long days = hours * 24;
                long elapsedDay = different / days;

                if (elapsedDay < 7) {
                    return elapsedDay + " hari lalu";
                } else {
                    long weeks = days * 7;
                    long elapsedWeek = different / weeks;

                    if (elapsedDay < 30) {
                        return elapsedWeek + " minggu lalu";

                    } else {
                        long months = days * 30;
                        long elapsedMonth = different / months;

                        if (elapsedMonth < 12) {
                            return elapsedMonth + " bulan lalu";
                        } else {
                            long years = days * 365;
                            long elapsedYear = different / years;

                            return elapsedYear + " tahun lalu";
                        }
                    }
                }
            }
        }
    }

    public static int extractNumber(String s) {
        return Integer.parseInt(s.replaceAll("\\D+", ""));
    }

    public static BufferedImage convertRoundedImage(Image image, int borderRadius) {
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, borderRadius, borderRadius));

        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }

    public static BufferedImage cropImage(final BufferedImage image, int maxImageWidth, int maxImageHeight) {
        int x = 0;
        int y = 0;
        int width = image.getWidth();
        int height = image.getHeight();

        if (width > maxImageWidth) {
            x = (width - maxImageWidth) / 2;

        }
        if (height > maxImageHeight) {
            y = (height - maxImageHeight) / 2;

        }

        return image.getSubimage(x, y, maxImageWidth, maxImageHeight);
    }

    public static Image scaleImage(Image image, int maxImageWidth, int maxImageHeight) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        double currentRatio = (double) width / height;
        double aspectRatio = (double) maxImageWidth / maxImageHeight;

        if (currentRatio < aspectRatio) {
            height = (int) (height * ((double) maxImageWidth / width));
            width = maxImageWidth;
        } else {
            width = (int) (width / ((double) height / maxImageHeight));
            height = maxImageHeight;
        }
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public static BufferedImage toBufferedImage(Image img) {
        BufferedImage buffImage = new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_RGB);

        buffImage.getGraphics().drawImage(img, 0, 0, null);

        return buffImage;
    }
}
