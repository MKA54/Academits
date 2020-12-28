package ru.academits.maksimenko.view;

import javax.swing.*;
import java.awt.*;

class ResultPanel implements Panel {
    private final FrameView frameView;
    private JLabel resultLabel;
    private final Font font;

    public ResultPanel(FrameView frameView, Font font) {
        this.frameView = frameView;
        this.font = font;
    }

    @Override
    public JPanel initializationPanel() {
        resultLabel = new JLabel();

        return new JPanel();
    }

    public void setResultPanel(double result) {
        resultLabel.setFont(font);
        resultLabel.setText("Результат: " + result);

        frameView.updateResultPanel(resultLabel);
    }
}