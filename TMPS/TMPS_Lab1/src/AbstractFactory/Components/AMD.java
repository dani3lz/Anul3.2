package AbstractFactory.Components;

import AbstractFactory.AbstractComponents;
import AbstractFactory.GPU;
import AbstractFactory.CPU;

import java.util.ArrayList;
import java.util.List;

public class AMD implements GPU, CPU {
    public static List<AbstractComponents> AMDsCPU = new ArrayList<>();
    public static List<AbstractComponents> AMDsGPU = new ArrayList<>();
    private String model;
    private double rank;
    private int price;
    private int memory;
    private boolean isCPU;

    public AMD(String model, double rank, int price, int memory, String type){
        this.model = model;
        this.rank = rank;
        this.price = price;
        this.memory = memory;
        if(type.equalsIgnoreCase("CPU")){
            AMDsCPU.add(this);
        } else if(type.equalsIgnoreCase("GPU")){
            AMDsGPU.add(this);
        }
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
