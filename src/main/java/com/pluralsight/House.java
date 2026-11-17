package com.pluralsight;

import java.math.BigDecimal;

public class House extends Asset{
    private String address;
    private int condition;
    private int squareFoot;
    private int lotSize;

    public House(String description, String dateAcquired, BigDecimal originalCost, String address, int condition, int squareFoot, int lotSize){
        // call Asset properties for "extends"
        super(description, dateAcquired, originalCost);

        this.address = address;
        this.condition = condition;
        this.squareFoot = squareFoot;
        this.lotSize = lotSize;
    }

    // getters and setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getSquareFoot() {
        return squareFoot;
    }

    public void setSquareFoot(int squareFoot) {
        this.squareFoot = squareFoot;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    // override getValue()
    public BigDecimal getValue(){
        BigDecimal pricePerSquare;

        // choose condition
        switch (condition){
            case 1 -> pricePerSquare = new BigDecimal("180.00");
            case 2 -> pricePerSquare = new BigDecimal("130.00");
            case 3 -> pricePerSquare = new BigDecimal("90.00");
            case 4 -> pricePerSquare = new BigDecimal("80.00");
            default -> {
                System.out.println("wrong input try again");
                return BigDecimal.ZERO;
            }
        }

        // we need to multiply the rate
        BigDecimal houseValue = pricePerSquare.multiply(BigDecimal.valueOf((squareFoot)));

        // value of land
        BigDecimal landValue = new BigDecimal("0.25").multiply(BigDecimal.valueOf(lotSize));

        // combine them
        return houseValue.add(landValue);
    }

}
