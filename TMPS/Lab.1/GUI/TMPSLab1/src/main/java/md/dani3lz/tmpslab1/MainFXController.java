package md.dani3lz.tmpslab1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import md.dani3lz.tmpslab1.Prototype.Employee;
import md.dani3lz.tmpslab1.Singletone.ComponentsStore;
import md.dani3lz.tmpslab1.AbstractFactory.*;
import md.dani3lz.tmpslab1.Factory.*;

import java.util.ArrayList;
import java.util.List;

public class MainFXController {
    @FXML
    public Button btn_1, btn_2, btn_3, btn_4, btn_5;

    @FXML
    public ListView<String> listView;

    @FXML
    public TextField textfield_1;
    public TextField textfield_2;
    public TextField textfield_3;
    public TextField textfield_4;
    public TextField textfield_5;
    public VBox vboxText;

    List<AbstractComponents> listAbstract = new ArrayList<>();
    List<Components> listFactory = new ArrayList<>();
    List<Employee> employees = new ArrayList<>();
    List<String> listString = new ArrayList<>();

    boolean modify = false;
    int sceneGlobal = 0;
    String methodGlobal = "";
    int nrGlobal = 0, lastNr = 0;
    boolean finalScene = false;
    boolean confirmed = false, showList = false;
    Employee employeeGlobal;

    private final int limitAbstract = 4, limitFactory = 3, limitPrototype = 2;

    @FXML
    protected void onBtn1Click() {
        if(sceneGlobal == 0){
            methodGlobal = "AbstractFactory";
        }
        if(finalScene){
            finalScene = false;
            confirmed = true;
        } else if(!showList){
            lastNr = nrGlobal;
            checkLimit();
        }
        if(confirmed){
            confirmed = false;
            System.out.println("Scene: " + sceneGlobal);
            btn_confirm(methodGlobal,nrGlobal);
            if(!modify) {
                sceneGlobal--;
            }

        } else {
            nrGlobal = 1;
        }
        setVisivilityToBtn(methodGlobal, sceneGlobal, nrGlobal);
        setContentList(methodGlobal, sceneGlobal);

    }
    @FXML
    protected void onBtn2Click() {
        if(sceneGlobal == 0){
            methodGlobal = "Prototype";
        }
        if(finalScene){
            finalScene = false;
        } else if(!showList){
            lastNr = nrGlobal;
        }

        checkLimit();
        nrGlobal = 2;
        setVisivilityToBtn(methodGlobal, sceneGlobal, nrGlobal);
        setContentList(methodGlobal, sceneGlobal);

        textfield_1.setText("");
        textfield_2.setText("");
        textfield_3.setText("");
        textfield_4.setText("");
        textfield_5.setText("");
    }

    @FXML
    protected void onBtn3Click() {
        if(sceneGlobal == 0){
            methodGlobal = "Factory";
        }
        if(finalScene){
            finalScene = false;
        } else if(!showList){
            lastNr = nrGlobal;
        }
        checkLimit();
        nrGlobal = 3;
        setVisivilityToBtn(methodGlobal, sceneGlobal, nrGlobal);
        setContentList(methodGlobal, sceneGlobal);
    }

    @FXML
    protected void onBtn4Click() {
        if(finalScene){
            finalScene = false;
        }
        checkLimit();
        lastNr = nrGlobal;
        nrGlobal = 4;
        setVisivilityToBtn(methodGlobal, sceneGlobal, nrGlobal);
        setContentList(methodGlobal, sceneGlobal);
    }

    @FXML
    protected void onBtn5Click() {
        System.out.println("Scene: " + sceneGlobal);


        if(showList){
            showList = false;
            listView.getItems().clear();
            listView.setVisible(false);
        }
        if(sceneGlobal != 0){
            sceneGlobal--;
            textfield_1.setText("");
            textfield_2.setText("");
            textfield_3.setText("");
            textfield_4.setText("");
            textfield_5.setText("");

            confirmed = false;
            finalScene = false;
            setVisivilityToBtn(methodGlobal, sceneGlobal, nrGlobal);
        }
        System.out.println("Scene after: " + sceneGlobal);
    }

    protected String getCorporationAbstract(){
        if(nrGlobal == 1){
            if(lastNr == 1){
                return "Intel";
            }else if (lastNr == 2){
                return "AMD";
            }
        } else if(nrGlobal == 2){
            if(lastNr == 1){
                return "Nvidia";
            }else if (lastNr ==2){
                return "AMD";
            }
        } else if(nrGlobal == 3){
            if(lastNr == 1){
                return "Kingston";
            }else if (lastNr == 2){
                return "HyperX";
            }
        }
        return "ХТО Я?";
    }

