import Prototype.*;
import Singletone.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws RuntimeException {
        Main main = new Main();
        while (true) {
            System.out.println("\n\n\n\n");
            System.out.println("1. Factory");
            System.out.println("2. Abstract Factory");
            System.out.println("3. Prototype");
            System.out.println("4. Exit");
            System.out.print("Number:\t");
            int type = new Scanner(System.in).nextInt();
            if (type == 4) {
                break;
            } else {
                if(type == 1){
                    main.factoryMethod();
                } else if(type == 2){
                    main.abstractFactory();
                } else if(type == 3){
                    main.prototype();
                } else {
                    System.out.println("Nu exista asa varianta!");
                }
            }
        }
    }

    public void prototype() throws RuntimeException{
        while (true){
            System.out.println("\n\n\n\n");
            System.out.println("1. List of Employees");
            System.out.println("2. Modify");
            System.out.println("3. Hire");
            System.out.println("4. Fire");
            System.out.println("5. Exit");
            System.out.print("Number:\t");
            int nr = new Scanner(System.in).nextInt();
            System.out.println("\n\n\n");
            if(nr == 5){
                break;
            }
            else {
                if(nr == 1){
                    try {
                        List<Employee> employees = Employee.employees;
                        int i = 1;
                        for(Employee e: employees){
                            Employee copied = e.copy();
                            System.out.println("Nr." + i);
                            System.out.println("First name:\t\t" + copied.getFirstName());
                            System.out.println("Last name:\t\t" + copied.getLastName());
                            System.out.println("Age:\t\t\t" + copied.getAge());
                            System.out.println("Position:\t\t" + copied.getPosition());
                            System.out.println("--------------------------------------");
                            i++;
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } else if(nr == 2){
                    try {
                        Employee employee = null;
                        boolean exist = false;
                        List<Employee> employees = Employee.employees;
                        System.out.print("First name: ");
                        String first = new Scanner(System.in).nextLine();
                        System.out.print("Last name: ");
                        String last = new Scanner(System.in).nextLine();
                        System.out.print("Age: ");
                        int age = new Scanner(System.in).nextInt();
                        for (Employee e: employees){
                            if(first.equalsIgnoreCase(e.getFirstName()) && last.equalsIgnoreCase(e.getLastName()) && age == e.getAge()){
                                employee = e;
                                exist = true;
                            }
                        }
                        while (exist) {
                            System.out.println("\n\n\n\n");
                            System.out.println("1.First name");
                            System.out.println("2.Last name:");
                            System.out.println("3.Age");
                            System.out.println("4.Position");
                            System.out.println("5. Exit");
                            System.out.print("Number:\t");
                            int employeeModifyNr = new Scanner(System.in).nextInt();
                            if(employeeModifyNr == 5){
                                exist = false;
                            } else {
                                System.out.println("\n\n");
                                if (employeeModifyNr == 1){
                                    System.out.print("New first name:\t");
                                    String new_first = new Scanner(System.in).nextLine();
                                    employee.setFirstName(new_first);
                                } else if (employeeModifyNr == 2){
                                    System.out.print("New last name:\t");
                                    String new_last = new Scanner(System.in).nextLine();
                                    employee.setLastName(new_last);
                                } else if (employeeModifyNr == 3){
                                    System.out.print("New age:\t");
                                    int new_age = new Scanner(System.in).nextInt();
                                    employee.setAge(new_age);
                                } else if( employeeModifyNr == 4){
                                    System.out.print("New position:\t");
                                    String new_position = new Scanner(System.in).nextLine();
                                    employee.setPosition(new_position);
                                }
                            }
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } else if(nr ==3){
                    System.out.print("First name:\t");
                    String first = new Scanner(System.in).nextLine();
                    System.out.print("Last name:\t");
                    String last = new Scanner(System.in).nextLine();
                    System.out.print("Age:\t");
                    int age = new Scanner(System.in).nextInt();
                    System.out.print("Position:\t");
                    String position = new Scanner(System.in).nextLine();
                    new Employee(first, last, age, position,false);
                }else  if(nr ==4){
                    List<Employee> employees = Employee.employees;
                    System.out.print("First name: ");
                    String first = new Scanner(System.in).nextLine();
                    System.out.print("Last name: ");
                    String last = new Scanner(System.in).nextLine();
                    System.out.print("Age: ");
                    int age = new Scanner(System.in).nextInt();
                    int i = 0;
                    for (Employee e: employees){
                        if(first.equalsIgnoreCase(e.getFirstName()) && last.equalsIgnoreCase(e.getLastName()) && age == e.getAge()){
                            employees.remove(i);
                            System.out.println("\n\nSuccessfully.");
                            break;
                        }
                        i++;
                    }

                } else {
                    System.out.println("\n\nNu exista asa varianta!");
                }
            }
        }
    }

    public void abstractFactory() throws RuntimeException{
        ComponentsStore componentsStore = ComponentsStore.getInstance();
        while (true) {
            String type = "AbstractFactory";
            String corporation;
            System.out.println("\n\n\n");
            System.out.println("1. CPU");
            System.out.println("2. GPU");
            System.out.println("3. RAM");
            System.out.println("4. Exit");
            System.out.print("Number:\t");
            int nr = new Scanner(System.in).nextInt();
            System.out.println("\n\n\n");
            if (nr == 4) {
                break;
            } else if (nr > 0 && nr < 4) {
                while (true) {
                    if (nr == 1) {
                        System.out.println("1. Intel");
                        System.out.println("2. AMD");
                        System.out.println("3. Exit");
                        System.out.print("Number:\t");
                        int corporationNr = new Scanner(System.in).nextInt();
                        if(corporationNr == 3){
                            break;
                        }
                        if (corporationNr == 1) {
                            corporation = "Intel";
                        } else if (corporationNr == 2) {
                            corporation = "AMD";
                        } else {
                            System.out.println("\n\nNu exista asa varianta!");
                            break;
                        }
                    } else if (nr == 2) {
                        System.out.println("1. Nvidia");
                        System.out.println("2. AMD");
                        System.out.println("3. Exit");
                        System.out.print("Number:\t");
                        int corporationNr = new Scanner(System.in).nextInt();
                        if(corporationNr == 3){
                            break;
                        }
                        if (corporationNr == 1) {
                            corporation = "Nvidia";
                        } else if (corporationNr == 2) {
                            corporation = "AMD";
                        } else {
                            System.out.println("\n\nNu exista asa varianta!");
                            break;
                        }
                    } else {
                        System.out.println("1. Kingston");
                        System.out.println("2. HyperX");
                        System.out.println("3. Exit");
                        System.out.print("Number:\t");
                        int corporationNr = new Scanner(System.in).nextInt();
                        if(corporationNr == 3){
                            break;
                        }
                        if (corporationNr == 1) {
                            corporation = "HyperX";
                        } else if (corporationNr == 2) {
                            corporation = "Kingston";
                        } else {
                            System.out.println("\n\nNu exista asa varianta!");
                            break;
                        }
                    }
                    while (true) {
                        System.out.println("\n\n\n\n");
                        System.out.println("1. Show");
                        System.out.println("2. Set");
                        System.out.println("3. Delete");
                        System.out.println("4. Exit");
                        System.out.print("Number:\t");
                        int subnr = new Scanner(System.in).nextInt();
                        System.out.println("\n\n\n\n");
                        if (subnr == 4) {
                            break;
                        } else if (subnr > 0 && subnr < 4) {
                            System.out.flush();
                            if (subnr == 1) {
                                componentsStore.showComponents(nr, type, corporation);
                            } else if (subnr == 2) {
                                componentsStore.setComponents(nr, type, corporation);
                            } else {
                                componentsStore.deleteComponents(nr, type, corporation);
                            }
                        } else {
                            System.out.println("\n\nNu exista asa numar..");
                        }
                    }
                }
            } else {
                System.out.println("\n\nNu exista asa numar..");
            }
        }
    }

    public void factoryMethod() throws RuntimeException{
        ComponentsStore componentsStore = ComponentsStore.getInstance();
        while (true) {
            String type = "Factory";
            System.out.println("\n\n\n\n");
            System.out.println("1. CPU");
            System.out.println("2. GPU");
            System.out.println("3. RAM");
            System.out.println("4. Exit");
            System.out.print("Number:\t");
            int nr = new Scanner(System.in).nextInt();
            if (nr == 4) {
                break;
            } else if (nr > 0 && nr < 4) {
                while (true) {
                    System.out.println("\n\n\n\n");
                    System.out.println("1. Show");
                    System.out.println("2. Set");
                    System.out.println("3. Delete");
                    System.out.println("4. Exit");
                    System.out.print("Number:\t");
                    int subnr = new Scanner(System.in).nextInt();
                    System.out.println("\n\n\n\n");
                    if (subnr == 4) {
                        break;
                    } else if (subnr > 0 && subnr < 4) {
                        System.out.flush();
                        if (subnr == 1) {
                            componentsStore.showComponents(nr, type, null);
                        } else if (subnr == 2) {
                            componentsStore.setComponents(nr, type, null);
                        } else {
                            componentsStore.deleteComponents(nr, type, null);
                        }
                    } else {
                        System.out.println("\n\nNu exista asa varianta!");
                    }
                }
            } else {
                System.out.println("\n\nNu exista asa varianta!");
            }
        }
    }
}
