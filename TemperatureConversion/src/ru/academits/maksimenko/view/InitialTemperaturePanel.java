package ru.academits.maksimenko.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class InitialTemperaturePanel implements Panel {
    private final ScaleButtonsList scaleButtonsList;

    private final ConversionTemperatures conversionTemperatures;

    public InitialTemperaturePanel(ConversionTemperatures conversionTemperatures, ScaleButtonsList scaleButtonsList) {
        this.scaleButtonsList = scaleButtonsList;
        this.conversionTemperatures = conversionTemperatures;
    }

    @Override
    public JPanel initializationPanel() {
        JPanel initialTemperaturePanel = new JPanel();
        initialTemperaturePanel.setLayout(new BoxLayout(initialTemperaturePanel, BoxLayout.PAGE_AXIS));

        initialTemperaturePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel initialScale = new JLabel("Выберите начальную шкалу");

        initialTemperaturePanel.add(initialScale);
        initialTemperaturePanel.add(Box.createRigidArea(new Dimension(5, 5)));

        JComboBox<String> scalesList = new JComboBox<>(scaleButtonsList.addTemperatureScale());
        initialTemperaturePanel.add(scalesList);

        ActionListener actionListener = e -> {
            JComboBox<String> box = (JComboBox<String>) e.getSource();
            String item = (String) box.getSelectedItem();

            conversionTemperatures.setInitialTemperature(item);
        };

        scalesList.addActionListener(actionListener);

        return initialTemperaturePanel;
    }
}