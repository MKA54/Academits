package ru.academits.temperature_conversion.maksimenko.model;

public interface Scale {
    double convertCelsius(double temperature);

    double convertFahrenheit(double temperature);

    double convertKelvin(double temperature);
}