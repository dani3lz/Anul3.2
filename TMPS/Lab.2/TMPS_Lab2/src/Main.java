import Adapter.*;
import Adapter.Notebooks.*;
import Decorator.*;
import Proxy.*;
import Facade.*;

import java.util.Scanner;

public class Main {

    static void useHDMI(HDMI notebook){
        notebook.usePortHDMI();
        notebook.show();
    }
     static void useVGA(VGA notebook){
        notebook.usePortVGA();
        notebook.show();
     }

     static void PCMethod(FacadeController controller){
        boolean run = true;
        while (run){
            System.out.println("\n\n\n1. Start PC.");
            System.out.println("2. Restart PC.");
            System.out.println("3. Stop PC.");
            System.out.print("Number: ");
            int nr = new Scanner(System.in).nextInt();
            switch (nr){
                case 1: {
                    controller.startPC();
                    break;
                }
                case 2: {
                    controller.restartPC();
                    break;
                }
                case 3: {
                    controller.stopPC();
                    run = false;
                    break;
                }
            }
        }
     }

    public static void main(String[] args) {
        boolean run = true;
        while (run){
            System.out.println("\n\n\n1. Proxy");
            System.out.println("2. Decorator");
            System.out.println("3. Adapter");
            System.out.println("4. Facade");
            System.out.println("5. Exit");
            System.out.print("Numarul: ");
            int nr = new Scanner(System.in).nextInt();
            switch (nr){
                case 1: {
                    Product product1 = new ProxyProduct("products1.json");
                    Product product2 = new ProxyProduct("products2.json");

                    product1.showProductFromJson();
                    product1.showProductFromJson();
                    product2.showProductFromJson();
                    product2.showProductFromJson();
                    break;
                }
                case 2:{
                    Zoom zoomDecorator = new FullZoom(new SimpleZoom());
                    System.out.println(zoomDecorator.showReview());
                    break;
                }
                case 3:{
                    Lenova lenova = new Lenova();
                    Acer acer = new Acer();

                    System.out.println("Use VGA on Lenovo:");
                    useVGA(lenova);
                    System.out.println("Use HDMI on Acer:");
                    useHDMI(acer);

                    System.out.println("Use HDMI on Lenovo");
                    useHDMI(new VGAtoHDMIAdapter(lenova));
                    break;
                }
                case 4: {
                    PCMethod(new FacadeController());
                    break;
                }
                case 5: {
                    run = false;
                    break;
                }
                default: {
                    System.out.println("Nu exista asa optiune\n\n\n");
                    break;
                }
            }
        }

    }
}
