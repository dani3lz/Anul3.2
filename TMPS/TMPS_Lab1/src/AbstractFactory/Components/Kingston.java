package AbstractFactory.Components;

import AbstractFactory.AbstractComponents;
import AbstractFactory.RAM;

import java.util.ArrayList;
import java.util.List;

public class Kingston implements RAM {
    public static List<AbstractComponents> kingstons = new ArrayList<>();
    private String model;
    private double rank;
    private int price;
    private int memory;

    public Kingston(String model, double rank, int price, int memory, String type){
        this.model = model;
        this.rank = rank;
        this.price = price;
        this.memory = memory;
        kingstons.add(this);
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
