package ru.academits.maksimenko.model;

public class Kelvin {
    public double convertToCelsiusTemperature(double kelvin) {
        final double absoluteZero = 273.15;

        return kelvin - absoluteZero;
    }

    public double convertToFahrenheitTemperature(double kelvin) {
        final double absoluteZero = 273.15;
        final int iceMeltingTemperature = 32;

        return (kelvin - absoluteZero) * 1.8 + iceMeltingTemperature;
    }
}