package Characters;

public class Makoi extends Characters {
    public Makoi() {
        super("Makoi", 110, 23);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " throws a Phantom Dagger!");
    }
}
