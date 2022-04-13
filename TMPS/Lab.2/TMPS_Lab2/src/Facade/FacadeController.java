package Facade;

public class FacadeController extends Starter {
    private Starter starter = new Starter();
    private PowerStarter powerStarter = new PowerStarter();
    private final int MAX_TEMP_COOLING = 90;
    private final int MAX_POWER = 70;

    public void startPC(){
        boolean successfully = powerStarter.startPower(MAX_POWER);
        starter.startCPU(MAX_TEMP_COOLING, successfully);

    }

    public void restartPC(){
        stopPC();
        startPC();
    }

    public void stopPC(){
        boolean isStart = starter.stopCPU();
        if(isStart){
            powerStarter.stopPower();
        }
    }
}
