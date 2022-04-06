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
                        2015, 376000, Colors.BLUE);
        vehicles[1] = new Vehicle(types[0], "Volkswagen Crafter", "6427 AA-7", 2500,
                2014, 227010, Colors.WHITE);
        vehicles[2] = new Vehicle(types[0], "Electric Bus E321", "6785 BA-7", 12080,
                2019, 20451, Colors.GREEN);
        vehicles[3] = new Vehicle(types[1], "Golf 5", "8682 AX-7", 1200,
                2006, 230451, Colors.GRAY);
        vehicles[4] = new Vehicle(types[1], "Tesla Model S 70D", "0001 AA-7", 2200,
                2019, 10454, Colors.WHITE);
        vehicles[5] = new Vehicle(types[2], "Hamm HD 12 VV", null, 3000,
                2016, 122, Colors.YELLOW);
        vehicles[6] = new Vehicle(types[3], "МТЗ Беларус-1025.4", "1145 AB-7", 1200,
                2020, 109, Colors.RED);

        Helper.printArray(vehicles);

        System.out.println();
        System.out.println();

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

        Helper.printArray(vehicles);
        System.out.println();
        System.out.println();

        Vehicle maxMileAgeVehicle = vehicles[0];
        Vehicle minMileAgeVehicle = vehicles[0];

        for (int i = 1; i < vehicles.length; i++) {
            if (vehicles[i].getMileAge() > maxMileAgeVehicle.getMileAge()){
                maxMileAgeVehicle = vehicles[i];
            }
            if (vehicles[i].getMileAge() < minMileAgeVehicle.getMileAge()){
                minMileAgeVehicle = vehicles[i];
            }
        }

        System.out.println("Car with minimal mileage: ");
        System.out.println(minMileAgeVehicle);
        System.out.println("Car with maximal mileage: ");
        System.out.println(maxMileAgeVehicle);

    }

    static class Helper{
        static <T> void printArray(T[] arr) {
            for (T arrEl : arr) {
                System.out.println(arrEl);
            }
        }
    }
}



