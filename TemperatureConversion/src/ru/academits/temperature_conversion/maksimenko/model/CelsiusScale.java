package ru.academits.temperature_conversion.maksimenko.model;

public class CelsiusScale implements Scale {
    private static final double celsiusAbsoluteZero = 273.15;
    private static final int fahrenheitIceMeltingTemperature = 32;

    @Override
    public double convertCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFahrenheit(double temperature) {
        return 1.8 * temperature + fahrenheitIceMeltingTemperature;
    }

    @Override
    public double convertKelvin(double temperature) {
        return temperature + celsiusAbsoluteZero;
    }

    @Override
    public String toString() {
        return "Цельсий";
    }
}