package ru.academits.maksimenko.model;

public class FahrenheitScale implements Scale {
    static double absoluteZero = 273.15;
    static int iceMeltingTemperature = 32;

    @Override
    public double convertCelsius(double temperature) {
        return (temperature - iceMeltingTemperature) * 5.0 / 9.0;
    }

    @Override
    public double convertFahrenheit(double temperature) {
        return temperature;
    }

    @Override
    public double convertKelvin(double temperature) {
        return (temperature - iceMeltingTemperature) / 1.8 + absoluteZero;
    }

    @Override
    public String toString() {
        return "Фаренгейт";
    }
}