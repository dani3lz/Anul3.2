package md.dani3lz.tmpslab1.Factory;

public class RAMFactory implements ComponentsFactory {
    @Override
    public Components createComponent(String corporation, String model, int year, double ranking, int price) {
        return new RAM(corporation, model, year, ranking, price);
    }
}
