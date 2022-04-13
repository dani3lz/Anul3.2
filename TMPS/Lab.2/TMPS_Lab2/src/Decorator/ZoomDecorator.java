package Decorator;

abstract class ZoomDecorator implements Zoom {
    private final Zoom zoom;

    public ZoomDecorator(Zoom zoom) {
        this.zoom = zoom;
    }


    @Override
    public void zoom() {
        zoom.zoom();
    }

    @Override
    public String showReview() {
        return zoom.showReview() + ", with stars";
    }
}
