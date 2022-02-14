package md.dani3lz.tmpslab1.AbstractFactory;

import md.dani3lz.tmpslab1.AbstractFactory.Components.HyperX;
import md.dani3lz.tmpslab1.AbstractFactory.Components.Kingston;

public class RAMAbstractFactory implements AbstractFactory<RAM>{

    @Override
    public RAM createComponent(String corporation, String model, double rank, int price, int memory, String type) {
        if(corporation.equalsIgnoreCase("HyperX")){
            return new HyperX(model, rank, price, memory, type);
        } else if(corporation.equalsIgnoreCase("Kingston")){
            return new Kingston(model, rank, price, memory, type);
        }
        return null;
    }
}
