package ru.academits.maksimenko.classes;

public class House {
    private double length;
    private double width;
    private String color;

    public House(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public House(double length, double width, String color) {
        this.length = length;
        this.width = width;
        this.color = color;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double houseArea() {
        return length * width;
    }

    public String[] getRecommendedColors() {
        return new String[]{
                "Brown",
                "White",
                "Gray"
        };
    }
}