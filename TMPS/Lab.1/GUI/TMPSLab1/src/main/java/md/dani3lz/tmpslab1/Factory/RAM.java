package md.dani3lz.tmpslab1.Factory;

import java.util.ArrayList;
import java.util.List;

public class RAM implements Components{
    public static List<Components> RAMList= new ArrayList<>();
    private final int price;
    private final String model;
    private final String corporation;
    private final double ranking;
    private final int year;

    public RAM(String corporation, String model, int year, double ranking, int price){
        this.corporation = corporation;
        this.model = model;
        this.year = year;
        this.ranking = ranking;
        this.price = price;
        RAMList.add(this);
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
