package Adapter.Notebooks;

import Adapter.HDMI;

public class Acer implements HDMI {
    private boolean connector;

    @Override
    public void show() {
        if(connector){
            System.out.println("Display content");
        } else {
            System.out.println("Connect HDMI first.");
        }
    }

    @Override
    public void usePortHDMI() {
        connector = true;
        System.out.println("HDMI connected.");
    }
}
