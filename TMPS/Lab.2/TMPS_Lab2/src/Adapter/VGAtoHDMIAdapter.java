package Adapter;

public class VGAtoHDMIAdapter implements HDMI{
    private final VGA vgaAdapter;

    public VGAtoHDMIAdapter(VGA vgaAdapter) {
        this.vgaAdapter = vgaAdapter;
    }

    @Override
    public void show() {
        vgaAdapter.show();
    }

    @Override
    public void usePortHDMI() {
        System.out.println("HDMI connected.");
        vgaAdapter.usePortVGA();
    }
}
