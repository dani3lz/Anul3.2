import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private static final List<Category> categories = new ArrayList<>();

    private int id;
    private String name;
    private int ItemsCount;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItemsCount(int itemCount) {
        this.ItemsCount = itemCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getItemsCount() {
        return ItemsCount;
    }

    public static void show() {
        for (Category value : categories) {
            System.out.println("////////////////////");
            System.out.println("ID: " + value.getId());
            System.out.println("Name: " + value.getName());
            System.out.println("ItemsCount: " + value.getItemsCount());
            System.out.println();
        }
        categories.clear();
    }

    public static void DeserializationCategories(String response) {
        JsonArray array = Json.createReader(new StringReader(response)).readArray();
        Category category;
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.getJsonObject(i);
            category = new Category();
            category.setId(object.getInt("id"));
            category.setName(object.getString("name"));
            category.setItemsCount(object.getInt("itemsCount"));
            categories.add(category);
        }
        show();
    }

    public static void DeserializationCategory(String response) {
        JsonArray array = Json.createReader(new StringReader(response)).readArray();
        JsonObject object = array.getJsonObject(0);
        System.out.println("////////////////////");
        System.out.println("ID: " + object.getInt("id"));
        System.out.println("Name: " + object.getString("name"));
        System.out.println("itemsCount: " + object.getInt("itemsCount"));
    }
}
