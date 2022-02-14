package md.dani3lz.tmpslab1.AbstractFactory;

public interface AbstractFactory<T> {
    T createComponent(String corporation, String model, double rank, int price, int memory, String type);
}
