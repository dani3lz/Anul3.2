package Adapter.Notebooks;

import Adapter.VGA;

public class Lenova implements VGA {
    private boolean connector;

    @Override
    public void show() {
        if(connector){
            System.out.println("Display content");
        } else {
            System.out.println("Connect VGA first.");
        }
    }

    @Override
    public void usePortVGA() {
        connector = true;
        System.out.println("VGA connected.");
    }
}
