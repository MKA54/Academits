package ru.academits.maksimenko.model;

public class Fahrenheit {
    public double convertToCelsiusTemperature(double fahrenheit) {
        final int iceMeltingTemperature = 32;

        return (fahrenheit - iceMeltingTemperature) * 5.0 / 9.0;
    }

    public double convertToKelvinTemperature(double fahrenheit) {
        final double absoluteZero = 273.15;
        final int iceMeltingTemperature = 32;

        return (fahrenheit - iceMeltingTemperature) / 1.8 + absoluteZero;
    }
}