package Factory;

public class GPUFactory implements ComponentsFactory {
    @Override
    public Components createComponent(String corporation, String model, int year, double ranking, int price) {
        return new GPU(corporation, model, year, ranking, price);
    }
}
