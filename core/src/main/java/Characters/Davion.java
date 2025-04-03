package Characters;

public class Davion extends Characters {
    public Davion() {
        super("Davion", 140, 15);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " transforms into a Dragon Knight!");
    }
}
