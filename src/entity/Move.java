package entity;

public class Move {

    public String name;
    public int power;
    public int critRate;
    public int speed;
    public int cost;

    public Move(String name, int power, int critRate, int speed, int cost) {
        this.name = name;
        this.power = power;
        this.critRate = critRate;
        this.speed = speed;
        this.cost = cost;
    }
}
