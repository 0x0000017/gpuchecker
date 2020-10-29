
package Main;

public class Items {
    
    int item_ID;
    String item_name;
    int item_quantity;
    double item_price;
    int item_memory;
    
    
    String misc_vendorName;
    int misc_ID;

    public Items(int item_ID, String item_name, int item_quantity, double item_price, int item_memory, String misc_vendorName, int misc_ID) {
        this.item_ID = item_ID;
        this.item_name = item_name;
        this.item_quantity = item_quantity;
        this.item_price = item_price;
        this.item_memory = item_memory;
        this.misc_vendorName = misc_vendorName;
        this.misc_ID = misc_ID;
    }

    public int getItem_ID() {
        return item_ID;
    }

    public void setItem_ID(int item_ID) {
        this.item_ID = item_ID;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public int getItem_memory() {
        return item_memory;
    }

    public void setItem_memory(int item_memory) {
        this.item_memory = item_memory;
    }

    public String getMisc_vendorName() {
        return misc_vendorName;
    }

    public void setMisc_vendorName(String misc_vendorName) {
        this.misc_vendorName = misc_vendorName;
    }

    public int getMisc_ID() {
        return misc_ID;
    }

    public void setMisc_ID(int misc_ID) {
        this.misc_ID = misc_ID;
    }
}