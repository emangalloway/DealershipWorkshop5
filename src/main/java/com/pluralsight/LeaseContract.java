package com.pluralsight;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leasingFee;
    private double monthlyPayment;

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = getVehicleSold().getPrice() * .5;
        this.leasingFee = getVehicleSold().getPrice() * .07;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeasingFee() {
        return leasingFee;
    }

    public void setLeasingFee(double leasingFee) {
        this.leasingFee = leasingFee;
    }

    @Override
    public double getTotalPrice() {
        return (getVehicleSold().getPrice() - expectedEndingValue) + leasingFee;
    }

    @Override
    public double getMonthlyPayment() {
        int numberOfPayments = 36;
        double interestRate = 4.0 / 1200;
        double monthlyPayment = getTotalPrice() * (interestRate * Math.pow(1 + interestRate, numberOfPayments)) / (Math.pow(1 + interestRate, numberOfPayments) - 1);
        monthlyPayment = Math.round(monthlyPayment * 100);
        monthlyPayment /= 100;
        return monthlyPayment;
    }

    @Override
    public String toString() {
        return "SALE|"+ getDateOfContract()+ "|" +getCustomerName() +"|"+ getCustomerEmail() +"|"+ getVehicleSold().getVin()+
                "|"+ getVehicleSold().getYear()+"|"+getVehicleSold().getMake()+ "|"+getVehicleSold().getModel()+
                "|"+getVehicleSold().getVehicleType()+"|"+getVehicleSold().getColor()+"|"+getVehicleSold().getOdometer()+"|"+getVehicleSold().getPrice()+
                "|"+expectedEndingValue + leasingFee + monthlyPayment;
    }
}
