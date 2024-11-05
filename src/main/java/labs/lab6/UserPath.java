package labs.lab6;

import java.util.ArrayList;

public class UserPath {
    private ArrayList<PathPoint> points;
    private boolean isComplete;

    public UserPath() {
        points = new ArrayList<>();
        isComplete = false;
    }

    public void addPoint(double x, double y) {
        points.add(new PathPoint(x, y));
    }

    public void clearPath() { points.clear(); }

    public ArrayList<PathPoint> getPoints() {
        return points;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void complete() {
        isComplete = true;
    }

    public void start(double x, double y) {
        clearPath();
        isComplete = false;
        addPoint(x, y);
    }
}
