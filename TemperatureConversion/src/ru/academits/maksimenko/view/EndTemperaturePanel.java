package ru.academits.maksimenko.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EndTemperaturePanel implements Panel {
    private final ScaleButtonsList scaleButtonsList;

    ConversionTemperatures conversionTemperatures;

    public EndTemperaturePanel(ConversionTemperatures conversionTemperatures, ScaleButtonsList scaleButtonsList) {
        this.scaleButtonsList = scaleButtonsList;
        this.conversionTemperatures = conversionTemperatures;
    }

    @Override
    public JPanel initializationPanel() {
        JPanel endTemperaturePanel = new JPanel();
        endTemperaturePanel.setLayout(new BoxLayout(endTemperaturePanel, BoxLayout.PAGE_AXIS));

        endTemperaturePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel resultingScale = new JLabel("Выберите результирующую шкалу");
        endTemperaturePanel.add(resultingScale);

        endTemperaturePanel.add(Box.createRigidArea(new Dimension(5, 5)));

        JComboBox<String> scalesList = new JComboBox<>(scaleButtonsList.addTemperatureScale());
        endTemperaturePanel.add(scalesList);

        ActionListener actionListener = e -> {
            JComboBox<String> box = (JComboBox<String>) e.getSource();
            String item = (String) box.getSelectedItem();

            conversionTemperatures.setEndTemperature(item);
        };

        scalesList.addActionListener(actionListener);

        return endTemperaturePanel;
    }
}