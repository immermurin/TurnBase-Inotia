package Characters;

public class GawrGura extends Characters {
    public GawrGura() {
        super("GawrGura", 105, 24);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " summons the Tidal Wave!");
    }
}
