package Factory;

import java.util.ArrayList;
import java.util.List;

public class CPU implements Components {
    public static List<Components> CPUList= new ArrayList<>();
    private final int price;
    private final String model;
    private final String corporation;
    private final double ranking;
    private final int year;

    public CPU(String corporation, String model, int year, double ranking, int price){
        this.corporation = corporation;
        this.model = model;
        this.year = year;
        this.ranking = ranking;
        this.price = price;
        CPUList.add(this);
    }

    @Override
    public int price() {
        return price;
    }

    @Override
    public String model() {
        return model;
    }

    @Override
    public int year() {
        return year;
    }

    @Override
    public double ranking() {
        return ranking;
    }

    @Override
    public String corporation() {
        return corporation;
    }
}
