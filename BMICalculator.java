import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculator extends JFrame {
    // Declare components for the GUI
    private JLabel weightLabel, heightLabel, resultLabel;
    private JTextField weightField, heightField;
    private JButton calculateButton;
    private JTextArea resultArea;

    // Constructor to set up the GUI
    public BMICalculator() {
        // Set the title of the frame
        setTitle("BMI Calculator");

        // Set layout for the frame
        setLayout(new FlowLayout());

        // Initialize components
        weightLabel = new JLabel("Enter Weight (kg):");
        heightLabel = new JLabel("Enter Height (m):");
        resultLabel = new JLabel("BMI Result:");
        
        weightField = new JTextField(10);
        heightField = new JTextField(10);
        
        calculateButton = new JButton("Calculate BMI");
        
        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);  // Make the result area non-editable
        
        // Add components to the frame
        add(weightLabel);
        add(weightField);
        add(heightLabel);
        add(heightField);
        add(calculateButton);
        add(resultLabel);
        add(new JScrollPane(resultArea)); // Scrollable text area

        // Set the action listener for the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get user input
                try {
                    double weight = Double.parseDouble(weightField.getText());
                    double height = Double.parseDouble(heightField.getText());
                    
                    // Validate inputs
                    if (weight <= 0 || height <= 0) {
                        JOptionPane.showMessageDialog(null, "Please enter valid positive numbers.");
                        return;
                    }
                    
                    // Calculate BMI
                    double bmi = weight / (height * height);
                    
                    // Classify BMI result
                    String classification = classifyBMI(bmi);
                    
                    // Display result in the text area
                    resultArea.setText(String.format("BMI: %.2f\n%s", bmi, classification));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numeric values for weight and height.");
                }
            }
        });

        // Set default frame properties
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window
    }

    // Method to classify BMI result
    private String classifyBMI(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Create and display the BMI calculator
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BMICalculator().setVisible(true);
            }
        });
    }
}
