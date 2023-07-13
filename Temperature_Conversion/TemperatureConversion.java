import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConversion extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;
    private JLabel scaleLabel;
    private JButton convertButton;
    private JButton clearButton;
    private JButton switchButton;
    private String currentScale;

    public TemperatureConversion() {
        // Set the title of the JFrame
        super("Temperature Conversion");

        // Set the layout for the JFrame
        setLayout(new FlowLayout());

        // Create the input field
        inputField = new JTextField(10);
        add(inputField);

        // Create the scale label
        scaleLabel = new JLabel("Scale: Celsius");
        add(scaleLabel);

        // Create the result label
        resultLabel = new JLabel();
        add(resultLabel);

        // Create the convert button
        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
        add(convertButton);

        // Create the clear button
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        add(clearButton);

        // Create the switch button
        switchButton = new JButton("Switch");
        switchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchScales();
            }
        });
        add(switchButton);

        // Initialize the current scale to Celsius
        currentScale = "Celsius";

        // Set default close operation and size for the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(inputField.getText());
            double result;

            if (currentScale.equals("Celsius")) {
                result = celsiusToFahrenheit(temperature);
                resultLabel.setText("Result: " + result + " °F");
            } else if (currentScale.equals("Fahrenheit")) {
                result = fahrenheitToCelsius(temperature);
                resultLabel.setText("Result: " + result + " °C");
            } else if (currentScale.equals("Kelvin")) {
                result = kelvinToCelsius(temperature);
                resultLabel.setText("Result: " + result + " °C");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
        }
    }

    private void clearFields() {
        inputField.setText("");
        resultLabel.setText("");
    }

    private void switchScales() {
        if (currentScale.equals("Celsius")) {
            currentScale = "Fahrenheit";
            switchButton.setText("Switch to Celsius");
        } else if (currentScale.equals("Fahrenheit")) {
            currentScale = "Kelvin";
            switchButton.setText("Switch to Fahrenheit");
        } else if (currentScale.equals("Kelvin")) {
            currentScale = "Celsius";
            switchButton.setText("Switch to Kelvin");
        }

        scaleLabel.setText("Scale: " + currentScale);
        clearFields();
    }

    private double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TemperatureConversion conversionApp = new TemperatureConversion();
                conversionApp.setVisible(true);
            }
        });
    }
}
