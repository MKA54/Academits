package ru.academits.maksimenko.model;

public class CelsiusScale implements Scale {
    static double absoluteZero = 273.15;
    static int iceMeltingTemperature = 32;

    @Override
    public double convertCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFahrenheit(double temperature) {
        return 1.8 * temperature + iceMeltingTemperature;
    }

    @Override
    public double convertKelvin(double temperature) {
        return temperature + absoluteZero;
    }

    @Override
    public String toString() {
        return "Цельсий";
    }
}