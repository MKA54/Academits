package ru.academits.maksimenko;

import ru.academits.maksimenko.model.CelsiusScale;
import ru.academits.maksimenko.model.FahrenheitScale;
import ru.academits.maksimenko.model.KelvinScale;
import ru.academits.maksimenko.model.Scale;
import ru.academits.maksimenko.view.FrameView;
import ru.academits.maksimenko.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new FrameView(new Scale[]{new CelsiusScale(), new FahrenheitScale(), new KelvinScale()});

        view.start();
    }
}