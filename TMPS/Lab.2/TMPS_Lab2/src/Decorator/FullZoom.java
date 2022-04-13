package Decorator;

public class FullZoom extends ZoomDecorator {

    @Override
    public void zoom() {
        super.zoom();
        System.out.println("Zoom Product Photo.");
    }

    @Override
    public String showReview() {
        return super.showReview() + ", including comments.";
    }

    public FullZoom(Zoom zoom) {
        super(zoom);
    }
}
