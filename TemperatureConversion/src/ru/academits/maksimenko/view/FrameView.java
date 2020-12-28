package ru.academits.maksimenko.view;

import ru.academits.maksimenko.model.Scale;

import javax.swing.*;
import java.awt.*;

public class FrameView implements View {
    private final Scale temperatureScale;
    private final ScaleButtonsList scaleButtonsList;
    private final ResultPanel resultPanel;

    private JFrame frame;
    private JPanel result;
    private final Font font;

    public FrameView(Scale temperatureScale, ScaleButtonsList scaleButtonsList) {
        this.temperatureScale = temperatureScale;
        this.scaleButtonsList = scaleButtonsList;
        font = new Font("Courier New", Font.BOLD, 14);
        resultPanel = new ResultPanel(this, font);
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

            ConversionTemperatures conversionTemperatures = new ConversionTemperatures(temperatureScale);

            InputPanel inputPanel = new InputPanel(conversionTemperatures, font);
            frame.add(inputPanel.initializationPanel(), BorderLayout.PAGE_START);

            InitialTemperaturePanel initialTemperaturePanel = new InitialTemperaturePanel(conversionTemperatures, scaleButtonsList);
            frame.add(initialTemperaturePanel.initializationPanel(), BorderLayout.LINE_START);

            EndTemperaturePanel endTemperaturePanel = new EndTemperaturePanel(conversionTemperatures, scaleButtonsList);
            frame.add(endTemperaturePanel.initializationPanel(), BorderLayout.CENTER);

            result = resultPanel.initializationPanel();

            conversionTemperatures.setResultPanel(resultPanel);

            frame.pack();
            frame.setLocationRelativeTo(null);
        });
    }

    public void updateResultPanel(JLabel label) {
        result.add(label);

        frame.add(result, BorderLayout.PAGE_END);
        frame.pack();
    }
}