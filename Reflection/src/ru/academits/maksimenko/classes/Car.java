package ru.academits.maksimenko.classes;

public class Car {
    private String brand;
    private double price;
    private double motorPower;
    private String color;

    public Car(double price, String color) {
        this.price = price;
        this.color = color;
    }

    public Car(double price, double motorPower, String color) {
        this.price = price;
        this.motorPower = motorPower;
        this.color = color;
    }

    public Car(String brand, double price, double motorPower, String color) {
        checkBrandName(brand);

        this.brand = brand;
        this.price = price;
        this.motorPower = motorPower;
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public double getMotorPower() {
        return motorPower;
    }

    public void setMotorPower(double motorPower) {
        this.motorPower = motorPower;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private void checkBrandName(String name) {
        String[] brandsArray = new String[]{"Toyota", "Honda", "Nissan", "Mitsubishi"};

        boolean contains = false;

        for (String brand : brandsArray) {
            if (brand.equals(name)) {
                contains = true;

                break;
            }
        }

        if (!contains) {
            throw new IllegalArgumentException("Введенный бренд автомобиля отсутствует: " + name);
        }
    }
}