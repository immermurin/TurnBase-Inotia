package Characters;

public class Barathrum extends Characters {
    public Barathrum() {
        super("Barathrum", 130, 18);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " charges with Spirit Breaker!");
    }
}
