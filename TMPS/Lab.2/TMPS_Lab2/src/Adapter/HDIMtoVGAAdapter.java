package Adapter;

public class HDIMtoVGAAdapter implements VGA{
    private final HDMI hdmiAdapter;

    public HDIMtoVGAAdapter(HDMI hdmiAdapter) {
        this.hdmiAdapter = hdmiAdapter;
    }

    @Override
    public void usePortVGA() {
        System.out.println("VGA connected.");
        hdmiAdapter.usePortHDMI();
    }

    @Override
    public void show() {
        hdmiAdapter.show();
    }
}
