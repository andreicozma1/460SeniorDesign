package com.example.andrei.a460seniordesign;

import android.app.Application;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.ugrokit.api.*;
public class MainActivity extends UgiActivity implements Ugi.ConnectionStateListener, UgiInventoryDelegate.InventoryTagSubsequentFindsListener,UgiInventoryDelegate,UgiInventoryDelegate.InventoryTagChangedListener {

    @Override
    public Ugi getUgi() {
        ApiDemoApp app = (ApiDemoApp) this.getApplication();
        return app.getUgi();
    }

    @Override
    public boolean ugiShouldHandleRotation() {
        // returning "true" here causes the Ugi code to automatically flip the
        // screen on devices where the audio port is on the top of the phone
        return true;
    }

    @Override public void connectionStateChanged(Ugi.ConnectionStates connectionState) {
        //Log.i(TAG, "connectionStateChanged: " + connectionState);
        if (connectionState == Ugi.ConnectionStates.CONNECTED) {
            System.out.println("Connected");

           try {
               getUgi().startInventory(this, UgiRfidConfiguration.forInventoryType(UgiRfidConfiguration.InventoryTypes.INVENTORY_SHORT_RANGE));
           }catch(Exception e){

           }
           }
    }
    @Override public void inventoryTagChanged(UgiTag tag, boolean firstFind) {

        System.out.println(tag.getEpc() + " " + firstFind);
        if (firstFind) {
            UgiInventory.TagAccessCompletion a = new UgiInventory.TagAccessCompletion() {
                @Override
                public void exec(UgiTag ugiTag, UgiInventory.TagAccessReturnValues tagAccessReturnValues) {
                    System.out.println(tagAccessReturnValues);
                }
            };
            UgiEpc one = new UgiEpc("1234567890abcdef12345678");
            getUgi().getActiveInventory().programTag(tag.getEpc(), one, UgiInventory.NO_PASSWORD, a);

            //getUgi().getActiveInventory().lockUnlockTag(tag.getEpc(), , UgiInventory.NO_PASSWORD, );
            System.out.println("Found tag for the first time");
        } else if (tag.isVisible()) {
            System.out.println("Tag is visible again");
        } else {
            System.out.println("Tag is not visible");
        }
    }
    @Override public void inventoryTagSubsequentFinds(UgiTag tag, int count, UgiInventory.DetailedPerReadData[] detailedPerReadData) {
        System.out.println(tag.getEpc() + " " + count);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUgi().addConnectionStateListener(this);
    }

}

