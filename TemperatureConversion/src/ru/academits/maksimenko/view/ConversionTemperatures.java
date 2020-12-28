package ru.academits.maksimenko.view;

import ru.academits.maksimenko.model.Scale;

import java.util.Objects;

class ConversionTemperatures {
    private final Scale temperatureScale;

    private ResultPanel resultPanel;

    private String initialTemperature = "Цельсий";
    private String endTemperature = "Цельсий";

    public ConversionTemperatures(Scale temperatureScale) {
        this.temperatureScale = temperatureScale;
    }

    public void conversion(double temperature) {
        String celsius = "Цельсий";
        String kelvin = "Кельвин";
        String fahrenheit = "Фаренгейт";

        if (Objects.equals(initialTemperature, celsius) && Objects.equals(endTemperature, kelvin)) {
            temperature = temperatureScale.convertCelsiusToKelvinTemperature(temperature);
        }

        if (Objects.equals(initialTemperature, celsius) && Objects.equals(endTemperature, fahrenheit)) {
            temperature = temperatureScale.convertCelsiusToFahrenheitTemperature(temperature);
        }

        if (Objects.equals(initialTemperature, kelvin) && Objects.equals(endTemperature, celsius)) {
            temperature = temperatureScale.convertKelvinToCelsiusTemperature(temperature);
        }

        if (Objects.equals(initialTemperature, kelvin) && Objects.equals(endTemperature, fahrenheit)) {
            temperature = temperatureScale.convertKelvinToFahrenheitTemperature(temperature);
        }

        if (Objects.equals(initialTemperature, fahrenheit) && Objects.equals(endTemperature, celsius)) {
            temperature = temperatureScale.convertFahrenheitToCelsiusTemperature(temperature);
        }

        if (Objects.equals(initialTemperature, fahrenheit) && Objects.equals(endTemperature, kelvin)) {
            temperature = temperatureScale.convertFahrenheitToKelvinTemperature(temperature);
        }

        resultPanel.setResultPanel(temperature);
    }

    public void setInitialTemperature(String initialTemperature) {
        this.initialTemperature = initialTemperature;
    }

    public void setEndTemperature(String endTemperature) {
        this.endTemperature = endTemperature;
    }

    public void setResultPanel(ResultPanel resultPanel) {
        this.resultPanel = resultPanel;
    }
}