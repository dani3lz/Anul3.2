import javax.json.Json;
import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Method {
    private static HttpURLConnection con;

    public static void POST_Product(String InputURL, int ID, JsonObject value) throws IOException {
        URL url = new URL(InputURL + "/" + ID + "/products");
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = String.valueOf(value).getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        int status = con.getResponseCode();
        if (status == 200) {
            System.out.println("Produsul a fost adaugat cu succes!");
        } else if (status == 404) {
            System.out.println("Not found - 404");
        } else if (status == 500) {
            System.out.println("Probleme cu serverul");
        }
    }

    public static String GET_Products(String InputURL, int ID) throws IOException {
        URL url = new URL(InputURL + "/" + ID + "/products");
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader bufferedReader;
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        if (status == 200) {
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } else if (status == 404) {
            System.out.println("Not found - 404");
        } else if (status == 500) {
            System.out.println("Probleme cu serverul");
        }
        return null;
    }

    public static String GET_Category(String InputURL, int ID) throws IOException {
        URL url;
        if (ID == -1) {
            url = new URL(InputURL);
        } else {
            url = new URL(InputURL + "/" + ID);
        }
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader bufferedReader;
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        if (status == 200) {
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } else if (status == 404) {
            System.out.println("Not found - 404");
        } else if (status == 500) {
            System.out.println("Probleme cu serverul");
        }
        return null;
    }

    public static void POST_Category(String InputURL, JsonObject value) throws IOException {
        URL url = new URL(InputURL);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = String.valueOf(value).getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        int status = con.getResponseCode();
        if (status == 200) {
            System.out.println("Categoria a fost adaugata cu succes!");
        } else if (status == 404) {
            System.out.println("Not found - 404");
        } else if (status == 500) {
            System.out.println("Probleme cu serverul");
        } else if (status == 409){
            System.out.println("Categorie cu asa titlu deja exista!");
        }
    }

    public static void DELETE_Category(String InputURL, int ID) throws IOException {
        URL url = new URL(InputURL + "/" + ID);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        int status = con.getResponseCode();
        if (status == 200) {
            System.out.println("Categoria s-a sters cu succes!");
        } else if (status == 404) {
            System.out.println("Not found - 404");
        } else if (status == 500) {
            System.out.println("Probleme cu serverul");
        }
    }

    public static int GET_Search(String InputURL, String name) throws IOException {
        URL url = new URL(InputURL + "/search?categoryName=" + name);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader bufferedReader;
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        if (status == 200) {
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return Integer.parseInt(stringBuilder.toString());
        } else if (status == 404) {
            System.out.println("Not found - 404");
            return -404;
        } else if (status == 500) {
            System.out.println("Probleme cu serverul");
            return -500;
        }
        return -1;
    }

    public static void PUT_Category(int ID, String title) throws IOException {
        URL url = new URL("https://localhost:44370/api/Category/" + ID);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        JsonObject value = Json.createObjectBuilder()
                .add("title", title)
                .build();
        con.setDoOutput(true);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = String.valueOf(value).getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        int status = con.getResponseCode();
        if (status == 200) {
            System.out.println("Titlu categoriei a fost schimbat cu succes!");
        } else if (status == 404) {
            System.out.println("Not found - 404");
        } else if (status == 500) {
            System.out.println("Probleme cu serverul");
        } else if (status == 409){
            System.out.println("Categorie cu asa titlu deja exista!");
        }
    }

}
