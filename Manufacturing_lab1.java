class ManufacturingCompany {
    private String companyName;
    private int employeeCount;
    private String location;

    
    public ManufacturingCompany() {
        companyName = "Default Manufacturing Company";
        employeeCount = 0;
        location = "Unspecified";
    }

   
    public ManufacturingCompany(String name, int employees) {
        companyName = name;
        employeeCount = employees;
        location = "Unspecified";
    }

  
    public ManufacturingCompany(String name, int employees, String companyLocation) {
        companyName = name;
        employeeCount = employees;
        location = companyLocation;
    }

    
    public void printCompanyDetails() {
        System.out.println("Company Name: " + companyName);
        System.out.println("Employee Count: " + employeeCount);
        System.out.println("Location: " + location);
    }


    public void printCompanyDetails(String additionalInfo) {
        System.out.println("Company Name: " + companyName);
        System.out.println("Employee Count: " + employeeCount);
        System.out.println("Additional Info: " + additionalInfo);
    }

    	
    public void printCompanyDetails(String companyLocation, int newEmployeeCount) {
        System.out.println("Company Name: " + companyName);
        System.out.println("Employee Count: " + newEmployeeCount);
        System.out.println("Location: " + companyLocation);
    }
}

public class Manufacturing_lab1 {
    public static void main(String[] args) {
        ManufacturingCompany defaultCompany = new ManufacturingCompany();
        ManufacturingCompany customCompany = new ManufacturingCompany("Custom Manufacturing Co.", 100);
        ManufacturingCompany customCompanyWithLocation = new ManufacturingCompany("Specific Co.", 50, "Industrial Park");

        System.out.println("Default Company Details:");
        defaultCompany.printCompanyDetails();

        System.out.println("\nCustom Company Details:");
        customCompany.printCompanyDetails();

        System.out.println("\nCustom Company Details with Additional Info:");
        customCompany.printCompanyDetails("This is a custom company.");

        System.out.println("\nCustom Company Details with Different Employee Count and Location:");
        customCompanyWithLocation.printCompanyDetails("Downtown", 75);
    }
}
