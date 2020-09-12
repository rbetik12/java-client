package io.rbetik12.models;

public class Label implements Comparable {
    private final String name;

    public Label(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        Label label = (Label) o;
        return name.compareTo(label.name);
    }
}
