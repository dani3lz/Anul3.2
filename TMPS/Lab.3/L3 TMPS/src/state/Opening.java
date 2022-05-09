package state;

public class Opening implements StoreActivity {
    @Override
    public void work() {
        System.out.println("Magazinul se deschide...");
    }
}
