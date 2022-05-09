import Iterator.Iterator;
import Iterator.Product;
import state.Closing;
import state.Store;
import state.StoreActivity;
import strategy.Opening;
import strategy.Selling;
import strategy.Sorting;
import template.StoreTemplate;
import template.Model8;
import template.Model16;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu();
    }
    public static void Menu(){
        boolean run = true;
        while (run){
            System.out.println("\n\n\n1. Iterator");
            System.out.println("2. State");
            System.out.println("3. Strategy");
            System.out.println("4. Template");
            System.out.println("5. Exit");
            System.out.print("> Option: ");
            int option = new Scanner(System.in).nextInt();
            switch (option){
                case 1:{
                    iterator();
                    break;
                }
                case 2:{
                    state();
                    break;
                }
                case 3:{
                    strategy();
                    break;
                }
                case 4:{
                    template();
                    break;
                }
                case 5:{
                    run = false;
                    break;
                }
                default:{
                    System.out.println("\n\nNu exista asa optiune.");
                    break;
                }
            }
        }
    }

    public static void iterator(){
        String[] componente = {"GPU", "CPU", "RAM", "Mouse", "Keyboard"};

        Product product = new Product("PC", componente);

        Iterator iterator = product.getIterator();
        System.out.println("\n> Product: " + product.getName());
        System.out.println("Components:");

        while (iterator.hasNext()){
            System.out.println(iterator.next().toString() + "");
        }
    }

    public static void state(){
        StoreActivity storeActivity = new state.Opening();
        Store store = new Store();

        store.setStoreActivity(storeActivity);
        for (int i=0 ; i<12; i++) {
            store.work();
            store.changeStoreActivity();
        }
    }

    public static void strategy(){
        strategy.Store store = new strategy.Store();

        store.setStoreActivity(new Opening());
        store.executeStoreActivity();

        store.setStoreActivity(new Sorting());
        store.executeStoreActivity();

        store.setStoreActivity(new Selling());
        store.executeStoreActivity();

        store.setStoreActivity(new strategy.Closing());
        store.executeStoreActivity();
    }

    public static void template(){
        StoreTemplate model8 = new Model8();
        StoreTemplate model16 = new Model16();

        model8.showContent();

        System.out.println("\n==================================\n");

        model16.showContent();
    }
}
