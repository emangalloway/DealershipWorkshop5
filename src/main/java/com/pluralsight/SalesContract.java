package com.pluralsight;

public class SalesContract extends Contract{
    private double salesTaxAMount;
    private double recordingFee;
    private double processingFee;
    private boolean finance;
    private double monthlyPayment;

    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, boolean finance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.salesTaxAMount = vehicleSold.getPrice() * .05;
        this.recordingFee = 100;
        this.processingFee = (vehicleSold.getPrice() < 1000) ? 295 : 495;
        this.finance = finance;
    }

    public double getSalesTaxAMount() {
        return salesTaxAMount;
    }

    public void setSalesTaxAMount(double salesTaxAMount) {
        this.salesTaxAMount = salesTaxAMount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + salesTaxAMount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        int numberOfPayments = 0;
        double interestRate = 0;
        if (finance) {
            if (getVehicleSold().getPrice() >= 10000) {
                numberOfPayments = 48;
                interestRate = 4.25 / 1200;
            } else {
                numberOfPayments = 24;
                interestRate = 5.25 / 1200;
            }

            double monthlyPayment = getTotalPrice() * (interestRate * Math.pow(1 + interestRate, numberOfPayments)) / (Math.pow(1 + interestRate, numberOfPayments) - 1);
            monthlyPayment = Math.round(monthlyPayment * 100);
            monthlyPayment /= 100;
            return monthlyPayment;
        } else {
            return 0.0;
        }
    }

    @Override
    public String toString() {
        return "SALE|"+ getDateOfContract()+ "|" +getCustomerName() +"|"+ getCustomerEmail() +"|"+ getVehicleSold().getVin()+
                "|"+ getVehicleSold().getYear()+"|"+getVehicleSold().getMake()+ "|"+getVehicleSold().getModel()+
                "|"+getVehicleSold().getVehicleType()+"|"+getVehicleSold().getColor()+"|"+getVehicleSold().getOdometer()+"|"+getVehicleSold().getPrice()+
                "|"+salesTaxAMount + recordingFee + processingFee + finance + monthlyPayment;
    }
}
