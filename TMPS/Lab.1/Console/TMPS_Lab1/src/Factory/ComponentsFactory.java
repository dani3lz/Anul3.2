package Factory;

public interface ComponentsFactory {
    public Components createComponent(String corporation, String model, int year, double ranking, int price);
}
