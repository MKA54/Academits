package ru.academits.maksimenko.model;

public class TemperatureScale implements Scale{
    static double absoluteZero = 273.15;
    static int iceMeltingTemperature = 32;

    public double convertCelsiusToFahrenheitTemperature(double celsius) {
        return 1.8 * celsius + iceMeltingTemperature;
    }

    public double convertCelsiusToKelvinTemperature(double celsius) {
        return celsius + absoluteZero;
    }

    public double convertFahrenheitToCelsiusTemperature(double fahrenheit) {
        return (fahrenheit - iceMeltingTemperature) * 5.0 / 9.0;
    }

    public double convertFahrenheitToKelvinTemperature(double fahrenheit) {
        return (fahrenheit - iceMeltingTemperature) / 1.8 + absoluteZero;
    }

    public double convertKelvinToCelsiusTemperature(double kelvin) {
        return kelvin - absoluteZero;
    }

    public double convertKelvinToFahrenheitTemperature(double kelvin) {
        return (kelvin - absoluteZero) * 1.8 + iceMeltingTemperature;
    }
}