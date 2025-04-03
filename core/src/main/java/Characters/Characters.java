package Characters;

public abstract class Characters {
    protected String name;
    protected int health;
    protected int attackPower;

    public Characters(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public abstract void specialMove();

    public void attack(Characters target) {
        System.out.println(name + " attacks " + target.name + " for " + attackPower + " damage!");
        target.health -= attackPower;
    }

    public void displayStats() {
        System.out.println(name + " - HP: " + health + ", Attack: " + attackPower);
    }
}
