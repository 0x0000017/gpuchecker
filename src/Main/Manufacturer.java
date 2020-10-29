/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

public class Manufacturer {
    
    int misc_ID;
    String misc_vendorName;

    public Manufacturer(int misc_ID, String misc_vendorName) {
        this.misc_ID = misc_ID;
        this.misc_vendorName = misc_vendorName;
    }

    public int getMisc_ID() {
        return misc_ID;
    }

    public void setMisc_ID(int misc_ID) {
        this.misc_ID = misc_ID;
    }

    public String getMisc_vendorName() {
        return misc_vendorName;
    }

    public void setMisc_vendorName(String misc_vendorName) {
        this.misc_vendorName = misc_vendorName;
    }
}
