package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractsFileManager {
    public void saveContract(Contract contract) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("contract.csv",true))) {
            // Write Contract information
            bw.write(contract.getDateOfContract() + "|" + contract.getCustomerName() + "|" + contract.getCustomerEmail()+ "|"+ contract.getVehicleSold()+ "|" +contract.getTotalPrice()+ "|" +contract.getMonthlyPayment());
            bw.newLine();

            // Write contract Inventory
            if (contract instanceof SalesContract){
                SalesContract salesContract = (SalesContract) contract;
                bw.write(salesContract.toString());
            }
            if (contract instanceof LeaseContract){
                LeaseContract leaseContract = (LeaseContract) contract;
                bw.write(leaseContract.toString());
            }

            System.out.println("Dealership saved successfully to dealership.csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}