package ru.academits.maksimenko.shape;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        final int oppositeSidesCount = 2;

        return oppositeSidesCount * (width + height);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) o;

        return width == rectangle.width && height == rectangle.height;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(width);

        return prime * hash + Double.hashCode(height);
    }

    @Override
    public String toString() {
        return "Прямоугольник с размерами: ширина - " + width + ", длинна - " + height;
    }
}