package Singletone;

import AbstractFactory.*;
import AbstractFactory.Components.*;
import Factory.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class ComponentsStore {
    private static ComponentsStore instance;
    private ComponentsStore(){}
    public static synchronized ComponentsStore getInstance() {
        if (instance == null) {
            instance = new ComponentsStore();
        }
        return instance;
    }

    public void showComponents(int nr, String type, String corporation){
        if(type.equalsIgnoreCase("Factory")) {
            try {
                String component = "";
                if (nr == 1) {
                    component = "CPU";
                } else if (nr == 2) {
                    component = "GPU";
                } else if (nr == 3) {
                    component = "RAM";
                }
                List<Components> list = getComponentsList(nr);
                System.out.println(component + ":");
                for (Components c : list) {
                    System.out.println("--------------------------------------");
                    System.out.println("Corporation:\t" + c.corporation());
                    System.out.println("Model:\t\t\t" + c.model());
                    System.out.println("Year:\t\t\t" + c.year());
                    System.out.println("Ranking:\t\t" + c.ranking());
                    System.out.println("Price(MDL):\t\t" + c.price());
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } else if(type.equalsIgnoreCase("AbstractFactory")){
            try {
                String component = "";
                if (nr == 1) {
                    component = "CPU";
                } else if (nr == 2) {
                    component = "GPU";
                } else if (nr == 3) {
                    component = "RAM";
                }
                List<AbstractComponents> list = getAbstractComponentsList(nr, corporation);
                System.out.println(component + ":");
                for (AbstractComponents c : list) {
                    System.out.println("--------------------------------------");
                    System.out.println("Corporation:\t" + corporation);
                    System.out.println("Model:\t\t\t" + c.model());
                    System.out.println("Ranking:\t\t" + c.ranking());
                    System.out.println("Memory:\t\t\t" + c.memory());
                    System.out.println("Price(MDL):\t\t" + c.price());
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void setComponents(int nr, String type, String corporation){
        if(type.equalsIgnoreCase("Factory")) {
            try {
                System.out.print("Corporation:\t");
                String corporationLocal = new Scanner(System.in).next();
                System.out.print("Model:\t");
                String model = new Scanner(System.in).nextLine();
                System.out.print("Year:\t");
                int year = new Scanner(System.in).nextInt();
                System.out.print("Ranking:\t");
                double rank = new Scanner(System.in).nextDouble();
                System.out.print("Price(MDL):\t");
                int price = new Scanner(System.in).nextInt();
                if (nr == 1) {
                    new Factory.CPUFactory().createComponent(corporationLocal, model, year, rank, price);
                } else if (nr == 2) {
                    new Factory.GPUFactory().createComponent(corporationLocal, model, year, rank, price);
                } else if (nr == 3) {
                    new Factory.RAMFactory().createComponent(corporationLocal, model, year, rank, price);
                }
                System.out.println("\n\nSuccessfully.");
            } catch (Exception e){
                e.printStackTrace();
            }
        } else if (type.equalsIgnoreCase("AbstractFactory")) {
            try{
                System.out.print("Model:\t");
                String model = new Scanner(System.in).nextLine();
                System.out.print("Ranking:\t");
                double rank = new Scanner(System.in).nextDouble();
                System.out.print("Price(MDL):\t");
                int price = new Scanner(System.in).nextInt();
                System.out.print("Memory:\t");
                int memory = new Scanner(System.in).nextInt();
                if (nr == 1) { // CPU
                    new CPUAbstractFactory().createComponent(corporation, model, rank, price, memory, "CPU");
                } else if (nr == 2) { // GPU
                    new GPUAbstractFactory().createComponent(corporation, model, rank, price, memory, "GPU");
                } else if (nr == 3) { // RAM
                    new RAMAbstractFactory().createComponent(corporation, model, rank, price, memory, "RAM");
                }
                System.out.println("\n\nSuccessfully.");
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void deleteComponents(int nr, String type, String corporation){
        if(type.equalsIgnoreCase("Factory")) {
            try {
                List<Components> list = getComponentsList(nr);
                System.out.print("Model name:\t");
                String name = new Scanner(System.in).next();
                System.out.print("Year:\t");
                int year = new Scanner(System.in).nextInt();
                int i = 0;
                for (Components c : list) {
                    if (c.model().contains(name) && year == c.year()) {
                        list.remove(i);
                        System.out.println("\n\nSuccessfully.");
                        break;
                    }
                    i++;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } else if(type.equalsIgnoreCase("AbstractFactory")) {
            try {
                List<AbstractComponents> list = getAbstractComponentsList(nr, corporation);
                System.out.print("Model name:\t");
                String name = new Scanner(System.in).next();
                System.out.print("Memory:\t");
                int memory = new Scanner(System.in).nextInt();
                int i = 0;
                for (AbstractComponents ac : list) {
                    if (ac.model().contains(name) && memory == ac.memory()) {
                        list.remove(i);
                        System.out.println("\n\nSuccessfully.");
                        break;
                    }
                    i++;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public List<AbstractComponents> getAbstractComponentsList(int nr, String corporation){
        List<AbstractComponents> list = new ArrayList<>();
        try {
            if (nr == 1) { // CPU
                if (corporation.equalsIgnoreCase("AMD")) {
                    list = AMD.AMDsCPU;
                } else if (corporation.equalsIgnoreCase("Intel")) {
                    list = Intel.Intels;
                }
            } else if (nr == 2) { // GPU
                if (corporation.equalsIgnoreCase("Nvidia")) {
                    list = Nvidia.Nvidias;
                } else if (corporation.equalsIgnoreCase("AMD")) {
                    list = AMD.AMDsGPU;
                }
            } else if (nr == 3) { // RAM
                if (corporation.equalsIgnoreCase("HyperX")) {
                    list = HyperX.hyperXs;
                } else if (corporation.equalsIgnoreCase("Kingston")) {
                    list = Kingston.kingstons;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Components> getComponentsList(int nr){
        List<Components> list = new ArrayList<>();
        try {
            if (nr == 1) {
                list = Factory.CPU.CPUList;
            } else if (nr == 2) {
                list = Factory.GPU.GPUList;
            } else if (nr == 3) {
                list = Factory.RAM.RAMList;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
