package labs.lab6;

import java.util.ArrayList;

public class Model {
    private ArrayList<Subscriber> subs;
    private ArrayList<Entity> entities;


    public Model() {
        subs = new ArrayList<>();
        entities = new ArrayList<>();
    }

    public boolean contains(double x, double y) {
        // alternative to forEach... look through each one of these and do something...
        return entities.stream().anyMatch(e -> e.contains(x, y));
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void addEntity(double x, double y) {
        entities.add(new Entity(x, y));
        notifySubscribers();
    }

    public Entity whichEntity(double x, double y) {
        // may return null.........
        return entities.stream().filter(e -> e.contains(x, y)).findFirst().orElse(null);
    }

    public ArrayList<Subscriber> getSubs() {
        return subs;
    }

    public void addSubscriber(Subscriber sub) {
        subs.add(sub);
    }

    private void notifySubscribers() {
        subs.forEach(Subscriber::modelChanged);
    }

    public void moveEntity(Entity e, double dx, double dy) {
        e.move(dx, dy);
        notifySubscribers();
    }

}