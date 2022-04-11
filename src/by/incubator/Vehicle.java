package by.incubator;

import by.incubator.engines.Startable;

public class Vehicle implements Comparable<Vehicle>{
    private final VehicleType vehicleType;
    private final String modelName;
    private String registrationNumber;
    private int mass;
    private final int manufactureYear;
    private int mileAge;
    private Colors color;
    private double tankLitres;
    Startable engine;

    public Vehicle(VehicleType vehicleType, String modelName, String registrationNumber, int mass, int manufactureYear,
                   int mileAge, Colors color, Startable engine) {
        if (TechnicalSpecialist.validateVehicleType(vehicleType)) {
            this.vehicleType = vehicleType;
        } else {
            this.vehicleType = new VehicleType();
        }

        if (TechnicalSpecialist.validateModelName(modelName)) {
            this.modelName = modelName;
        } else {
            this.modelName = "Default model";
        }

        if (TechnicalSpecialist.validateRegistrationNumber(registrationNumber)) {
            this.registrationNumber = registrationNumber;
        }

        if (TechnicalSpecialist.validateWeight(mass)) {
            this.mass = mass;
        }

        if (TechnicalSpecialist.validateManufactureYear(manufactureYear)) {
            this.manufactureYear = manufactureYear;
        } else {
            this.manufactureYear = TechnicalSpecialist.LOWER_LIMIT_MANUFACTURE_YEAR;
        }

        if (TechnicalSpecialist.validateMileage(mileAge)) {
            this.mileAge = mileAge;
        }

        if (TechnicalSpecialist.validateColor(color)) {
            this.color = color;
        }

        this.engine = engine;
    }

    public VehicleType getVehicleType () {
        return vehicleType;
    }

    public String getModelName() {
        return modelName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Startable getEngine() {
        return engine;
    }

    public void setEngine(Startable engine) {
        this.engine = engine;
    }

    public void setRegistrationNumber(String registrationNumber) {
        if (TechnicalSpecialist.validateRegistrationNumber(registrationNumber)) {
            this.registrationNumber = registrationNumber;
        }
    }

    public double getMass() {
        return mass;
    }

    public void setMass(int mass) {
        if (TechnicalSpecialist.validateWeight(mass)) {
            this.mass = mass;
        }
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public double getMileAge() {
        return mileAge;
    }

    public void setMileAge(int mileAge) {
        if (TechnicalSpecialist.validateMileage(mileAge)) {
            this.mileAge = mileAge;
        }
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public double getTankLitres() {
        return tankLitres;
    }

    public void setTankLitres(double tankLitres) {
        if (tankLitres > 0) {
            this.tankLitres = tankLitres;
        }
    }

    double getCalcTaxPerMonth() {
        return mass * 0.0013 + engine.getTaxPerMonth() * vehicleType.getTaxCoefficient() * 30 + 5; //round()
    }

    @Override
    public String toString() {
        return vehicleType.getTypeName() + "," + modelName + "," + registrationNumber + "," + mass + ","
                + manufactureYear + "," + mileAge + "," + color + "," + getCalcTaxPerMonth() + "," + engine.toString(); //""
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicleType.equals(vehicle.vehicleType)
                && modelName.equals(vehicle.modelName);
    }

    @Override
    public int hashCode() {
        int hash = 1;

        hash = 31 * hash + vehicleType.hashCode();
        hash = 31 * hash + modelName.hashCode();

        return hash;
    }

    @Override
    public int compareTo(Vehicle o) {
        int result = 0;

        if (manufactureYear < o.manufactureYear) {result = -1;}
        else if (manufactureYear > o.manufactureYear) {result = 1;}
        else {
            if (mileAge < o.mileAge) {result = -1;}
            if (mileAge > o.mileAge) {result = 1;}
        }

        return result;
    }
}
