public class ManuSys_lab_3 {

    public static void main(String[] args) {
        System.out.println("\nProd Details");

        ElecDev smartphone = new ElecDev("SD-123", 0.05);
        double smartphoneProdCost = smartphone.calcProdCost(500);
        System.out.println("\nProd cost for Smartphone: $" + smartphoneProdCost);

        Mach machinery = new Mach("M-456", 0.08, 3);
        double machMaintCost = machinery.calcProdCost(2000);  // Corrected method name
        System.out.println("Maint cost for Machinery: $" + machMaintCost);

        machinery.displayTotalCost(2000);
    }
}

abstract class ManuProd {
    protected String prodId;
    protected double prodRate;

    public ManuProd(String prodId, double prodRate) {
        this.prodId = prodId;
        this.prodRate = prodRate;
    }

    public abstract double calcProdCost(double matCost);
}

class ElecDev extends ManuProd {
    public ElecDev(String prodId, double prodRate) {
        super(prodId, prodRate);
    }

    @Override
    public double calcProdCost(double matCost) {
        return matCost * prodRate;
    }
}

final class Mach extends ManuProd {
    private int yearsInOp;

    public Mach(String prodId, double prodRate, int yearsInOp) {
        super(prodId, prodRate);
        this.yearsInOp = yearsInOp;
    }

    @Override
    public double calcProdCost(double matCost) {
        return matCost * prodRate * yearsInOp;
    }

    public final void displayTotalCost(double matCost) {
        double totCost = matCost + calcProdCost(matCost);
        System.out.println("Tot Cost for " + prodId + ": $" + totCost + "\n");
    }
}
