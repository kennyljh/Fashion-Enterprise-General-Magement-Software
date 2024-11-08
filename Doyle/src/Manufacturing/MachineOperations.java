package Manufacturing;

public interface MachineOperations {


    boolean isRunning();

    void startMachine();

    void stopMachine();
    //begin making X amount of products
    void startProduction();

    void stopProduction();
    //create 1 single product



}
