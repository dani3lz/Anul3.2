import javax.json.Json;
import javax.json.JsonObject;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
       Main.Menu();
    }

    public static void Menu() throws IOException {
        String url = "https://localhost:44370/api/Category/categories";
        while (true) {
            System.out.println("1. Show all categories");
            System.out.println("2. Create category");
            System.out.println("3. Show category");
            System.out.println("4. Delete category");
            System.out.println("5. Show products");
            System.out.println("6. Create product");
            System.out.println("7. Search category");
            System.out.println("8. Change category");
            System.out.println("9. Exit");
            System.out.print("Number: ");
            Scanner scanner = new Scanner(System.in);
            int nr = scanner.nextInt();
            System.out.println();System.out.println();System.out.println();System.out.println();
            switch (nr) {
                case 1: {
                    String response = Method.GET_Category(url, -1);
                    Category.DeserializationCategories(response);
                    break;
                }
                case 2: {
                    System.out.print("Title: "); String title = new Scanner(System.in).nextLine();
                    JsonObject json = Json.createObjectBuilder()
                            .add("title", title)
                            .build();
                    Method.POST_Category(url, json);
                    break;
                }
                case 3: {
                    System.out.print("Category ID: "); int id = scanner.nextInt();
                    String response = Method.GET_Category(url, id);
                    Category.DeserializationCategory(response);
                    break;
                }
                case 4: {
                    System.out.print("Category ID: "); int id = scanner.nextInt();
                    Method.DELETE_Category(url, id);
                    break;
                }
                case 5: {
                    System.out.print("Category ID: "); int id = scanner.nextInt();
                    String response = Method.GET_Products(url, id);
                    Products.DeserializationProducts(response);
                    break;
                }
                case 6: {
                    System.out.print("Category ID: "); int id = scanner.nextInt();
                    System.out.print("Product title: "); String title = new Scanner(System.in).nextLine();
                    System.out.print("Product price: "); int price = scanner.nextInt();
                    JsonObject json = Json.createObjectBuilder()
                            .add("title", title)
                            .add("price", price)
                            .build();
                    Method.POST_Product(url, id, json);
                    break;
                }
                case 7: {
                    System.out.print("Category name: "); String name = new Scanner(System.in).nextLine();
                    int id = Method.GET_Search(url, name);
                    if (id != -404 && id != -500){
                        System.out.println("ID Category: " + id);
                    }
                    break;
                }
                case 8: {
                    System.out.print("Category ID: "); int id = scanner.nextInt();
                    System.out.print("New Category title: "); String title = new Scanner(System.in).nextLine();
                    Method.PUT_Category(id, title);
                    break;
                }
                case 9: {
                    return;
                }
                default: {
                    System.out.println("Nu exista asa optiune!");
                    break;
                }
            }
            System.out.println();System.out.println();System.out.println();System.out.println();
        }
    }

}
