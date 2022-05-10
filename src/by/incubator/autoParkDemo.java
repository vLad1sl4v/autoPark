package by.incubator;


import by.incubator.engines.ElectricalEngine;
import by.incubator.Rent;
import by.incubator.vehicle.Vehicle;
import by.incubator.VehicleCollection;
import by.incubator.vehicle.VehicleType;

import java.util.Comparator;

import java.util.List;
import java.util.Queue;

public class  autoParkDemo {
    private static final String path = "./src/by/incubator/data/";
    private static final String TYPES_PATH = "src/by/incubator/data/";
    private static final String VEHICLES_PATH = "src/by/incubator/data/";
    private static final String RENTS_PATH = "src/by/incubator/data/";

    public static void main(String[] args) {
        VehicleCollection vehicleCollection = new VehicleCollection(path, path, path);

        List<VehicleType> vehicleTypes = vehicleCollection.loadTypes("types.csv");
        List<Vehicle> vehicles = vehicleCollection.loadVehicles("vehicles.csv");
        List<Rent> rents = vehicleCollection.loadRents("rents.csv");

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
