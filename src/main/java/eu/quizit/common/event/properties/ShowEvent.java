package eu.quizit.common.event.properties;

public class ShowEvent {

    private Double x;
    private Double y;

    public ShowEvent(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

}
