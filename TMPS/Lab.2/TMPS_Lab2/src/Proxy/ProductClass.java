package Proxy;

public class ProductClass implements Product {
    private final String jsonFile;

    ProductClass(String jsonFile){
        this.jsonFile = jsonFile;
        loadProductsFromJson();
    }

    private void loadProductsFromJson(){
        System.out.println("Json file: " + jsonFile + " // Loading...");
    }

    @Override
    public void showProductFromJson() {
        System.out.println("Displaying: " + jsonFile);
    }
}
