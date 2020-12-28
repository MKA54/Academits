package ru.academits.maksimenko.view;

import javax.swing.*;
import java.awt.*;

class InputPanel implements Panel {
    private final Font font;

    private final ConversionTemperatures conversionTemperatures;

    public InputPanel(ConversionTemperatures conversionTemperatures, Font font) {
        this.font = font;
        this.conversionTemperatures = conversionTemperatures;
    }

    @Override
    public JPanel initializationPanel() {
        JPanel inputPanel = new JPanel();

        JLabel inputLabel = new JLabel("Введите температуру");
        inputLabel.setFont(font);
        inputPanel.add(inputLabel);

        JTextField temperatureField = new JTextField(8);
        inputPanel.add(temperatureField);

        JButton buttonConvert = new JButton("Сконвертировать");
        inputPanel.add(buttonConvert);

        buttonConvert.addActionListener(e -> {
            try {
                double initialTemperature = Double.parseDouble(temperatureField.getText());

                conversionTemperatures.conversion(initialTemperature);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Необходимо ввести число", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        return inputPanel;
    }
}