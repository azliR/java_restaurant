package common;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
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

        if (null == dialogType) {
            return;
        } else {
            switch (dialogType) {
                case EMPTY_FIELD:
                    title = "Masukan tidak valid";
                    message = "Harap isi semua kolom yang dibutuhkan";
                    break;

                case INSERT_ERROR:
                    title = "Terjadi kesalahan";
                    message = "Kesalahan saat menyimpan data ke database";
                    break;

                case INSERT_SUCCESS:
                    title = "Berhasil";
                    message = "Data berhasil disimpan!";
                    break;

                default:
                    title = "Terjadi kesalahan";
                    message = "Terjadi kesalahan saat memunculkan dialog ini. Periksa log segera!";
            }
        }
        JOptionPane.showMessageDialog(MainPage.parent, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();

        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();

        return bufferedImage;
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

    public static BufferedImage convertRoundedImage(BufferedImage image, int borderRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
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

    public static BufferedImage toBufferedImage(Image img, Rectangle rectangle) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        int imageWidth = bimage.getWidth();
        int imageHeight = bimage.getHeight();
        int cropWidth = rectangle.width;
        int cropHeight = rectangle.height;

        return bimage.getSubimage((imageWidth - cropWidth) / 2, (imageHeight - cropHeight) / 2, cropWidth, cropHeight);
    }
}
