package md.dani3lz.tmpslab1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import md.dani3lz.tmpslab1.AbstractFactory.ComponentsFactory;
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
    String corporationGlobal = "";
    int componentClicked = 0;
    boolean abstractNext = false, abstractPrev = false, factoryNext = false;

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
            setVisivilityToBtn(methodGlobal, sceneGlobal, lastNr);
        }
    }

    protected void checkLimit(){
        if(methodGlobal.equalsIgnoreCase("AbstractFactory")){
            int limitAbstract = 4;
            if(sceneGlobal < limitAbstract){
                sceneGlobal++;
            }
        } else if (methodGlobal.equalsIgnoreCase("Factory")){
            int limitFactory = 3;
            if(sceneGlobal < limitFactory){
                sceneGlobal++;
            }
        } else if (methodGlobal.equalsIgnoreCase("Prototype")){
            int limitPrototype = 2;
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
                    listAbstract = componentsStore.getAbstractComponentsList(componentClicked, corporationGlobal);
                    int i = 1;
                    for (AbstractComponents ac : listAbstract) {
                        listString.add("Nr." + i);
                        listString.add("Corporation:\t\t" + corporationGlobal);
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
            }
        } else if (method.equalsIgnoreCase("Factory")) {
            if (scene == 3) {
                if (nrGlobal == 1) {
                    listView.setVisible(true);
                    vboxText.setVisible(false);
                    listFactory = componentsStore.getComponentsList(componentClicked);
                    int i = 1;
                    for (Components c : listFactory) {
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
            }
        } else if (method.equalsIgnoreCase("Prototype")) {
            if (scene == 2) {
                try {
                    if(nrGlobal == 1) {
                        listView.setVisible(true);
                        vboxText.setVisible(false);
                        employees = Employee.employees;
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
                        // Modify
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

                    } else if(nrGlobal == 3){
                        // Hire (Add)

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


                    } else if(nrGlobal == 4){
                        // Fire (Delete)

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
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void btn_confirm(String method, int nr){
        if(method.equalsIgnoreCase("AbstractFactory")){
            if(nr==2){
                // Add
                String model = textfield_1.getText();
                double rank = Double.parseDouble(textfield_2.getText());
                int memory = Integer.parseInt(textfield_3.getText());
                int price = Integer.parseInt(textfield_4.getText());
                if (componentClicked == 1) { // CPU
                    new ComponentsFactory().getFactory(corporationGlobal,model,rank,price,memory,"CPU");
                    //new CPUAbstractFactory().createComponent(corporationGlobal, model, rank, price, memory, "CPU");
                } else if (componentClicked == 2) { // GPU
                    new ComponentsFactory().getFactory(corporationGlobal,model,rank,price,memory,"GPU");
                    //new GPUAbstractFactory().createComponent(corporationGlobal, model, rank, price, memory, "GPU");
                } else if (componentClicked == 3) { // RAM
                    new ComponentsFactory().getFactory(corporationGlobal,model,rank,price,memory,"RAM");
                    //new RAMAbstractFactory().createComponent(corporationGlobal, model, rank, price, memory, "RAM");
                }
            } else if(nr==3){
                // Delete

                String model = textfield_1.getText();
                int memory = Integer.parseInt(textfield_2.getText());

                try {
                    List<AbstractComponents> list = ComponentsStore.getInstance().getAbstractComponentsList(componentClicked, corporationGlobal);
                    int i = 0;
                    for (AbstractComponents ac : list) {
                        if (ac.model().contains(model) && memory == ac.memory()) {
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
        } else if (method.equalsIgnoreCase("Factory")){
            if(nr==2){
                // Add

                String corporation = textfield_1.getText();
                String model = textfield_2.getText();
                int year = Integer.parseInt(textfield_3.getText());
                double rank = Double.parseDouble(textfield_4.getText());
                int price = Integer.parseInt(textfield_5.getText());

                if(componentClicked == 1){
                    new md.dani3lz.tmpslab1.Factory.CPUFactory().createComponent(corporation, model, year, rank, price);
                } else if (componentClicked == 2){
                    new md.dani3lz.tmpslab1.Factory.GPUFactory().createComponent(corporation, model, year, rank, price);
                } else if( componentClicked == 3){
                    new md.dani3lz.tmpslab1.Factory.RAMFactory().createComponent(corporation, model, year, rank, price);
                }

            } else if(nr==3){
                // Delete

                String model = textfield_1.getText();
                int year = Integer.parseInt(textfield_2.getText());

                try {
                    List<Components> list = ComponentsStore.getInstance().getComponentsList(componentClicked);
                    int i = 0;
                    for (Components c : list) {
                        if (c.model().contains(model) && year == c.year()) {
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
        } else if (method.equalsIgnoreCase("Prototype")){
            if(nr == 2) {
                // Modify
                if(!modify) {
                    String first = textfield_1.getText();
                    String last = textfield_2.getText();
                    int age = Integer.parseInt(textfield_3.getText());
                    boolean exist = false;
                    for (Employee e : employees) {
                        if (first.equalsIgnoreCase(e.getFirstName()) && last.equalsIgnoreCase(e.getLastName()) && age == e.getAge()) {
                            employeeGlobal = e;
                            exist = true;
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

            } else if (nr == 3){
                // Hire

                String first = textfield_1.getText();
                String last = textfield_2.getText();
                int age = Integer.parseInt(textfield_3.getText());
                String position = textfield_4.getText();
                new Employee(first, last, age, position, false);


            } else if (nr == 4){
                // Fire

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
                textfield_4.setVisible(false);
                textfield_5.setVisible(false);
                listView.setVisible(false);
                vboxText.setVisible(false);

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
                listView.setVisible(false);
                vboxText.setVisible(false);

                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(true);
                btn_4.setVisible(false);

                btn_1.setText("CPU");
                btn_2.setText("GPU");
                btn_3.setText("RAM");
                btn_5.setText("Back");
                abstractNext = true;

            } else if (scene == 2) {
                abstractPrev = true;

                textfield_1.setVisible(false);
                textfield_2.setVisible(false);
                textfield_3.setVisible(false);
                textfield_4.setVisible(false);
                textfield_5.setVisible(false);
                listView.setVisible(false);
                vboxText.setVisible(false);

                if(abstractNext) {
                    componentClicked = nrGlobal;
                    abstractNext = false;
                } else {
                    nr = componentClicked;
                }

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
                textfield_4.setVisible(false);
                textfield_5.setVisible(false);
                listView.setVisible(false);
                vboxText.setVisible(false);

                if (abstractPrev) {
                    getCorporation();
                    abstractPrev = false;
                }

                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(true);
                btn_4.setVisible(false);

                btn_1.setText("Show");
                btn_2.setText("Set");
                btn_3.setText("Delete");
                btn_5.setText("Back");
            } else if (scene == 4) {
                if(nrGlobal == 2) {
                    listView.setVisible(false);

                    btn_1.setVisible(true);
                    btn_2.setVisible(false);
                    btn_3.setVisible(false);
                    btn_4.setVisible(false);
                    btn_1.setText("Confirm");
                    btn_5.setText("Cancel");

                    textfield_1.setVisible(true);
                    textfield_2.setVisible(true);
                    textfield_3.setVisible(true);
                    textfield_4.setVisible(true);
                    textfield_1.setPromptText("Model");
                    textfield_2.setPromptText("Ranking");
                    textfield_3.setPromptText("Memory");
                    textfield_4.setPromptText("Price(MDL)");
                    vboxText.setVisible(true);

                    finalScene = true;
                } else if(nrGlobal == 1){
                    listView.setVisible(true);
                    vboxText.setVisible(false);
                    showList = true;
                    btn_1.setVisible(false);
                    btn_2.setVisible(false);
                    btn_3.setVisible(false);
                    btn_4.setVisible(false);
                    btn_5.setText("Back");


                } else if(nrGlobal == 3){
                    listView.setVisible(false);
                    btn_1.setVisible(true);
                    btn_2.setVisible(false);
                    btn_3.setVisible(false);
                    btn_4.setVisible(false);
                    btn_1.setText("Confirm");
                    btn_5.setText("Cancel");

                    textfield_1.setVisible(true);
                    textfield_2.setVisible(true);
                    textfield_1.setPromptText("Model");
                    textfield_2.setPromptText("Memory");
                    vboxText.setVisible(true);

                    finalScene = true;
                }

            }
        } else if (method.equalsIgnoreCase("Factory")) {
            if (scene == 0) {
                textfield_1.setVisible(false);
                textfield_2.setVisible(false);
                textfield_3.setVisible(false);
                textfield_4.setVisible(false);
                textfield_5.setVisible(false);
                listView.setVisible(false);
                vboxText.setVisible(false);

                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(true);
                btn_4.setVisible(false);

                btn_3.setText("Factory");
                btn_1.setText("Abstract Factory");
                btn_2.setText("Prototype");
            } else if (scene == 1) {
                factoryNext = true;
                textfield_1.setVisible(false);
                textfield_2.setVisible(false);
                textfield_3.setVisible(false);
                textfield_4.setVisible(false);
                textfield_5.setVisible(false);
                listView.setVisible(false);
                vboxText.setVisible(false);

                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(true);
                btn_4.setVisible(false);

                btn_1.setText("CPU");
                btn_2.setText("GPU");
                btn_3.setText("RAM");
                btn_5.setText("Back");
            } else if (scene == 2) {
                if(factoryNext) {
                    componentClicked = nrGlobal;
                    factoryNext = false;
                }
                textfield_1.setVisible(false);
                textfield_2.setVisible(false);
                textfield_3.setVisible(false);
                textfield_4.setVisible(false);
                textfield_5.setVisible(false);
                listView.setVisible(false);
                vboxText.setVisible(false);

                btn_1.setVisible(true);
                btn_2.setVisible(true);
                btn_3.setVisible(true);
                btn_4.setVisible(false);

                btn_1.setText("Show");
                btn_2.setText("Set");
                btn_3.setText("Delete");
                btn_5.setText("Back");
            } else if (scene == 3) {
                if(nrGlobal == 2) {
                    textfield_1.setVisible(true);
                    textfield_2.setVisible(true);
                    textfield_3.setVisible(true);
                    textfield_4.setVisible(true);
                    textfield_5.setVisible(true);
                    listView.setVisible(false);
                    vboxText.setVisible(true);

                    btn_1.setVisible(true);
                    btn_2.setVisible(false);
                    btn_3.setVisible(false);
                    btn_4.setVisible(false);
                    btn_1.setText("Confirm");
                    btn_5.setText("Cancel");

                    textfield_1.setPromptText("Corporation");
                    textfield_2.setPromptText("Model");
                    textfield_3.setPromptText("Year");
                    textfield_4.setPromptText("Ranking");
                    textfield_5.setPromptText("Price(MDL)");

                    finalScene = true;
                }else if(nrGlobal == 1){
                    listView.setVisible(true);
                    showList = true;

                    btn_1.setVisible(false);
                    btn_2.setVisible(false);
                    btn_3.setVisible(false);
                    btn_4.setVisible(false);
                    btn_5.setText("Back");

                } else if (nrGlobal == 3){
                    textfield_1.setVisible(true);
                    textfield_2.setVisible(true);
                    textfield_3.setVisible(false);
                    textfield_4.setVisible(false);
                    textfield_5.setVisible(false);
                    listView.setVisible(false);
                    vboxText.setVisible(true);

                    textfield_1.setPromptText("Model");
                    textfield_2.setPromptText("Year");

                    btn_1.setVisible(true);
                    btn_2.setVisible(false);
                    btn_3.setVisible(false);
                    btn_4.setVisible(false);
                    btn_1.setText("Confirm");
                    btn_5.setText("Cancel");

                    finalScene = true;
                }
            }
        } else if (method.equalsIgnoreCase("Prototype")) {
            if (scene == 0) {
                textfield_1.setVisible(false);
                textfield_2.setVisible(false);
                textfield_3.setVisible(false);
                textfield_4.setVisible(false);
                textfield_5.setVisible(false);
                listView.setVisible(false);
                vboxText.setVisible(false);

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
                listView.setVisible(false);
                vboxText.setVisible(false);

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
                    listView.setVisible(false);
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
                    vboxText.setVisible(true);

                    finalScene = true;
                }else {
                    listView.setVisible(true);
                    showList = true;
                    btn_1.setVisible(false);
                    btn_2.setVisible(false);
                    btn_3.setVisible(false);
                    btn_4.setVisible(false);
                    btn_5.setVisible(true);
                    btn_5.setText("Back");
                    vboxText.setVisible(false);
                }
            }
        }
    }

    protected void getCorporation(){
        if(componentClicked == 1){
            if(nrGlobal == 1){
                corporationGlobal = "Intel";
            } else if (nrGlobal == 2){
                corporationGlobal = "AMD";
            }
        } else if (componentClicked == 2){
            if(nrGlobal == 1){
                corporationGlobal = "Nvidia";
            } else if (nrGlobal == 2){
                corporationGlobal = "AMD";
            }
        } else if (componentClicked == 3){
            if(nrGlobal == 1){
                corporationGlobal = "Kingston";
            } else if (nrGlobal == 2){
                corporationGlobal = "HyperX";
            }
        }
    }

}