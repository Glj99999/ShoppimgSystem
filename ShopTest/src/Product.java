import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Product {
    private String productNumber;
    private String productName;
    private String manufacturer;
    private String productionDate;
    private String model;
    private double purchasePrice;
    private double retailPrice;
    private int quantity;

    public Product(String productNumber, String productName, String manufacturer, String productionDate, String model,
                   double purchasePrice, double retailPrice, int quantity) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.productionDate = productionDate;
        this.model = model;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
        this.quantity = quantity;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public String getModel() {
        return model;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }
}
