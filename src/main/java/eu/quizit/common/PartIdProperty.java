package eu.quizit.common;

public enum PartIdProperty {

    PREFIX("prefix"), ID("id"), X("x"), Y("y");

    private String name;

    private PartIdProperty(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
