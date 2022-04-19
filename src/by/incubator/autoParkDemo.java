package by.incubator;

import by.incubator.carGarage.CarGarageStack;
import by.incubator.engines.ElectricalEngine;
import by.incubator.vehicle.Rent;
import by.incubator.vehicle.Vehicle;
import by.incubator.vehicle.VehicleCollection;
import by.incubator.vehicle.VehicleType;

import java.util.Comparator;
import java.util.List;

public class autoParkDemo {
    public static void main(String[] args) {
        final String path = "./src/by/incubator/data/";
        VehicleCollection vehicleCollection = new VehicleCollection(path, path, path);

        List<VehicleType> vehicleTypes = vehicleCollection.loadTypes("types.csv");
        List<Vehicle> vehicles = vehicleCollection.loadVehicles("vehicles.csv");
        List<Rent> rents = vehicleCollection.loadRents("rents.csv");

        vehicleCollection.display();

        CarGarageStack<Vehicle> vehicleStack = fillInGarage(vehicles);
        driveOutAllCars(vehicleStack);
    }

    static void driveOutAllCars(CarGarageStack<Vehicle> vehicleStack) {
        int size = vehicleStack.size();

        for (int i = 0; i < size; i++) {
            driveOutCar(vehicleStack);
        }
    }

    static void driveOutCar(CarGarageStack<Vehicle> vehicleStack) {
        Vehicle lastVehicle = vehicleStack.pop();
        System.out.println(lastVehicle.getModelName() + " got out of garage");
    }

    static CarGarageStack<Vehicle> fillInGarage(List<Vehicle> vehicles) {
        Vehicle[] vehiclesArr = vehicles.toArray(new Vehicle[0]);
        CarGarageStack<Vehicle> stack = new CarGarageStack<>();

        for (int i = 0; i < vehiclesArr.length; i++) {
            stack.push(vehiclesArr[i]);
            System.out.println(vehiclesArr[i].getModelName() + " got in garage");
        }

        System.out.println("garage is filled");

        return stack;
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



