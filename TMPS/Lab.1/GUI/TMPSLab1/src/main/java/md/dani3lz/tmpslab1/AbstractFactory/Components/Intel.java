package md.dani3lz.tmpslab1.AbstractFactory.Components;

import md.dani3lz.tmpslab1.AbstractFactory.AbstractComponents;
import md.dani3lz.tmpslab1.AbstractFactory.CPU;

import java.util.ArrayList;
import java.util.List;

public class Intel implements CPU {
    public static List<AbstractComponents> Intels = new ArrayList<>();
    private final String model;
    private final double rank;
    private final int price;
    private final int memory;

    public Intel(String model, double rank, int price, int memory, String type){
        this.model = model;
        this.rank = rank;
        this.price = price;
        this.memory = memory;
        Intels.add(this);
    }

    @Override
    public String model() {
        return model;
    }

    @Override
    public double ranking() {
        return rank;
    }

    @Override
    public int price() {
        return price;
    }

    @Override
    public int memory() {
        return memory;
    }
}
