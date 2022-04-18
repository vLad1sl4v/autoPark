package by.incubator;

import by.incubator.engines.ElectricalEngine;
import by.incubator.engines.GasolineEngine;
import by.incubator.vehicle.Vehicle;
import by.incubator.vehicle.VehicleCollection;
import by.incubator.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class autoParkDemo {
    public static void main(String[] args) {
        final String path = "./src/by/incubator/data/";
        VehicleCollection vehicleCollection = new VehicleCollection(path, path, path);

        List<VehicleType> vehicleTypes = vehicleCollection.loadTypes("types.csv");
        List<Vehicle> vehicles = vehicleCollection.loadVehicles("vehicles.csv");
        List<Rent> rents = vehicleCollection.loadRents("rents.csv");

        vehicleCollection.display();

        Vehicle vehicle = new Vehicle(8,new VehicleType(1, "Bus", 2.2),"Volkswagen Crafter","5427 AX-7",2022,2015,376000, Colors.BLUE,
                new ElectricalEngine(1.2, 1.1));

        vehicles.add(vehicle);
        vehicleCollection.delete(1);
        vehicleCollection.delete(4);

        vehicleCollection.display();

        vehicles.sort(new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                int result = 0;

                if (o1.getManufactureYear() < o2.getManufactureYear()) {result = -1;}
                else if (o1.getManufactureYear() > o2.getManufactureYear()) {result = 1;}
                else {
                    if (o1.getMileAge() < o2.getMileAge()) {result = -1;}
                    if (o1.getMileAge() > o2.getMileAge()) {result = 1;}
                }
                return result;
            }
        });

        vehicleCollection.display();
    }

    static class Helper{
        static <T> void printArray(T[] arr) {
            for (int i = 0; i < arr.length; i++) {
                    System.out.println(arr[i]);
            }
        }
    }

    static Vehicle[] sortVehicles(Vehicle[] vehicles) {
        Vehicle memory;

        for (int i = 1; i < vehicles.length; i++) {
            for (int j = 1; j < vehicles.length; j++) {
                if (vehicles[j].compareTo(vehicles[j - 1]) < 0) {
                    memory = vehicles[j - 1];
                    vehicles[j - 1] = vehicles[j];
                    vehicles[j] = memory;
                }
            }
        }

        return vehicles;
    }

    static Vehicle getMaxAgeVehicle(Vehicle[] vehicles) {
        Vehicle maxMileAgeVehicle = vehicles[0];

        for (int i = 1; i < vehicles.length; i++) {
            if (vehicles[i].getMileAge() > maxMileAgeVehicle.getMileAge()){
                maxMileAgeVehicle = vehicles[i];
            }
        }

        return maxMileAgeVehicle;
    }

    static Vehicle getMinAgeVehicle(Vehicle[] vehicles) {
        Vehicle minMileAgeVehicle = vehicles[0];

        for (int i = 1; i < vehicles.length; i++) {
            if (vehicles[i].getMileAge() < minMileAgeVehicle.getMileAge()){
                minMileAgeVehicle = vehicles[i];
            }
        }

        return minMileAgeVehicle;
    }

    static void displayEqualVehicles(Vehicle[] vehicles) {
        int equalCount = 0;

        for (int i = 0; i < vehicles.length; i++) {
            for (int j = i + 1; j < vehicles.length; j++) {
                if (vehicles[i].equals(vehicles[j])) {
                    equalCount++;

                    System.out.println(vehicles[i] + "\n and \n" + vehicles[j]);
                }
            }
        }

        if (equalCount == 0) {
            System.out.println("No equal vehicles");
        }
    }

    static Vehicle findMaxKilometersVehicle(Vehicle[] vehicles) {
        Vehicle maxKmVehicle = vehicles[0];

        for (int i = 1; i < vehicles.length; i++) {
            if (vehicles[i].getEngine().getMaxKilometers() > maxKmVehicle.getEngine().getMaxKilometers()) {
                maxKmVehicle = vehicles[i];
            }
        }

        return maxKmVehicle;
    }
}



