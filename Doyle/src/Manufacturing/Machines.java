package Manufacturing;

public class Machines implements MachineOperations {


    private boolean isRunning;
    private boolean isProducing;

    public Machines() {
        isRunning = false;
        isProducing = false;
    }
    //isRunning
    @Override
    public boolean isRunning() {

        return isRunning;
    }

    //checkStatus
    @Override
    public void startMachine() {

        if(!isRunning) {
            isRunning = true;
            System.out.println("Machine is running.");
        }else{
            System.out.println("Machine is already running.");
        }
    }

    @Override
    public void stopMachine() {
        if(isRunning) {
            isRunning = false;
            System.out.println("Machine is stopped.");
        }
        else{
            System.out.println("Machine is not running.");
        }
    }

    @Override
    public void startProduction() {

        if(isRunning && !isProducing) {
            isProducing = true;
            System.out.println("Machine is producing.");
        }
        else if(!isRunning){
            System.out.println("Machine is not producing.");
        }
        else{
            System.out.println("Machine is already producing.");
        }
    }

    @Override
    public void stopProduction() {

        if(isProducing){
            isProducing = false;
            System.out.println("Machine is stopped.");
        }
        else{
            System.out.println("Machine is not producing.");
        }
    }

}
