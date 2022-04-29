package by.incubator;

import by.incubator.engines.ElectricalEngine;
import by.incubator.Rent;
import by.incubator.vehicle.Vehicle;
import by.incubator.VehicleCollection;
import by.incubator.vehicle.VehicleType;

import java.util.Comparator;
import java.util.List;

public class autoParkDemo {
    static final String path = "./src/by/incubator/data/";

    public static void main(String[] args) {
        VehicleCollection vehicleCollection = new VehicleCollection(path, path, path);

        List<VehicleType> vehicleTypes = vehicleCollection.loadTypes("types.csv");
        List<Vehicle> vehicles = vehicleCollection.loadVehicles("vehicles.csv");
        List<Rent> rents = vehicleCollection.loadRents("rents.csv");

        vehicleCollection.display();

        Vehicle vehicle1 = new Vehicle(8, new VehicleType(1, "Bus", 2.2), "Volkswagen Crafter", "5427 AX-7", 2022, 2015, 376000, Colors.BLUE,
                new ElectricalEngine(1.2, 1.1));
        Vehicle vehicle2 = new Vehicle(9, new VehicleType(1, "Bus", 2.2), "Volkswagen Crafter", "5427 AX-7", 2022, 2015, 376000, Colors.BLUE,
                new ElectricalEngine(1.2, 1.1));

        detectBreakages(vehicleCollection);
        repairAllVehicles(vehicleCollection);

    }

    private static void repairAllVehicles(VehicleCollection vehicleCollection) {
        MechanicService mechanicService = new MechanicService();

        for (Vehicle vehicle : vehicleCollection.getVehicles()) {
            if (mechanicService.isBroken(vehicle)) {
                mechanicService.repair(vehicle);
                System.out.println("Repaired vehicle: " + vehicle);
            }
        }
    }

    private static void detectBreakages(VehicleCollection vehicleCollection) {
        MechanicService mechanicService = new MechanicService();

        for(Vehicle vehicle : vehicleCollection.getVehicles()) {
            mechanicService.detectBreaking(vehicle);

            if (!mechanicService.isBroken(vehicle)) {
                System.out.println("Not broken vehicle: " + vehicle);
            }
        }
    }
}


