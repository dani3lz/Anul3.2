package md.dani3lz.tmpslab1.Singletone;

import md.dani3lz.tmpslab1.AbstractFactory.*;
import md.dani3lz.tmpslab1.AbstractFactory.Components.*;
import md.dani3lz.tmpslab1.Factory.*;

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
                list = md.dani3lz.tmpslab1.Factory.CPU.CPUList;
            } else if (nr == 2) {
                list = md.dani3lz.tmpslab1.Factory.GPU.GPUList;
            } else if (nr == 3) {
                list = md.dani3lz.tmpslab1.Factory.RAM.RAMList;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
