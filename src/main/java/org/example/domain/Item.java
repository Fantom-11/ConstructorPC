package org.example.domain;

public class Item {
    private Component component;

    private String model;

    private Manufacturer manufacturer;

    private float price;

    public Item(Component component, String model, Manufacturer manufacturer, float price) {
        this.component = component;
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public Item() {
    }

    public Item(Component component, String model) {
        this.component = component;
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public float getPrice() {
        return price;
    }
}
