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

            JPanel panel1 = new JPanel();
            frame.add(panel1, BorderLayout.PAGE_START);

            JLabel inputLabel = new JLabel("Введите температуру");
            panel1.add(inputLabel);

            JTextField temperatureField = new JTextField(8);
            panel1.add(temperatureField);

            JButton buttonConvert = new JButton("Сконвертировать");
            panel1.add(buttonConvert);

            JPanel panel2 = new JPanel();
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));

            frame.add(panel2, BorderLayout.LINE_START);

            JLabel initialScale = new JLabel("Выберете начальную шкалу");
            panel2.add(initialScale);

            JButton initialCelsius = new JButton("Цельсий");
            panel2.add(initialCelsius);

            JButton initialKelvin = new JButton("Кельвин");
            panel2.add(initialKelvin);

            JButton initialFahrenheit = new JButton("Фаренгейт");
            panel2.add(initialFahrenheit);

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

            JPanel panel3 = new JPanel();
            panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));

            frame.add(panel3, BorderLayout.CENTER);

            JLabel resultingScale = new JLabel("Выберете результирующую шкалу");
            panel3.add(resultingScale);

            JButton resultingCelsius = new JButton("Цельсий");
            panel3.add(resultingCelsius);

            JButton resultingKelvin = new JButton("Кельвин");
            panel3.add(resultingKelvin);

            JButton resultingFahrenheit = new JButton("Фаренгейт");
            panel3.add(resultingFahrenheit);

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

            JPanel panel4 = new JPanel();
            frame.add(panel4, BorderLayout.PAGE_END);

            JLabel resultLabel = new JLabel();
            panel4.add(resultLabel);

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