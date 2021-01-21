package ru.academits.temperature_conversion.maksimenko.view;

import ru.academits.temperature_conversion.maksimenko.model.Scale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FrameView implements View {
    private final Scale[] scales;

    private Scale originalScale;
    private Scale resultingScale;

    private JFrame frame;
    private JPanel resultPanel;
    private JLabel resultLabel;

    public FrameView(Scale[] scales) {
        this.scales = scales;
        originalScale = scales[0];
        resultingScale = scales[0];
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            frame = new JFrame(" Перевод температуры");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setVisible(true);

            JPanel inputPanel = new JPanel();

            Font font = new Font("Courier New", Font.BOLD, 14);

            JLabel inputLabel = new JLabel("Введите температуру");
            inputLabel.setFont(font);
            inputPanel.add(inputLabel);

            JTextField temperatureField = new JTextField(8);

            inputPanel.add(temperatureField);

            JButton buttonConvert = new JButton("Сконвертировать");

            inputPanel.add(buttonConvert);

            frame.add(inputPanel, BorderLayout.PAGE_START);

            JPanel initialTemperaturePanel = new JPanel();
            initialTemperaturePanel.setLayout(new BoxLayout(initialTemperaturePanel, BoxLayout.PAGE_AXIS));

            initialTemperaturePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel selectInitialScale = new JLabel("Выберите начальную шкалу", JLabel.LEFT);

            initialTemperaturePanel.add(selectInitialScale);

            initialTemperaturePanel.add(Box.createRigidArea(new Dimension(5, 5)));

            JComboBox<Scale> initialScaleBox = new JComboBox<>(scales);
            initialScaleBox.setMaximumSize(new Dimension(90, 25));

            initialTemperaturePanel.add(initialScaleBox);

            ActionListener initActionListener = e -> {
                JComboBox box = (JComboBox) e.getSource();

                originalScale = (Scale) box.getSelectedItem();
            };

            initialScaleBox.addActionListener(initActionListener);

            frame.add(initialTemperaturePanel, BorderLayout.LINE_START);

            JPanel resultingTemperaturePanel = new JPanel();
            resultingTemperaturePanel.setLayout(new BoxLayout(resultingTemperaturePanel, BoxLayout.PAGE_AXIS));

            resultingTemperaturePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel selectResultingScale = new JLabel("Выберите результирующую шкалу", JLabel.LEFT);

            resultingTemperaturePanel.add(selectResultingScale);

            resultingTemperaturePanel.add(Box.createRigidArea(new Dimension(5, 5)));

            JComboBox<Scale> resultingScaleBox = new JComboBox<>(scales);
            resultingScaleBox.setMaximumSize(new Dimension(90, 25));

            resultingTemperaturePanel.add(resultingScaleBox);

            ActionListener endActionListener = e -> {
                JComboBox box = (JComboBox) e.getSource();

                resultingScale = (Scale) box.getSelectedItem();
            };

            resultingScaleBox.addActionListener(endActionListener);

            frame.add(resultingTemperaturePanel, BorderLayout.CENTER);

            resultPanel = new JPanel();

            frame.add(resultPanel, BorderLayout.PAGE_END);

            buttonConvert.addActionListener(e -> {
                try {
                    double initialTemperature = Double.parseDouble(temperatureField.getText());

                    if (originalScale == scales[0] && resultingScale == scales[1]) {
                        initialTemperature = originalScale.convertFahrenheit(initialTemperature);
                    }

                    if (originalScale == scales[0] && resultingScale == scales[2]) {
                        initialTemperature = originalScale.convertKelvin(initialTemperature);
                    }

                    if (originalScale == scales[1] && resultingScale == scales[0]) {
                        initialTemperature = originalScale.convertCelsius(initialTemperature);
                    }

                    if (originalScale == scales[1] && resultingScale == scales[2]) {
                        initialTemperature = originalScale.convertKelvin(initialTemperature);
                    }

                    if (originalScale == scales[2] && resultingScale == scales[0]) {
                        initialTemperature = originalScale.convertCelsius(initialTemperature);
                    }

                    if (originalScale == scales[2] && resultingScale == scales[1]) {
                        initialTemperature = originalScale.convertFahrenheit(initialTemperature);
                    }

                    updateResultPanel(initialTemperature);
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Необходимо ввести число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            resultLabel = new JLabel();

            frame.pack();
            frame.setLocationRelativeTo(null);
        });
    }

    private void updateResultPanel(double result) {
        resultLabel.setText("Результат: " + result);

        resultPanel.add(resultLabel);

        frame.pack();
    }
}