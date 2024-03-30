import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BubbleSortVisualization extends JPanel {
    private static final int SIZE = 200;
    private static final int GAP = 4;
    private static final int DELAY = 500;
    private ArrayList<Integer> numbers = new ArrayList<>(SIZE);
    private JFrame frame;

    public BubbleSortVisualization() {
        setPreferredSize(new Dimension(GAP * SIZE + 1, SIZE + 1));
        generateRandomNumbers();
    }

    private void generateRandomNumbers() {
        for (int i = 1; i <= SIZE; i++) {
            numbers.add(i);
        }
        long seed = System.currentTimeMillis();
        Collections.shuffle(numbers, new Random(seed));
    }

    private void swap(int i, int j) {
        int temp = numbers.get(i);
        numbers.set(i, numbers.get(j));
        numbers.set(j, temp);
    }

    private void bubbleSort() {
        int n = numbers.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    swap(j, j + 1);
                    drawGraph(j, j + 1);
                }
            }
        }
    }

    private void drawGraph(int i, int j) {
        Graphics g = getGraphics();
        g.setColor(Color.GREEN);
        g.drawLine(GAP * j + 1, SIZE, GAP * j + 1, SIZE - numbers.get(j));
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.setColor(Color.BLACK);
        g.drawLine(GAP * j + 1, SIZE, GAP * j + 1, SIZE - numbers.get(j));

        g.setColor(Color.WHITE);
        g.drawLine(GAP * j + 1, SIZE, GAP * j + 1, SIZE - numbers.get(i));

        g.setColor(Color.BLACK);
        g.drawLine(GAP * i + 1, SIZE, GAP * i + 1, SIZE - numbers.get(i));

        g.setColor(Color.GREEN);
        g.drawLine(GAP * i + 1, SIZE, GAP * i + 1, SIZE - numbers.get(j));
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.setColor(Color.WHITE);
        g.drawLine(GAP * i + 1, SIZE, GAP * i + 1, SIZE - numbers.get(j));
    }

    public void startSorting() {
        new Thread(() -> {
            bubbleSort();
            frame.setTitle("Bubble Sort Completed");
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BubbleSortVisualization visualization = new BubbleSortVisualization();
            visualization.frame = new JFrame("Bubble Sort Visualization");
            visualization.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            visualization.frame.setResizable(false);
            visualization.frame.add(visualization);
            visualization.frame.pack();
            visualization.frame.setLocationRelativeTo(null);
            visualization.frame.setVisible(true);
            visualization.startSorting();
        });
    }
}