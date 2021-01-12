package ru.academits.maksimenko.model;

public class KelvinScale implements Scale {
    static double absoluteZero = 273.15;
    static int iceMeltingTemperature = 32;

    @Override
    public double convertCelsius(double temperature) {
        return temperature - absoluteZero;
    }

    @Override
    public double convertFahrenheit(double temperature) {
        return (temperature - absoluteZero) * 1.8 + iceMeltingTemperature;
    }

    @Override
    public double convertKelvin(double temperature) {
        return temperature;
    }

    @Override
    public String toString() {
        return "Кельвин";
    }
}