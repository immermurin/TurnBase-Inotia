package Characters;

public class NakiriAyame extends Characters {
    public NakiriAyame() {
        super("Nakiri Ayame", 95, 26);
    }

    @Override
    public void specialMove() {
        System.out.println(name + " performs Oni Slash!");
    }
}
