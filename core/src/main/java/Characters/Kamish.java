package Characters;

public class Kamish extends Characters {
    public Kamish() {
        super("Kamish", 125, 17);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " summons a Dark Flame!");
    }
}
