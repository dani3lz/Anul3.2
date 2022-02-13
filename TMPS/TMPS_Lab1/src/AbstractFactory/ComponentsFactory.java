package AbstractFactory;

public class ComponentsFactory {
    public static AbstractFactory getFactory(String component){
        if(component.equalsIgnoreCase("CPU")){
            return new CPUAbstractFactory();
        } else if(component.equalsIgnoreCase("GPU")){
            return new GPUAbstractFactory();
        } else if(component.equalsIgnoreCase("RAM")){
            return new RAMAbstractFactory();
        }
        return null;
    }
}
