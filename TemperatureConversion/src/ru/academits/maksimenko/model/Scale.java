package ru.academits.maksimenko.model;

public interface Scale {
    double convertCelsiusToFahrenheitTemperature(double celsius);

    double convertCelsiusToKelvinTemperature(double celsius);

    double convertFahrenheitToCelsiusTemperature(double fahrenheit);

    double convertFahrenheitToKelvinTemperature(double fahrenheit);

    double convertKelvinToCelsiusTemperature(double kelvin);

    double convertKelvinToFahrenheitTemperature(double kelvin);
}