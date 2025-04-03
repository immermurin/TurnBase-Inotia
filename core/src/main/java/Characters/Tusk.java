package Characters;

public class Tusk extends Characters {
    public Tusk() {
        super("Tusk", 120, 15);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " uses Walrus Punch!");
    }
}
