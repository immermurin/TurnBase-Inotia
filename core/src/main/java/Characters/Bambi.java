package Characters;

public class Bambi extends Characters {
    public Bambi() {
        super("Bambi", 90, 25);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " uses Nature's Grace!");
    }
}
