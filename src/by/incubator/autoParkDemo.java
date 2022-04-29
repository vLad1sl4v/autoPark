package by.incubator;

import by.incubator.carWash.CarWashQueue;
import by.incubator.vehicle.*;
import by.incubator.carWash.CarWashQueue;
import by.incubator.vehicle.*;
import java.util.List;
import java.util.Queue;

public class autoParkDemo {
    private static final String path = "./src/by/incubator/data/";
    private static final String TYPES_PATH = "src/by/incubator/data/";
    private static final String VEHICLES_PATH = "src/by/incubator/data/";
    private static final String RENTS_PATH = "src/by/incubator/data/";

    public static void main(String[] args) {
        VehicleCollection vehicleCollection = new VehicleCollection(path, path, path);

        List<VehicleType> vehicleTypes = vehicleCollection.loadTypes("types.csv");
        List<Vehicle> vehicles = vehicleCollection.loadVehicles("vehicles.csv");
        List<Rent> rents = vehicleCollection.loadRents("rents.csv");

        CarWashQueue<Vehicle> queue = initCarWashQueue(vehicles);
        washAllCars(queue);

        System.out.println(queue.size() + " Machines left in queue");

    }

    static CarWashQueue<Vehicle> initCarWashQueue(List<Vehicle> vehicles) {
        Vehicle[] vehiclesArr = vehicles.toArray(new Vehicle[]{});

        return new CarWashQueue<>(vehiclesArr);
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

}

