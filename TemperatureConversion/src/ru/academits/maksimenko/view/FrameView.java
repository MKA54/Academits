package ru.academits.maksimenko.view;

import ru.academits.maksimenko.model.Scale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FrameView implements View {
    private final Scale temperatureScale;
    private final ScaleButtonsList scaleButtonsList;

    private JFrame frame;
    private JPanel resultPanel;
    private final JLabel resultLabel;

    public FrameView(Scale temperatureScale, ScaleButtonsList scaleButtonsList) {
        this.temperatureScale = temperatureScale;
        this.scaleButtonsList = scaleButtonsList;
        resultLabel = new JLabel();
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

            JLabel initialScale = new JLabel("Выберите начальную шкалу");

            initialTemperaturePanel.add(initialScale);
            initialTemperaturePanel.add(Box.createRigidArea(new Dimension(5, 5)));

            JComboBox<String> initScalesList = new JComboBox<>(scaleButtonsList.addTemperatureScale());
            initialTemperaturePanel.add(initScalesList);

            ConversionTemperatures conversionTemperatures = new ConversionTemperatures(this, temperatureScale);

            ActionListener initActionListener = e -> {
                JComboBox<String> box = (JComboBox<String>) e.getSource();
                String item = (String) box.getSelectedItem();

                conversionTemperatures.setInitialTemperature(item);
            };

            initScalesList.addActionListener(initActionListener);

            frame.add(initialTemperaturePanel, BorderLayout.LINE_START);

            JPanel endTemperaturePanel = new JPanel();
            endTemperaturePanel.setLayout(new BoxLayout(endTemperaturePanel, BoxLayout.PAGE_AXIS));

            endTemperaturePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel resultingScale = new JLabel("Выберите результирующую шкалу");
            endTemperaturePanel.add(resultingScale);

            endTemperaturePanel.add(Box.createRigidArea(new Dimension(5, 5)));

            JComboBox<String> endScalesList = new JComboBox<>(scaleButtonsList.addTemperatureScale());
            endTemperaturePanel.add(endScalesList);

            ActionListener endActionListener = e -> {
                JComboBox<String> box = (JComboBox<String>) e.getSource();
                String item = (String) box.getSelectedItem();

                conversionTemperatures.setEndTemperature(item);
            };

            endScalesList.addActionListener(endActionListener);

            frame.add(endTemperaturePanel, BorderLayout.CENTER);

            resultPanel = new JPanel();

            frame.add(resultPanel, BorderLayout.PAGE_END);

            buttonConvert.addActionListener(e -> {
                try {
                    double initialTemperature = Double.parseDouble(temperatureField.getText());

                    conversionTemperatures.conversion(initialTemperature);
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Необходимо ввести число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            frame.pack();
            frame.setLocationRelativeTo(null);
        });
    }

    public void updateResultPanel(double result) {
        resultLabel.setText("Результат: " + result);

        resultPanel.add(resultLabel);

        frame.pack();
    }
}