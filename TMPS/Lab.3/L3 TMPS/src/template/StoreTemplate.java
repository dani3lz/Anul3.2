package template;

public abstract class StoreTemplate {
    public void showContent() {
        System.out.println("Componenta: HyperX");
        showStoreContent();
        System.out.println("Tipul: RAM");
    }
    public abstract void showStoreContent();
}
