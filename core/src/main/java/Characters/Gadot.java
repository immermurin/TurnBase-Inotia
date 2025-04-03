package Characters;

public class Gadot extends Characters {
    public Gadot() {
        super("Gadot", 120, 19);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " uses Iron Fortress!");
    }
}
