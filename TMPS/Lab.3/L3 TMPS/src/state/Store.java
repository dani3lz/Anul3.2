package state;

public class Store {
    StoreActivity storeActivity;

    public void setStoreActivity(StoreActivity storeActivity) {
        this.storeActivity = storeActivity;
    }

    public void changeStoreActivity(){
        if(storeActivity instanceof Closing){
            setStoreActivity(new Opening());
        }else if (storeActivity instanceof Opening){
            setStoreActivity(new Sorting());
        }else if (storeActivity instanceof Sorting){
            setStoreActivity(new Selling());
        }else if (storeActivity instanceof Selling){
            setStoreActivity(new Closing());
        }
    }
    public void work(){
        storeActivity.work();
    }
}
