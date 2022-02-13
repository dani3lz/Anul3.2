package AbstractFactory;

import AbstractFactory.Components.AMD;
import AbstractFactory.Components.Nvidia;

public class GPUAbstractFactory implements AbstractFactory<GPU>{

    @Override
    public GPU createComponent(String corporation, String model, double rank, int price, int memory, String type) {
        if(corporation.equalsIgnoreCase("Nvidia")){
            return new Nvidia(model, rank, price, memory, type);
        } else if(corporation.equalsIgnoreCase("AMD")){
            return new AMD(model, rank, price, memory, type);
        }
        return null;
    }
}
