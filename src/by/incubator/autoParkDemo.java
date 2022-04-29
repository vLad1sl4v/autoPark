package by.incubator;

import by.incubator.carWash.CarWashQueue;
import by.incubator.vehicle.*;

import java.util.List;

public class autoParkDemo {
    static final String path = "./src/by/incubator/data/";

    public static void main(String[] args) {
        VehicleCollection vehicleCollection = new VehicleCollection(path, path, path);

        List<VehicleType> vehicleTypes = vehicleCollection.loadTypes("types.csv");
        List<Vehicle> vehicles = vehicleCollection.loadVehicles("vehicles.csv");
        List<Rent> rents = vehicleCollection.loadRents("rents.csv");

        vehicleCollection.display();

        CarWashQueue<Vehicle> queue = initCarWashQueue(vehicles);
        washAllCars(queue);

        System.out.println(queue.size() + " Machines left in queue");
    }

    static void washAllCars(CarWashQueue<Vehicle> queue) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            washCar(queue);
        }
    }

    static void washCar(CarWashQueue<Vehicle> queue) {
        Vehicle washedCar = queue.dequeue();

        System.out.println(washedCar.getModelName() + " washed");
    }


    static CarWashQueue<Vehicle> initCarWashQueue(List<Vehicle> vehicles) {
        Vehicle[] vehiclesArr = vehicles.toArray(new Vehicle[]{});

        return new CarWashQueue<>(vehiclesArr);
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



