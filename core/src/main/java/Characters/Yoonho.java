package Characters;

public class Yoonho extends Characters {
    public Yoonho() {
        super("Yoonho", 130, 20);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " activates Spirit Guardian!");
    }
}
