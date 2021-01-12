package ru.academits.maksimenko.model;

public interface Scale {
    double convertCelsius(double temperature);

    double convertFahrenheit(double temperature);

    double convertKelvin(double temperature);
}