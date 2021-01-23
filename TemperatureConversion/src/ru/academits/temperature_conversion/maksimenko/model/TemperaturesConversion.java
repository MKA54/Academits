package ru.academits.temperature_conversion.maksimenko.model;

public interface TemperaturesConversion {
    double convertTemperature(double temperature, Scale original, Scale resulting);

    Scale[] getScalesList();

    Scale getInitialScale();
}