package labs.lab6;

import java.util.ArrayList;

public class IModel {
    Entity selected;
    ArrayList<Subscriber> subs;


    public IModel() {
        selected = null;
        subs = new ArrayList<>();
    }

    public Entity getSelected() {
        return selected;
    }

    public void setSelected(Entity selected) {
        this.selected = selected;
        notifySubscribers();
    }

    public void unselect() {
        selected = null;
        notifySubscribers();
    }

    public void addSubscriber(Subscriber subscriber) {
        subs.add(subscriber);
    }

    private void notifySubscribers() {
        subs.forEach(Subscriber::modelChanged);
    }
}
