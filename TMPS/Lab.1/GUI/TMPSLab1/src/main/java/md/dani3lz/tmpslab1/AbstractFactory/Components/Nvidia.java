package md.dani3lz.tmpslab1.AbstractFactory.Components;

import md.dani3lz.tmpslab1.AbstractFactory.AbstractComponents;
import md.dani3lz.tmpslab1.AbstractFactory.GPU;

import java.util.ArrayList;
import java.util.List;

public class Nvidia implements GPU {
    public static List<AbstractComponents> Nvidias = new ArrayList<>();
    private String model;
    private double rank;
    private int price;
    private int memory;

    public Nvidia(String model, double rank, int price, int memory, String type){
        this.model = model;
        this.rank = rank;
        this.price = price;
        this.memory = memory;
        Nvidias.add(this);
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
