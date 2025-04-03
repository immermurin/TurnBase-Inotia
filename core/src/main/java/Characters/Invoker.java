package Characters;

public class Invoker extends Characters {
    public Invoker() {
        super("Invoker", 100, 20);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " casts Chaos Meteor!");
    }
}
