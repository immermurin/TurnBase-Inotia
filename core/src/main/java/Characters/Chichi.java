package Characters;

public class Chichi extends Characters {
    public Chichi() {
        super("Chichi", 100, 22);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " performs a Lightning Strike!");
    }
}
