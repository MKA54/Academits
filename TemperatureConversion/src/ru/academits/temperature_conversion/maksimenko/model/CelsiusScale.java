package ru.academits.temperature_conversion.maksimenko.model;

public class CelsiusScale implements Scale {
    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convert(double temperature) {
        return temperature;
    }

    @Override
    public String toString() {
        return "Цельсий";
    }
}