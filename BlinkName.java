import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BlinkName {

    public static final String NAME = "Jorge Sarricolea";
    public static final int TEXT_SIZE = 72;
    public static final int COLOR_BLINK = 80; // Milliseconds

    public static void main(String[] args) {
        JFrame frame = new JFrame("Blink Name");
        JLabel label = new JLabel(NAME);
        label.setFont(new Font("Arial", Font.PLAIN, TEXT_SIZE));
        frame.getContentPane().add(label);
        frame.setSize(800, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        ColorChanger colorChanger = new ColorChanger(label);
        new Thread(colorChanger).start();
    }
}

class ColorChanger implements Runnable {

    private static final Color[] COLORS = {
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.MAGENTA,
            Color.YELLOW
    };

    private JLabel label;

    public ColorChanger(JLabel label) {
        this.label = label;
    }

    @Override
    public void run() {
        int colorIndex = 0;

        while (true) {
            try {
                Thread.sleep(BlinkName.COLOR_BLINK);

                label.setForeground(COLORS[colorIndex]);

                colorIndex++;
                if (colorIndex == COLORS.length) {
                    colorIndex = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
