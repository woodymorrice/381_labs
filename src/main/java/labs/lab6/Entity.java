package labs.lab6;

public class Entity {
    private double x, y;
    private double r;


    public Entity(double nx, double ny) {
        x = nx;
        y = ny;
        r = 10;
    }

    public boolean contains(double mx, double my) {

        return Math.hypot(x - mx, y - my) <= r;
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }
}