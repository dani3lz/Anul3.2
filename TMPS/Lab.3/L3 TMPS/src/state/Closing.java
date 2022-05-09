package state;

public class Closing implements StoreActivity {
    @Override
    public void work() {
        System.out.println("Se inchide magazinul...");
    }
}