    protected void checkLimit(){
        if(methodGlobal.equalsIgnoreCase("AbstractFactory")){
            if(sceneGlobal < limitAbstract){
                sceneGlobal++;
            }
        } else if (methodGlobal.equalsIgnoreCase("Factory")){
            if(sceneGlobal < limitFactory){
                sceneGlobal++;
            }
        } else if (methodGlobal.equalsIgnoreCase("Prototype")){
            if(sceneGlobal < limitPrototype){
                sceneGlobal++;
            }
        }
    }

    protected void setContentList(String method, int scene){
        ComponentsStore componentsStore = ComponentsStore.getInstance();
        if (method.equalsIgnoreCase("AbstractFactory")) {
            if (scene == 4) {
                if (nrGlobal == 1) {
                    listView.setVisible(true);
                    vboxText.setVisible(false);
                    String corporation = getCorporationAbstract();
                    listAbstract = componentsStore.getAbstractComponentsList(nrGlobal, corporation);
                    int i = listAbstract.size() + 1;
                    // ------------------------------------------
                    for (int j = i; j < i + 5; j++) {
                        System.out.println("J = " + j + " - " + corporation);
                        new CPUAbstractFactory().createComponent(corporation, "Radeon RX Aurel Turbo GT-" + j, 105, 12000, 16000, "CPU");
                    }
                    // ------------------------------------------
                    i = 1;
                    for (AbstractComponents ac : listAbstract) {
                        System.out.println("I = " + i);
                        listString.add("Nr." + i);
                        listString.add("Corporation:\t\t" + corporation);
                        listString.add("Model:\t\t\t" + ac.model());
                        listString.add("Memory:\t\t\t" + ac.memory());
                        listString.add("Ranking:\t\t\t" + ac.ranking());
                        listString.add("Price(MDL):\t\t" + ac.price());
                        listString.add("--------------------------------------------------------------");
                        i++;
                    }
                    listView.getItems().setAll(listString);
                    listString.clear();
                }
            } else if(nrGlobal == 2){
                // Set
            } else if(nrGlobal == 3){
                // Delete
            }
        } else if (method.equalsIgnoreCase("Factory")) {
            if (scene == 3) {
                if (nrGlobal == 1){
                    listView.setVisible(true);
                    vboxText.setVisible(false);
                    listFactory = componentsStore.getComponentsList(lastNr);
                    // ------------------------------------------
                    int i = listFactory.size() + 1;
                    for (int j = i; j < i + 5; j++) {
                        new GPUFactory().createComponent("Nvidia", "GTX +100500 ASEM-" + j, 2022, 1500, 16000);
                    }
                    // ------------------------------------------
                    i = 1;
                    for (Components c : listFactory) {
                        System.out.println("I = " + i);
                        listString.add("Nr." + i);
                        listString.add("Corporation:\t\t" + c.corporation());
                        listString.add("Model:\t\t\t" + c.model());
                        listString.add("Year:\t\t\t\t" + c.year());
                        listString.add("Ranking:\t\t\t" + c.ranking());
                        listString.add("Price(MDL):\t\t" + c.price());
                        listString.add("--------------------------------------------------------------");
                        i++;
                    }
                    listView.getItems().setAll(listString);
                    listString.clear();
                }
            } else if (nrGlobal == 2){
                // Set
            } else if(nrGlobal == 3){
                // Delete
            }
        } else if (method.equalsIgnoreCase("Prototype")) {
            if (scene == 2) {
                try {
                    if(nrGlobal == 1) {
                        listView.setVisible(true);
                        vboxText.setVisible(false);
                        employees = Employee.employees;
                        // ------------------------------------------
                        /*int i = employees.size();
                        for (int j = i; j < i + 5; j++) {
                            new Employee("Alexandru", "Gumaniuc", 21 + j, "Ciort", false);
                        }*/
                        // ------------------------------------------
                        int i = 1;
                        for (Employee e : employees) {
                            Employee copied = e.copy();
                            listString.add("Nr." + i);
                            listString.add("First name:\t" + copied.getFirstName());
                            listString.add("Last name:\t" + copied.getLastName());
                            listString.add("Age:\t\t\t" + copied.getAge());
                            listString.add("Position:\t\t" + copied.getPosition());
                            listString.add("--------------------------------------------------------------");
                            i++;
                        }
                        listView.getItems().setAll(listString);
                        listString.clear();
                    } else if (nrGlobal == 2){
                        if(!modify) {
                            vboxText.setVisible(true);
                            listView.setVisible(false);
                            textfield_1.setVisible(true);
                            textfield_2.setVisible(true);
                            textfield_3.setVisible(true);
                            textfield_4.setVisible(false);
                            textfield_5.setVisible(false);

                            textfield_1.setPromptText("First name");
                            textfield_2.setPromptText("Last name");
                            textfield_3.setPromptText("Age");
                        }else {
                            vboxText.setVisible(true);
                            listView.setVisible(false);
                            textfield_1.setVisible(true);
                            textfield_2.setVisible(true);
                            textfield_3.setVisible(true);
                            textfield_4.setVisible(true);
                            textfield_5.setVisible(false);

                            textfield_1.setPromptText("New first name");
                            textfield_2.setPromptText("New last name");
                            textfield_3.setPromptText("New age");
                            textfield_4.setPromptText("New position");
                        }

                        // Modify
                    } else if(nrGlobal == 3){
                        vboxText.setVisible(true);
                        listView.setVisible(false);
                        textfield_1.setVisible(true);
                        textfield_2.setVisible(true);
                        textfield_3.setVisible(true);
                        textfield_4.setVisible(true);
                        textfield_5.setVisible(false);

                        textfield_1.setPromptText("First name");
                        textfield_2.setPromptText("Last name");
                        textfield_3.setPromptText("Age");
                        textfield_4.setPromptText("Position");

                        // Hire
                    } else if(nrGlobal == 4){
                        vboxText.setVisible(true);
                        listView.setVisible(false);
                        textfield_1.setVisible(true);
                        textfield_2.setVisible(true);
                        textfield_3.setVisible(true);
                        textfield_4.setVisible(false);
                        textfield_5.setVisible(false);

                        textfield_1.setPromptText("First name");
                        textfield_2.setPromptText("Last name");
                        textfield_3.setPromptText("Age");
                        // Fire
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void btn_confirm(String method, int nr){
        if(method.equalsIgnoreCase("AbstractFactory")){
            // gdf
        } else if (method.equalsIgnoreCase("Factory")){
            // gf
        } else if (method.equalsIgnoreCase("Prototype")){
            if(nr == 2) {
                System.out.println("Modify is " + modify);
                if(!modify) {
                    String first = textfield_1.getText();
                    String last = textfield_2.getText();
                    int age = Integer.parseInt(textfield_3.getText());
                    boolean exist = false;
                    for (Employee e : employees) {
                        if (first.equalsIgnoreCase(e.getFirstName()) && last.equalsIgnoreCase(e.getLastName()) && age == e.getAge()) {
                            employeeGlobal = e;
                            exist = true;
                            System.out.println("MODIFYYYYYYYYYYYYY");
                            modify = true;
                            break;
                        }
                    }
                    if (exist) {
                        textfield_1.setText("");
                        textfield_2.setText("");
                        textfield_3.setText("");
                        textfield_4.setText("");

                        textfield_4.setVisible(true);
                        textfield_1.setPromptText("New First Name");
                        textfield_2.setPromptText("New Last Name");
                        textfield_3.setPromptText("New Age");
                        textfield_4.setPromptText("New Position");

                        System.out.println("EXIST!");
                    }
                } else {
                    modify = false;
                    String first = textfield_1.getText();
                    String last = textfield_2.getText();
                    String age = textfield_3.getText();
                    String position = textfield_4.getText();
                    if(!first.equals("")){
                        employeeGlobal.setFirstName(first);
                    }
                    if(!last.equals("")){
                        employeeGlobal.setLastName(last);
                    }
                    if(!age.equals("")){
                        employeeGlobal.setAge(Integer.parseInt(age));
                    }
                    if(!position.equals("")){
                        employeeGlobal.setPosition(position);
                    }
                }
                // Modify
            } else if (nr == 3){
                System.out.println("HHHHHHHHHHEEEEEEREEEEEE HIRE");
                String first = textfield_1.getText();
                String last = textfield_2.getText();
                int age = Integer.parseInt(textfield_3.getText());
                String position = textfield_4.getText();
                new Employee(first, last, age, position, false);

                // Hire
            } else if (nr == 4){
                System.out.println("HHHHHHHHHHEEEEEEREEEEEE FIRE");
                String first = textfield_1.getText();
                String last = textfield_2.getText();
                int age = Integer.parseInt(textfield_3.getText());

                int i = 0;
                for (Employee e: employees){
                    if(first.equalsIgnoreCase(e.getFirstName()) && last.equalsIgnoreCase(e.getLastName()) && age == e.getAge()){
                        employees.remove(i);
                        System.out.println("\n\nSuccessfully.");
                        break;
                    }
                    i++;
                }
                // Fire
            }
        }
    }


    protected void setVisivilityToBtn(String method, int scene, int nr) {
        btn_5.setVisible(sceneGlobal != 0);
        showList = false;
        if (method.equalsIgnoreCase("AbstractFactory")) {
            if (scene == 0) {
                textfield_1.setVisible(false);
                textfield_2.setVisible(false);
                textfield_3.setVisible(false);

                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(true);
                btn_4.setVisible(false);

                btn_3.setText("Factory");
                btn_1.setText("Abstract Factory");
                btn_2.setText("Prototype");
            } else if (scene == 1) {
                textfield_1.setVisible(false);
                textfield_2.setVisible(false);
                textfield_3.setVisible(false);

                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(true);
                btn_4.setVisible(false);

                btn_1.setText("CPU");
                btn_2.setText("GPU");
                btn_3.setText("RAM");
                btn_5.setText("Back");

            } else if (scene == 2) {
                textfield_1.setVisible(false);
                textfield_2.setVisible(false);
                textfield_3.setVisible(false);

                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(false);
                btn_4.setVisible(false);
                if (nr == 1) {
                    btn_1.setText("Intel");
                    btn_2.setText("AMD");
                    btn_5.setText("Back");
                } else if (nr == 2) {
                    btn_1.setText("Nvidia");
                    btn_2.setText("AMD");
                    btn_5.setText("Back");
                } else if (nr == 3) {
                    btn_1.setText("Kingston");
                    btn_2.setText("HyperX");
                    btn_5.setText("Back");
                }
            } else if (scene == 3) {
                textfield_1.setVisible(false);
                textfield_2.setVisible(false);
                textfield_3.setVisible(false);

                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(true);
                btn_4.setVisible(false);

                btn_1.setText("Show");
                btn_2.setText("Set");
                btn_3.setText("Delete");
                btn_5.setText("Back");
            } else if (scene == 4) {
                if(nrGlobal != 1) {
                    btn_1.setVisible(true);
                    btn_2.setVisible(false);
                    btn_3.setVisible(false);
                    btn_4.setVisible(false);
                    btn_1.setText("Confirm");
                    btn_5.setText("Cancel");

                    textfield_1.setVisible(true);
                    textfield_2.setVisible(true);
                    textfield_3.setVisible(true);

                    finalScene = true;
                } else {
                    showList = true;
                }

            }
        } else if (method.equalsIgnoreCase("Factory")) {
            if (scene == 0) {
                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(true);
                btn_4.setVisible(false);

                btn_3.setText("Factory");
                btn_1.setText("Abstract Factory");
                btn_2.setText("Prototype");
            } else if (scene == 1) {
                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(true);
                btn_4.setVisible(false);

                btn_1.setText("CPU");
                btn_2.setText("GPU");
                btn_3.setText("RAM");
                btn_5.setText("Back");
            } else if (scene == 2) {
                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(true);
                btn_4.setVisible(false);

                btn_1.setText("Show");
                btn_2.setText("Set");
                btn_3.setText("Delete");
                btn_5.setText("Back");
            } else if (scene == 3) {
                if(nrGlobal != 1) {
                    btn_1.setVisible(true);
                    btn_2.setVisible(false);
                    btn_3.setVisible(false);
                    btn_4.setVisible(false);
                    btn_1.setText("Confirm");
                    btn_5.setText("Cancel");
                    finalScene = true;
                }else {
                    showList = true;
                }
            }
        } else if (method.equalsIgnoreCase("Prototype")) {
            if (scene == 0) {
                textfield_1.setVisible(false);
                textfield_2.setVisible(false);
                textfield_3.setVisible(false);
                textfield_4.setVisible(false);
                textfield_5.setVisible(false);

                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(true);
                btn_4.setVisible(false);

                btn_3.setText("Factory");
                btn_1.setText("Abstract Factory");
                btn_2.setText("Prototype");
            } else if (scene == 1) {
                textfield_1.setVisible(false);
                textfield_2.setVisible(false);
                textfield_3.setVisible(false);
                textfield_4.setVisible(false);
                textfield_5.setVisible(false);

                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(true);
                btn_4.setVisible(true);

                btn_1.setText("List of Employees");
                btn_2.setText("Modify");
                btn_3.setText("Hire");
                btn_4.setText("Fire");
                btn_5.setText("Back");
            } else if (scene == 2) {
                if(nrGlobal != 1) {
                    btn_1.setVisible(true);
                    btn_2.setVisible(false);
                    btn_3.setVisible(false);
                    btn_4.setVisible(false);
                    btn_1.setText("Confirm");
                    btn_5.setText("Cancel");

                    listView.setVisible(false);
                    textfield_1.setVisible(true);
                    textfield_2.setVisible(true);
                    textfield_3.setVisible(true);
                    textfield_4.setVisible(true);
                    textfield_5.setVisible(false);

                    finalScene = true;
                }else {
                    showList = true;
                    btn_1.setVisible(false);
                    btn_2.setVisible(false);
                    btn_3.setVisible(false);
                    btn_4.setVisible(false);
                    btn_5.setVisible(true);
                    btn_5.setText("Back");
                }
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

}