import Helper.Panel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

public class ImageExport {
    private static BufferedImage exportAsImage(Panel panel) {
        int width = panel.getPreferredSize().width;
        int height = panel.getPreferredSize().height;
        panel.setSize(width, height);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = image.createGraphics();
        panel.paint(g2d);
        g2d.dispose();

        return image;
    }

    public static void exportToFile(Panel panel, String filename) throws IOException {
        BufferedImage image = exportAsImage(panel);
        ImageIO.write(image, "PNG", new File(filename));
    }
}