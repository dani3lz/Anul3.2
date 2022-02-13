package AbstractFactory;

public interface AbstractFactory<T> {
    T createComponent(String corporation, String model, double rank, int price, int memory, String type);
}
