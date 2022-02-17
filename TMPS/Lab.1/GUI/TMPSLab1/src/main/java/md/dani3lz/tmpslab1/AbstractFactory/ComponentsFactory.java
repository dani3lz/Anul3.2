package md.dani3lz.tmpslab1.AbstractFactory;

public class ComponentsFactory {
    public void getFactory(String corporation, String model, double rank, int price, int memory, String type){
        if(type.equalsIgnoreCase("CPU")){
            new CPUAbstractFactory().createComponent(corporation, model, rank, price, memory, type);
        } else if(type.equalsIgnoreCase("GPU")){
            new GPUAbstractFactory().createComponent(corporation, model, rank, price, memory, type);
        } else if(type.equalsIgnoreCase("RAM")){
            new RAMAbstractFactory().createComponent(corporation, model, rank, price, memory, type);
        }
    }
}
