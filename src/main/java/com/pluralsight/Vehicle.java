package com.pluralsight;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.BiFunction;

public class Vehicle extends Asset {
    // properties
    private String makeModel;
    private int year;
    private int odometer;

    // constructors
    public Vehicle(String description, String dateAcquired, BigDecimal originalCost, String makeModel, int year, int odometer){
        super(description, dateAcquired, originalCost);

        this.makeModel = makeModel;
        this.year = year;
        this.odometer = odometer;
    }

    // getters and setters
    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    // override getValue()
    public BigDecimal getValue(){
        // Set up the values used in the calculation.
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - year;
        BigDecimal value;
        BigDecimal yearlyRate = BigDecimal.ZERO;
        BigDecimal totalRate;
        BigDecimal depreciation;

        String lowerMakeModel = makeModel.toLowerCase();
        boolean isHondaOrToyota = lowerMakeModel.contains("honda") || lowerMakeModel.contains("toyota");

        // Treat future model years as zero years old.
        if (age < 0) age = 0;

        // Choose the yearly depreciation rate.
        if (age <= 3) {
            yearlyRate = new BigDecimal("0.03");
        } else if (age <= 6) {
            yearlyRate = new BigDecimal("0.06");
        } else if (age <= 10) {
            yearlyRate = new BigDecimal("0.08");
        }

        // Calculate the total percentage and dollar amount lost.
        totalRate = yearlyRate.multiply(BigDecimal.valueOf(age));
        depreciation = getOriginalCost().multiply(totalRate);

        // Determine the value based on age.
        if (age > 10) {
            value = new BigDecimal("1000.00");
        } else {
            value = getOriginalCost().subtract(depreciation);
        }

        // Reduce the value by 25% for high mileage, except Honda or Toyota.
        if (odometer > 100_000 && !isHondaOrToyota) value = value.multiply(new BigDecimal("0.75"));

        return value;

    }
}
