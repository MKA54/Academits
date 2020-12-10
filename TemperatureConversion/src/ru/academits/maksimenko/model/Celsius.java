package ru.academits.maksimenko.model;

public class Celsius {
    public double convertToFahrenheitTemperature(double celsius) {
        final int iceMeltingTemperature = 32;

        return 1.8 * celsius + iceMeltingTemperature;
    }

    public double convertToKelvinTemperature(double celsius) {
        final double absoluteZero = 273.15;

        return celsius + absoluteZero;
    }
}