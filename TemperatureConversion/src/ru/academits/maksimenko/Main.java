package ru.academits.maksimenko;

import ru.academits.maksimenko.view.FrameView;
import ru.academits.maksimenko.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new FrameView();

        view.start();
    }
}