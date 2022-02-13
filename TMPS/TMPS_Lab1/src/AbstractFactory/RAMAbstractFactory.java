package AbstractFactory;

import AbstractFactory.Components.HyperX;
import AbstractFactory.Components.Kingston;

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
