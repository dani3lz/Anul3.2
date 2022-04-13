package Proxy;

public class ProxyProduct implements Product{
    private final String jsonFile;
    private ProductClass product;

    public ProxyProduct(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    @Override
    public void showProductFromJson() {
        if(product == null){
            product = new ProductClass(jsonFile);
        }
        product.showProductFromJson();
    }
}
