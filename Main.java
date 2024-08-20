import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class Drug {
    private String serialNumber;
    private String name;
    private String manufacturer;
    private boolean isAuthenticated;

    public Drug(String serialNumber, String name, String manufacturer) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.manufacturer = manufacturer;
        this.isAuthenticated = false;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void authenticate() {
        this.isAuthenticated = true;
    }

    @Override
    public String toString() {
        return "Drug Name: " + name + ", Serial Number: " + serialNumber + ", Manufacturer: " + manufacturer +
                ", Authenticated: " + isAuthenticated;
    }
}

class SmartContract {
    private Map<String, Drug> drugLedger = new HashMap<>();
    public void manufactureDrug(Drug drug) {
        drugLedger.put(drug.getSerialNumber(), drug);
        System.out.println("Drug manufactured: " + drug.getName() + " by " + drug.getManufacturer());
    }

    public boolean authenticateDrug(String serialNumber) {
        Drug drug = drugLedger.get(serialNumber);
        if (drug != null) {
            if (!drug.isAuthenticated()) {
                drug.authenticate();
                System.out.println("Drug authenticated: " + drug.getName() + " (Serial: " + serialNumber + ")");
                return true;
            } else {
                System.out.println("Drug already authenticated: " + drug.getName() + " (Serial: " + serialNumber + ")");
                return true;
            }
        }
        System.out.println("Drug not found for authentication: Serial " + serialNumber);
        return false;
    }

    public Drug traceDrug(String serialNumber) {
        Drug drug = drugLedger.get(serialNumber);
        if (drug != null) {
            System.out.println("Drug traced: " + drug.getName() + " (Serial: " + serialNumber + ") by " + drug.getManufacturer());
            return drug;
        }
        System.out.println("Drug not found in ledger: Serial " + serialNumber);
        return null;
    }
    public void viewAllDrugs() {
        if (drugLedger.isEmpty()) {
            System.out.println("No drugs available in the ledger.");
        } else {
            System.out.println("\nAll drugs in the ledger:");
            for (Drug drug : drugLedger.values()) {
                System.out.println(drug);
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartContract contract = new SmartContract();

        while (true) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Manufacture a Drug");
            System.out.println("2. Authenticate a Drug");
            System.out.println("3. Trace a Drug");
            System.out.println("4. View All Drugs");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Drug Serial Number: ");
                    String serialNumber = scanner.nextLine();
                    System.out.print("Enter Drug Name: ");
                    String drugName = scanner.nextLine();
                    System.out.print("Enter Manufacturer Name: ");
                    String manufacturerName = scanner.nextLine();
                    Drug drug = new Drug(serialNumber, drugName, manufacturerName);
                    contract.manufactureDrug(drug);
                    break;

                case 2:
                    System.out.print("Enter Drug Serial Number to Authenticate: ");
                    String authSerialNumber = scanner.nextLine();
                    contract.authenticateDrug(authSerialNumber);
                    break;

                case 3:
                    System.out.print("Enter Drug Serial Number to Trace: ");
                    String traceSerialNumber = scanner.nextLine();
                    contract.traceDrug(traceSerialNumber);
                    break;

                case 4:
                    contract.viewAllDrugs();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
