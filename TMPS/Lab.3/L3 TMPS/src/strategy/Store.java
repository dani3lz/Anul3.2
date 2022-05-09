package strategy;

public class Store {
    StoreActivity storeActivity;

    public void setStoreActivity(StoreActivity storeActivity) {
        this.storeActivity = storeActivity;
    }
    public void executeStoreActivity(){
        storeActivity.work();

    }
}
