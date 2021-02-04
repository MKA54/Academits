package ru.academits.temperature_conversion.maksimenko.model;

public interface Scale {
    double convertToCelsius(double temperature);

    double convert(double temperature);
}