package lab2_1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JPanel {
    private static Color[] colors = {
            new Color(30, 168, 37),
            new Color(36, 173, 43),
            new Color(42, 176, 49),
            new Color(51, 181, 58),
            new Color(61, 184, 67),
            new Color(70, 184, 75),
            new Color(70, 174, 75)
    };
    private static BufferedImage imgBear;
    private static BufferedImage imgBee;
    private static BufferedImage imgRIP;
    private static final int widthField = 20;
    private static final int heightField = 14;
    public static final int sizeOfPoint = 50;
    private static final int numberOfBees = 3;
    private Bear bear;
    private Bee[] bees;
    private Field field;
    static {
        try {
            imgBear = ImageIO.read(new File("C://Users//Comfy//IdeaProjects//lab2//src//res//bear.jpg"));
            imgBee = ImageIO.read(new File("C://Users//Comfy//IdeaProjects//lab2//src//res//bee.jpg"));
            imgRIP = ImageIO.read(new File("C://Users//Comfy//IdeaProjects//lab2//src//res//rip.jpg"));
        } catch (IOException e) {
        }
    }
    public void start() {
        Bag bag = new Bag(heightField);
        field = new Field(heightField, widthField);
        bear = new Bear(field);
        bees = new Bee[numberOfBees];
        for (int i = 0; i < numberOfBees; i++) {
            bees[i] = new Bee(field, bear, bag);
        }

        bear.start();
        for (Bee bee : bees) {
            bee.start();
        }
        JFrame f = new JFrame();
        f.add(this);
        f.setSize(widthField*sizeOfPoint+10, heightField*sizeOfPoint+35);
        f.setVisible(true);
    }

    public void paint(Graphics g) {

        super.paint(g);
        drawField(g);
        for (Bee bee : bees) {
            g.drawImage(imgBee, bee.getX() * sizeOfPoint, bee.getRowForSearching() * sizeOfPoint, sizeOfPoint, sizeOfPoint, null);
        }
        if (!bear.isNotDead){
            g.drawImage(imgRIP,  bear.getX()* sizeOfPoint, bear.getY() * sizeOfPoint, sizeOfPoint, sizeOfPoint, null);
            return;
        }
        g.drawImage(imgBear,  bear.getX()* sizeOfPoint, bear.getY() * sizeOfPoint, sizeOfPoint, sizeOfPoint, null);
        repaint();

    }

    public void drawField(Graphics g){
        for (int i = 0; i < heightField; i++) {
            for (int j = 0; j < widthField; j++) {
                g.setColor(colors[(i*j)%colors.length]);
                g.fillRect(j*sizeOfPoint, i*sizeOfPoint, sizeOfPoint, sizeOfPoint);
            }
        }
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
