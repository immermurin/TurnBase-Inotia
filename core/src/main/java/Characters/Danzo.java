package Characters;

public class Danzo extends Characters {
    public Danzo() {
        super("Danzo", 110, 20);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " activates Izanagi!");
    }
}
