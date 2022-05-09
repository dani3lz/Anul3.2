package strategy;

public class Closing implements StoreActivity {
    @Override
    public void work() {
        System.out.println("Magazinul se inchide...");
    }
}
