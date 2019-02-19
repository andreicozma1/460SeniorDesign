package com.example.andrei.a460seniordesign;

import android.app.Application;

import com.ugrokit.api.Ugi;
import com.ugrokit.api.UgiInventoryDelegate;
import com.ugrokit.api.UgiRfidConfiguration;
import com.ugrokit.api.UgiTag;

public class ApiDemoApp extends Application{

    private Ugi ugi;
    public Ugi getUgi() { return ugi; }

    @Override public void onCreate() {
        super.onCreate();
        System.out.println("Here");
        ugi = Ugi.createSingleton(this);
        ugi.openConnection();

    }

    /*
    @Override public void inventoryTagChanged(UgiTag tag, boolean firstFind) {
        System.out.println("Here 1");
        if (firstFind) {
            System.out.println("Found tag for the first time");
        } else if (tag.isVisible()) {
            // tag was not visible, is now visible again
            System.out.println("Tag is visible again");
        } else {
            System.out.println("Tag is not visible");
        }
    }
    */
}
