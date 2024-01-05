class ManufacturingStage implements Runnable {
    private String stageName;

    public ManufacturingStage(String stageName) {
        this.stageName = stageName;
    }

    @Override
    public void run() {
        try {
            System.out.println(stageName + " started.");
            // Simulate the manufacturing process
            Thread.sleep(2000); // Simulating work by sleeping for 2 seconds
            System.out.println(stageName + " completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main__5_ {
    public static void main(String[] args) {
        // Creating instances of ManufacturingStage for each stage
        ManufacturingStage assemblyStage = new ManufacturingStage("Assembly");
        ManufacturingStage paintingStage = new ManufacturingStage("Painting");
        ManufacturingStage packagingStage = new ManufacturingStage("Packaging");

        // Creating threads for each stage
        Thread assemblyThread = new Thread(assemblyStage);
        Thread paintingThread = new Thread(paintingStage);
        Thread packagingThread = new Thread(packagingStage);

        // Starting threads
        assemblyThread.start();
        paintingThread.start();
        packagingThread.start();

        // Waiting for all threads to complete
        try {
            assemblyThread.join();
            paintingThread.join();
            packagingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Manufacturing process completed.");
    }
}
