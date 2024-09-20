import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MergeSortVisualization extends JFrame {
    private ArrayList<Integer> array;
    private JPanel visualPanel;
    private JScrollPane scrollPane;

    private int stepCounter;

    public MergeSortVisualization() {
        array = new ArrayList<>();
        visualPanel = new JPanel();
        stepCounter = 0;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Merge Sort Visualization");
        setSize(800, 600);
        setLocationRelativeTo(null);

        JLabel inputLabel = new JLabel("Enter numbers separated by commas:");
        JTextField inputField = new JTextField(20);
        JButton sortButton = new JButton("Sort");

        JPanel inputPanel = new JPanel();
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(sortButton);

        sortButton.addActionListener(e -> {
            String inputText = inputField.getText();
            String[] inputArray = inputText.split(",");
            array.clear();
 
            try {
                for (String s : inputArray) {
                    array.add(Integer.parseInt(s.trim()));
                }
                displayMergeSortSteps();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter integers separated by commas.");
            }
        });

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(visualPanel, BorderLayout.CENTER);

        visualPanel.setLayout(new GridLayout(0, 1));
        scrollPane = new JScrollPane(visualPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void displayMergeSortSteps() {
        stepCounter = 0;
        mergeSort(array);
    }

    private void mergeSort(ArrayList<Integer> arr) {
        if (arr.size() <= 1) {
            return;
        }

        int mid = arr.size() / 2;
        ArrayList<Integer> leftArr = new ArrayList<>(arr.subList(0, mid));
        ArrayList<Integer> rightArr = new ArrayList<>(arr.subList(mid, arr.size()));

        mergeSort(leftArr);
        mergeSort(rightArr);

        merge(arr, leftArr, rightArr);
    }

    private void merge(ArrayList<Integer> result, ArrayList<Integer> leftArray, ArrayList<Integer> rightArray) {
        int i = 0, j = 0, k = 0;
        Integer[] mergedArray = new Integer[result.size()];

        while (i < leftArray.size() && j < rightArray.size()) {
            if (leftArray.get(i) <= rightArray.get(j)) {
                mergedArray[k++] = leftArray.get(i++);
            } else {
                mergedArray[k++] = rightArray.get(j++);
            }
        }

        while (i < leftArray.size()) {
            mergedArray[k++] = leftArray.get(i++);
        }

        while (j < rightArray.size()) {
            mergedArray[k++] = rightArray.get(j++);
        }

        // Copy merged elements back to result array
        for (k = 0; k < mergedArray.length; k++) {
            result.set(k, mergedArray[k]);
        }

        displayStep(result, leftArray, rightArray);
    }

    private void displayStep(ArrayList<Integer> result, ArrayList<Integer> leftArray, ArrayList<Integer> rightArray) {
        visualPanel.removeAll();
        visualPanel.setLayout(new GridLayout(3, 1));

        JLabel resultLabel = new JLabel("Merged Array: " + Arrays.toString(result.toArray()));
        JLabel leftLabel = new JLabel("Left Array: " + Arrays.toString(leftArray.toArray()));
        JLabel rightLabel = new JLabel("Right Array: " + Arrays.toString(rightArray.toArray()));

        visualPanel.add(resultLabel);
        visualPanel.add(leftLabel);
        visualPanel.add(rightLabel);

        visualPanel.revalidate();
        visualPanel.repaint();

        // Delay for visualization purposes
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}