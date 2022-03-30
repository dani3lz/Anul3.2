import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Products {
    private static final List<Products> products = new ArrayList<>();

    private int ID;
    private String title;
    private int Price;
    private int categoryId;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return Price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public static void show() {
        for (Products value : products) {
            System.out.println("////////////////////");
            System.out.println("ID: " + value.getID());
            System.out.println("Title: " + value.getTitle());
            System.out.println("Price: " + value.getPrice());
            System.out.println("CategoryID: " + value.getCategoryId());
            System.out.println();
        }
        products.clear();
    }

    public static void DeserializationProducts(String response) {
        JsonArray array = Json.createReader(new StringReader(response)).readArray();
        Products product;
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.getJsonObject(i);
            product = new Products();
            product.setID(object.getInt("id"));
            try {
                product.setTitle(object.getString("title"));
            } catch (Exception e){
                product.setTitle("null");
            }
            product.setPrice(object.getInt("price"));
            product.setCategoryId(object.getInt("categoryId"));
            products.add(product);
        }
        show();
    }
}
