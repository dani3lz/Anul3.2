package state;

public class Selling implements StoreActivity {
    @Override
    public void work() {
        System.out.println("Se deservesc clienti care cumpara componente");
    }
}
