package ru.academits.maksimenko.view;

import ru.academits.maksimenko.model.Celsius;
import ru.academits.maksimenko.model.Fahrenheit;
import ru.academits.maksimenko.model.Kelvin;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FrameView implements View {
    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame frame = new JFrame(" Перевод температуры");

            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setVisible(true);

            JPanel inputPanel = new JPanel();
            frame.add(inputPanel, BorderLayout.PAGE_START);

            Font font = new Font("Courier New", Font.BOLD, 14);

            JLabel inputLabel = new JLabel("Введите температуру");
            inputLabel.setFont(font);
            inputPanel.add(inputLabel);

            JTextField temperatureField = new JTextField(8);
            inputPanel.add(temperatureField);

            JButton buttonConvert = new JButton("Сконвертировать");
            inputPanel.add(buttonConvert);

            JPanel initialTemperaturePanel = new JPanel();
            initialTemperaturePanel.setLayout(new BoxLayout(initialTemperaturePanel, BoxLayout.PAGE_AXIS));
            initialTemperaturePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            frame.add(initialTemperaturePanel, BorderLayout.LINE_START);

            JLabel initialScale = new JLabel("Выберете начальную шкалу");

            initialTemperaturePanel.add(initialScale);

            initialTemperaturePanel.add(Box.createRigidArea(new Dimension(5, 5)));

            JButton initialCelsius = new JButton("Цельсий");
            initialCelsius.setMaximumSize(new Dimension(120, 40));

            initialTemperaturePanel.add(initialCelsius);

            initialTemperaturePanel.add(Box.createRigidArea(new Dimension(5, 5)));

            JButton initialKelvin = new JButton("Кельвин");
            initialKelvin.setMaximumSize(new Dimension(120, 40));

            initialTemperaturePanel.add(initialKelvin);

            initialTemperaturePanel.add(Box.createRigidArea(new Dimension(5, 5)));

            JButton initialFahrenheit = new JButton("Фаренгейт");
            initialFahrenheit.setMaximumSize(new Dimension(120, 40));

            initialTemperaturePanel.add(initialFahrenheit);

            AtomicBoolean initCelsius = new AtomicBoolean();
            AtomicBoolean initKelvin = new AtomicBoolean();
            AtomicBoolean initFahrenheit = new AtomicBoolean();

            initialCelsius.addActionListener(e -> {
                initCelsius.set(true);

                initialKelvin.setEnabled(false);
                initialFahrenheit.setEnabled(false);
            });

            initialKelvin.addActionListener(e -> {
                initKelvin.set(true);

                initialCelsius.setEnabled(false);
                initialFahrenheit.setEnabled(false);
            });

            initialFahrenheit.addActionListener(e -> {
                initFahrenheit.set(true);

                initialCelsius.setEnabled(false);
                initialKelvin.setEnabled(false);
            });

            JPanel endTemperaturePanel = new JPanel();
            endTemperaturePanel.setLayout(new BoxLayout(endTemperaturePanel, BoxLayout.PAGE_AXIS));

            endTemperaturePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            frame.add(endTemperaturePanel, BorderLayout.CENTER);

            JLabel resultingScale = new JLabel("Выберете результирующую шкалу");

            endTemperaturePanel.add(resultingScale);

            endTemperaturePanel.add(Box.createRigidArea(new Dimension(5, 5)));

            JButton resultingCelsius = new JButton("Цельсий");
            resultingCelsius.setMaximumSize(new Dimension(120, 40));

            endTemperaturePanel.add(resultingCelsius);

            endTemperaturePanel.add(Box.createRigidArea(new Dimension(5, 5)));

            JButton resultingKelvin = new JButton("Кельвин");
            resultingKelvin.setMaximumSize(new Dimension(120, 40));

            endTemperaturePanel.add(resultingKelvin);

            endTemperaturePanel.add(Box.createRigidArea(new Dimension(5, 5)));

            JButton resultingFahrenheit = new JButton("Фаренгейт");
            resultingFahrenheit.setMaximumSize(new Dimension(120, 40));

            endTemperaturePanel.add(resultingFahrenheit);

            AtomicBoolean resultCelsius = new AtomicBoolean();
            AtomicBoolean resultKelvin = new AtomicBoolean();
            AtomicBoolean resultFahrenheit = new AtomicBoolean();

            resultingCelsius.addActionListener(e -> {
                resultCelsius.set(true);

                resultingKelvin.setEnabled(false);
                resultingFahrenheit.setEnabled(false);
            });

            resultingKelvin.addActionListener(e -> {
                resultKelvin.set(true);

                resultingCelsius.setEnabled(false);
                resultingFahrenheit.setEnabled(false);
            });

            resultingFahrenheit.addActionListener(e -> {
                resultFahrenheit.set(true);

                resultingCelsius.setEnabled(false);
                resultingKelvin.setEnabled(false);
            });

            JPanel resultPanel = new JPanel();

            frame.add(resultPanel, BorderLayout.PAGE_END);

            JLabel resultLabel = new JLabel();
            resultLabel.setFont(font);

            resultPanel.add(resultLabel);

            buttonConvert.addActionListener(e -> {
                try {
                    double initialTemperature = Double.parseDouble(temperatureField.getText());

                    double result = 0;

                    if (initCelsius.get() && resultKelvin.get()) {
                        result = new Celsius().convertToKelvinTemperature(initialTemperature);
                    }

                    if (initCelsius.get() && resultFahrenheit.get()) {
                        result = new Celsius().convertToFahrenheitTemperature(initialTemperature);
                    }

                    if (initKelvin.get() && resultCelsius.get()) {
                        result = new Kelvin().convertToCelsiusTemperature(initialTemperature);
                    }

                    if (initKelvin.get() && resultFahrenheit.get()) {
                        result = new Kelvin().convertToFahrenheitTemperature(initialTemperature);
                    }

                    if (initFahrenheit.get() && resultCelsius.get()) {
                        result = new Fahrenheit().convertToCelsiusTemperature(initialTemperature);
                    }

                    if (initFahrenheit.get() && resultKelvin.get()) {
                        result = new Fahrenheit().convertToKelvinTemperature(initialTemperature);
                    }

                    if (result == 0) {
                        result = initialTemperature;
                    }

                    resultLabel.setText("Результат = " + result);
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Необходимо ввести число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
    }
}