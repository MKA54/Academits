package ru.academits.temperature_conversion.maksimenko.model;

public class FahrenheitScale implements Scale {
    private static final int fahrenheitIceMeltingTemperature = 32;

    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - fahrenheitIceMeltingTemperature) * 5.0 / 9.0;
    }

    @Override
    public double convert(double temperature) {
        return 9.0 / 5.0 * temperature + fahrenheitIceMeltingTemperature;
    }

    @Override
    public String toString() {
        return "Фаренгейт";
    }
}