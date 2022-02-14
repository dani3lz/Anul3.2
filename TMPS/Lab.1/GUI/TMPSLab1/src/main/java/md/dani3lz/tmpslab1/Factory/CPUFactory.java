package md.dani3lz.tmpslab1.Factory;

public class CPUFactory implements ComponentsFactory {
    @Override
    public Components createComponent(String corporation, String model, int year, double ranking, int price) {
        return new CPU(corporation, model, year, ranking, price);
    }
}
