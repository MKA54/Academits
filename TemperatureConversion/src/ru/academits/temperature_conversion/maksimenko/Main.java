package ru.academits.temperature_conversion.maksimenko;

import ru.academits.temperature_conversion.maksimenko.model.CelsiusScale;
import ru.academits.temperature_conversion.maksimenko.model.FahrenheitScale;
import ru.academits.temperature_conversion.maksimenko.model.KelvinScale;
import ru.academits.temperature_conversion.maksimenko.model.Scale;
import ru.academits.temperature_conversion.maksimenko.view.FrameView;
import ru.academits.temperature_conversion.maksimenko.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new FrameView(new Scale[]{
                new CelsiusScale(),
                new FahrenheitScale(),
                new KelvinScale()});

        view.start();
    }
}