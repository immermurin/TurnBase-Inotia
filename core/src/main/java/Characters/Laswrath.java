package Characters;

public class Laswrath extends Characters {
    public Laswrath() {
        super("Laswrath", 115, 21);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " unleashes Wrath of the Ancients!");
    }
}
