package ru.academits.temperature_conversion.maksimenko.model;

public class TemperaturesConversionLogic implements TemperaturesConversion {
    private final Scale[] scales;

    public TemperaturesConversionLogic() {
        scales = new Scale[]{
                new CelsiusScale(),
                new FahrenheitScale(),
                new KelvinScale()};
    }

    public double convertTemperature(double temperature, Scale original, Scale resulting) {
        if (original == scales[0] && resulting == scales[1]) {
            return original.convertFahrenheit(temperature);
        }

        if (original == scales[0] && resulting == scales[2]) {
            return original.convertKelvin(temperature);
        }

        if (original == scales[1] && resulting == scales[0]) {
            return original.convertCelsius(temperature);
        }

        if (original == scales[1] && resulting == scales[2]) {
            return original.convertKelvin(temperature);
        }

        if (original == scales[2] && resulting == scales[0]) {
            return original.convertCelsius(temperature);
        }

        if (original == scales[2] && resulting == scales[1]) {
            return original.convertFahrenheit(temperature);
        }

        return temperature;
    }

    public Scale[] getScalesList() {
        return scales;
    }

    public Scale getInitialScale(){
        return scales[0];
    }
}