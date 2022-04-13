package Facade;

import Facade.Interface.*;

public class Starter extends PowerStarter implements CPU, GPU, RAM, Cooler, SSD {
    private boolean isStart = false;

    public boolean isStart() {
        return isStart;
    }

    @Override
    public void startCPU(int MAX_TEMP_COOLING, boolean successfully) {
        if(successfully){
            System.out.println("Starting CPU...");
            startRAM();
            startSSD();
            startGPU();
            startCooler(MAX_TEMP_COOLING);
            isStart = true;
        } else {
            System.out.println("Problem with Power Supply.");
        }

    }

    @Override
    public boolean stopCPU() {
        if(isStart){
            System.out.println("Stopping CPU...");
            stopGPU();
            stopSSD();
            stopCooler();
            stopRAM();
            isStart = false;
            return true;
        } else {
            System.out.println("PC is already off.");
            return false;
        }

    }

    @Override
    public boolean startCooler(int MAX_TEMP_COOLING) {
        if(MAX_TEMP_COOLING > 100){
            System.out.println("Can't start with this temp speed.");
            return false;
        }else {
            System.out.println("Starting Cooler...");
            return true;
        }

    }

    @Override
    public void stopCooler() {
        System.out.println("Stopping Cooler");
    }

    @Override
    public void startGPU() {
        System.out.println("Starting GPU...");
    }

    @Override
    public void stopGPU() {
        System.out.println("Stopping GPU...");
    }

    @Override
    public void startRAM() {
        System.out.println("Starting using RAM...");
    }

    @Override
    public void stopRAM() {
        System.out.println("Ending using RAM...");
    }

    @Override
    public void startSSD() {
        System.out.println("Starting SSD...");
    }

    @Override
    public void stopSSD() {
        System.out.println("Stopping SSD...");
    }
}
