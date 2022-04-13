package Facade;

import Facade.Interface.PowerSupply;

public class PowerStarter implements PowerSupply {

    @Override
    public boolean startPower(int MAX_POWER) {
        if (MAX_POWER < 50){
            System.out.println("Can't start power supply");
            return false;
        } else {
            System.out.println("Start Power Supply.");
            return true;
        }

    }

    @Override
    public boolean stopPower() {
        System.out.println("Stop Power Supply.");
        return false;
    }
}
