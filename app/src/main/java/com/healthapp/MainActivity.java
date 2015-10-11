package com.healthapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.healthapp.fragment.Home;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * Created by Danish on 11-Oct-15.
 */

public class MainActivity extends AppCompatActivity {

    public Toolbar toolbar;
    private Drawer drawer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);                                     //Creating the Toolbar.
        toolbar.setBackgroundColor(getResources().getColor(R.color.primary));
        toolbar.setTitle(getResources().getString(R.string.app_name));
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            toolbar.setElevation(getResources().getDimension(R.dimen.toolbar_elevation));
        }
        setSupportActionBar(toolbar);

        AccountHeader acthdr = new AccountHeaderBuilder()                                   //Header Object for Drawer
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withSelectionFirstLine(getResources().getString(R.string.app_name))
                .withSavedInstance(savedInstanceState)
                .build();

        drawer = new DrawerBuilder()                                                        //Drawer Object
                .withAccountHeader(acthdr)
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(GoogleMaterial.Icon.gmd_home).withIdentifier(0),                  //Primary Items
                        new PrimaryDrawerItem().withName("Sports").withIcon(GoogleMaterial.Icon.gmd_directions_run).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Nutrition").withIcon(GoogleMaterial.Icon.gmd_brightness_low).withIdentifier(2),
                        new DividerDrawerItem(),                                                                                            //Divider
                        new SecondaryDrawerItem().withName("Tips & Tricks").withIcon(GoogleMaterial.Icon.gmd_done_all).withIdentifier(3),   //Secondary Items
                        new SecondaryDrawerItem().withName("Tools").withIcon(GoogleMaterial.Icon.gmd_build).withIdentifier(4)
                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        if (iDrawerItem != null) {
                            switch (iDrawerItem.getIdentifier()) {
                                case 0:
                                    //Performs action when the section is selected
                                    //Fragment Trasaction...
                                    Fragment h = new Home();
                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.replace(R.id.container, h);
                                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                    ft.commit();
                                case 1:
                                    //Performs action when the section is selected
                                case 2:
                                    //Performs action when the section is selected
                                case 3:
                                    //Performs action when the section is selected
                                case 4:
                                    //Performs action when the section is selected
                            }

                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        if (savedInstanceState == null) {
            drawer.setSelectionByIdentifier(0);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = drawer.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen()) {          //Closes the drawer on back pressed and if the drawer is closed closes the app
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();                              //Overflow Menu Stuff

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
