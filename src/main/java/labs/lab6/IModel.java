package labs.lab6;

import java.util.ArrayList;

public class IModel {
    Entity selected;
    private ArrayList<Subscriber> subs;
    private double viewLeft, viewTop;

    public IModel() {
        selected = null;
        subs = new ArrayList<>();
        viewLeft = 0;
        viewTop = 0;
    }

    public Entity getSelected() {
        return selected;
    }

    public void select(Entity e) {
        selected = e;
    }

    public void setSelected(Entity selected) {
        this.selected = selected;
        notifySubscribers();
    }

    public void unselect() {
        selected = null;
        notifySubscribers();
    }

    public boolean isSelected(Entity e) {
        return selected.equals(e);
    }

    public void addSubscriber(Subscriber subscriber) {
        subs.add(subscriber);
    }

    private void notifySubscribers() {
        subs.forEach(Subscriber::modelChanged);
    }

    public void moveViewportRight() {
        viewLeft += 10;
        notifySubscribers();
    }

    public void moveViewportLeft() {
        viewLeft -= 10;
        notifySubscribers();
    }

    public double getViewLeft() {
        return viewLeft;
    }

    public double getViewTop() {
        return viewTop;
    }

    public void setViewLeft(double d) {
        viewLeft = d;
    }

    public void setViewTop(double d) {
        viewTop = d;
    }
}
