package by.incubator;

import by.incubator.engines.DieselEngine;
import by.incubator.engines.ElectricalEngine;
import by.incubator.engines.GasolineEngine;

public class autoParkDemo {
    public static void main(String[] args) {
        final int CAR_TYPES_SIZE = 4;
        final int CARS_SIZE = 7;
        VehicleType[] types = new VehicleType[CAR_TYPES_SIZE];
        double maxCoefficient = 0.0d;
        double avgCoefficient = 0.0d;

        types[0] = new VehicleType("Bus", 1.2d);
        types[1] = new VehicleType("Car", 1.0d);
        types[2] = new VehicleType("Rink", 1.5d);
        types[3] = new VehicleType("Tractor", 1.2d);

        for (int i = 0; i < CAR_TYPES_SIZE; i++){
            types[i].display();
        }
        System.out.println();

        types[3].setTaxCoefficient(1.3d);

        for (int i = 0; i < CAR_TYPES_SIZE; i++){
            types[i].display();

            if (types[i].getTaxCoefficient() > maxCoefficient) {
                maxCoefficient = types[i].getTaxCoefficient();
            }

            avgCoefficient += types[i].getTaxCoefficient() / CAR_TYPES_SIZE;
        }
        System.out.println("Maximum coefficient: " + maxCoefficient);
        System.out.println("Average coefficient: " + avgCoefficient);

        Vehicle[] vehicles = new Vehicle[CARS_SIZE];

        vehicles[0] = new Vehicle(types[0], "Volkswagen Crafter", "5427 AX-7", 2022,
                        2015, 376000, Colors.BLUE, new GasolineEngine(2, 8.1, 75));
        vehicles[1] = new Vehicle(types[0], "Volkswagen Crafter", "6427 AA-7", 2500,
                2014, 227010, Colors.WHITE, new GasolineEngine(2.18, 8.5, 75));
        vehicles[2] = new Vehicle(types[0], "Electric Bus E321", "6785 BA-7", 12080,
                2019, 20451, Colors.GREEN, new ElectricalEngine(50, 150));
        vehicles[3] = new Vehicle(types[1], "Golf 5", "8682 AX-7", 1200,
                2006, 230451, Colors.GRAY, new DieselEngine(1.6, 7.2, 55));
        vehicles[4] = new Vehicle(types[1], "Tesla Model S 70D", "0001 AA-7", 2200,
                2019, 10454, Colors.WHITE, new ElectricalEngine(25, 70));
        vehicles[5] = new Vehicle(types[2], "Hamm HD 12 VV", null, 3000,
                2016, 122, Colors.YELLOW, new DieselEngine(3.2, 25, 20));
        vehicles[6] = new Vehicle(types[3], "МТЗ Беларус-1025.4", "1145 AB-7", 1200,
                2020, 109, Colors.RED, new DieselEngine(4.75, 20.1, 135));

        Helper.printArray(vehicles);

        System.out.println();

        vehicles = sortVehicles(vehicles);

        System.out.println("Sorted array: ");
        Helper.printArray(vehicles);
        System.out.println();
        System.out.println();

        Vehicle maxMileAgeVehicle = getMaxAgeVehicle(vehicles);
        Vehicle minMileAgeVehicle = getMinAgeVehicle(vehicles);

        System.out.println("Car with minimal mileage: ");
        System.out.println(minMileAgeVehicle);
        System.out.println("Car with maximal mileage: ");
        System.out.println(maxMileAgeVehicle);
        System.out.println();

        System.out.println("Let's find out if there are some same vehicles: ");

        displayEqualVehicles(vehicles);
        System.out.println();
        System.out.println();

        System.out.println("Vehicle that can do the longest ride:");
        System.out.println(findMaxKilometersVehicle(vehicles));
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



