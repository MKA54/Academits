package ru.academits.temperature_conversion.maksimenko;

import ru.academits.temperature_conversion.maksimenko.model.*;
import ru.academits.temperature_conversion.maksimenko.view.FrameView;
import ru.academits.temperature_conversion.maksimenko.view.View;

public class Main {
    public static void main(String[] args) {
        TemperaturesConversion temperaturesConversion = new TemperaturesConversionLogic();

        View view = new FrameView(temperaturesConversion);

        view.start();
    }
}