package ru.academits.temperature_conversion.maksimenko.model;

public class FahrenheitScale implements Scale {
    private static final double celsiusAbsoluteZero = 273.15;
    private static final int fahrenheitIceMeltingTemperature = 32;

    @Override
    public double convertCelsius(double temperature) {
        return (temperature - fahrenheitIceMeltingTemperature) * 5.0 / 9.0;
    }

    @Override
    public double convertFahrenheit(double temperature) {
        return temperature;
    }

    @Override
    public double convertKelvin(double temperature) {
        return (temperature - fahrenheitIceMeltingTemperature) / 1.8 + celsiusAbsoluteZero;
    }

    @Override
    public String toString() {
        return "Фаренгейт";
    }
}