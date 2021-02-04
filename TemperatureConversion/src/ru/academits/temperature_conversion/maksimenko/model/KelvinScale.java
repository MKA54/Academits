package ru.academits.temperature_conversion.maksimenko.model;

public class KelvinScale implements Scale {
    private static final double celsiusAbsoluteZero = 273.15;

    @Override
    public double convertToCelsius(double temperature) {
        return temperature - celsiusAbsoluteZero;
    }

    @Override
    public double convert(double temperature) {
        return temperature + celsiusAbsoluteZero;
    }

    @Override
    public String toString() {
        return "Кельвин";
    }
}