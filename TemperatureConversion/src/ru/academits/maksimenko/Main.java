package ru.academits.maksimenko;

import ru.academits.maksimenko.model.Scale;
import ru.academits.maksimenko.model.TemperatureScale;
import ru.academits.maksimenko.view.FrameView;
import ru.academits.maksimenko.view.ScaleButtons;
import ru.academits.maksimenko.view.ScaleButtonsList;
import ru.academits.maksimenko.view.View;

public class Main {
    public static void main(String[] args) {
        Scale temperatureScale = new TemperatureScale();
        ScaleButtonsList scaleButtonsList = new ScaleButtons();

        View view = new FrameView(temperatureScale, scaleButtonsList);

        view.start();
    }
}