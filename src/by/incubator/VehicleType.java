package by.incubator;

public class VehicleType {
    private String typeName;
    private double taxCoefficient;

    public VehicleType() {
    }

    public VehicleType(String typeName, double taxCoefficient) {
        this.typeName = typeName;
        this.taxCoefficient = taxCoefficient;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getTaxCoefficient() {
        return taxCoefficient;
    }

    public void setTaxCoefficient(double taxCoefficient) {
        this.taxCoefficient = taxCoefficient;
    }

    public void display() {
        System.out.println("Type Name: " + typeName + "; Tax Coefficient: " + taxCoefficient + ";");
    }

    public void getString() {
        System.out.println(typeName + ",\"" + taxCoefficient + "\"");
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleType)) return false;
        VehicleType vehicleType = (VehicleType) o;
        return typeName.equals(vehicleType.typeName);
    }

    @Override
    public int hashCode() {
        int hash = 1;

        hash = 31 * hash + typeName.hashCode();

        return hash;
    }
}
