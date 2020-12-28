package ru.academits.maksimenko.view;

public class ScaleButtons implements ScaleButtonsList {
    @Override
    public String[] addTemperatureScale() {
        return new String[]{"Цельсий", "Кельвин", "Фаренгейт"};
    }
}