package ru.academits.temperature_conversion.maksimenko.shape_main;

import ru.academits.temperature_conversion.maksimenko.shape.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }
}