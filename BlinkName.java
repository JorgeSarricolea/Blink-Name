import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.util.HashMap;
import java.util.Map;

public class BlinkName {

    public static final String NAME = "Jorge Sarricolea Veyro";
    public static final int TEXT_SIZE = 72;
    public static final int COLOR_BLINK = 80; // Milliseconds

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // GUI setup in the main thread
        JFrame frame = new JFrame("Blink Name");
        JLabel label = new JLabel(NAME);
        label.setFont(new Font("Arial", Font.PLAIN, TEXT_SIZE));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(label);
        frame.setSize(1000, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // ColorChanger thread is started to change colors
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

    private static final Map<Color, String> COLOR_NAMES = new HashMap<>();

    static {
        // Mapping colors to their names
        COLOR_NAMES.put(Color.RED, "RED");
        COLOR_NAMES.put(Color.GREEN, "GREEN");
        COLOR_NAMES.put(Color.BLUE, "BLUE");
        COLOR_NAMES.put(Color.MAGENTA, "MAGENTA");
        COLOR_NAMES.put(Color.YELLOW, "YELLOW");
    }

    private JLabel label;
    private int[] colorIndex = { 0 }; // Use an array to store the index

    public ColorChanger(JLabel label) {
        this.label = label;
    }

    @Override
    public void run() {
        // Thread begins here
        while (true) {
            try {
                // Get the name of the current thread
                String threadName = Thread.currentThread().getName();

                // Get current color
                Color currentColor = COLORS[colorIndex[0]];

                // Get color name
                String colorName = COLOR_NAMES.get(currentColor);

                // Print information about the thread in the terminal
                System.out.println("Current Thread: " + threadName + " | Current Color: " + colorName);

                // The thread is paused to change color at regular intervals
                Thread.sleep(BlinkName.COLOR_BLINK);

                // The user interface is updated
                SwingUtilities.invokeLater(() -> {
                    if (label != null) {
                        label.setForeground(currentColor);
                        label.repaint();
                    }
                });

                // Changes to the next color
                colorIndex[0]++;
                if (colorIndex[0] == COLORS.length) {
                    colorIndex[0] = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/* Jorge Sarricolea Veyro
 * jjorgesarricole18@gmail.com
 * 17/nov/2023
 */
