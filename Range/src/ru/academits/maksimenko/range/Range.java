package ru.academits.maksimenko.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    /**
     * @noinspection unused
     */
    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    /**
     * @noinspection unused
     */
    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public Range getIntersection(Range range) {
        if (range.to <= from || to <= range.from) {
            return null;
        }

        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {
        if (range.to < from || to < range.from) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (range.from > from && range.to < to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        if (range.from > from && range.to >= to && range.from < to) {
            return new Range[]{new Range(from, range.from)};
        }

        if (range.from <= from && range.to < to && range.to > from) {
            return new Range[]{new Range(range.to, to)};
        }

        if (range.from <= from && to <= range.to) {
            return new Range[0];
        }

        return new Range[]{new Range(from, to)};
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    @Override
    public String toString() {
        return "{" + from + ", " + to + "}";
    }
}