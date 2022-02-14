package md.dani3lz.tmpslab1.Factory;

public interface ComponentsFactory {
    public Components createComponent(String corporation, String model, int year, double ranking, int price);
}
