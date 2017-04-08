package ua.art.myapppackage;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ActMain extends AppCompatActivity {
    private DataStorage DATA_STORAGE;
    private ActionBar actionBar;
    private FragmentManager fragmentManager;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private int currentPosition;

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        DATA_STORAGE = DataStorage.getDataStorage();
        DATA_STORAGE.setAppNameList(new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.apps_list))));
        actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        drawerListView = (ListView) findViewById(R.id.drawer_list_view);
        drawerListView.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, DATA_STORAGE.getAppNameList()));
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem(position);
            }
        });
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fragment = fragmentManager.findFragmentByTag("current_fragment");
                if (fragment instanceof FragHome) currentPosition = 0;
                else if (fragment instanceof FragCalculator) currentPosition = 1;
                else if (fragment instanceof FragConverter) currentPosition = 2;
                else if (fragment instanceof FragDifferent) currentPosition = 3;
                setActionBarTitle(currentPosition);
                drawerListView.setItemChecked(currentPosition, true);
            }
        });
        drawerLayout = (DrawerLayout) this.findViewById(R.id.act_main_root_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); //Declare that the options menu has changed, so should be recreated.
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.addDrawerListener(drawerToggle); //Drawer events. onDrawerOpened/onDrawerClosed.

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.frame_container, new FragHome())
                    .commit();
        } else {
            currentPosition = savedInstanceState.getInt("currentPosition");
            setActionBarTitle(currentPosition);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Initialize the contents of the Activity's standard options menu.
        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override/*ACTION BAR CLICK LISTENER*/
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_main_settings) {
            this.startActivity(new Intent(ActMain.this, ActSettings.class));
        } else if (item.getItemId() == R.id.menu_main_about) {
            SpannableString hyperlink = new SpannableString(this.getResources().getString(R.string.my_github));
            Linkify.addLinks(hyperlink, Linkify.WEB_URLS);
            TextView alertTextView = new TextView(this);
            alertTextView.setText(hyperlink);
            alertTextView.setMovementMethod(LinkMovementMethod.getInstance());
            alertTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ActMain.this)
                    .setTitle(R.string.alert_title)
                    .setMessage(R.string.alert_info)
                    .setView(alertTextView)
                    .setIcon(R.drawable.art)
                    .setCancelable(false)
                    .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = alertDialogBuilder.create();
            alert.show();
        } else if (drawerToggle.onOptionsItemSelected(item)) { //This hook is called whenever an drawerToggle item in your options menu is selected.
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) { //Dynamically modify the contents of the menu.
        boolean b = drawerLayout.isDrawerOpen(drawerListView);
        (menu.findItem(R.id.menu_main_item)).setVisible(!b);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState(); //Sync the toggle state after onRestoreInstanceState has occurred.
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentPosition", currentPosition);
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    /*DRAWER LIST VIEW CLICK LISTENER*/
    private void selectedItem(int position) {
        Fragment fragment;
        if (position == 0) fragment = new FragHome();
        else if (position == 1) fragment = new FragCalculator();
        else if (position == 2) fragment = new FragConverter();
        else fragment = new FragDifferent();

        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment, "current_fragment")
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

        drawerLayout.closeDrawer(drawerListView);
        currentPosition = position;
    }

    /*SET ACTION BAR TITLE*/
    private void setActionBarTitle(int currentPosition) {
        if (currentPosition != 0) actionBar.setTitle(DATA_STORAGE.getAppNameList().get(currentPosition));
        else actionBar.setTitle(getResources().getString(R.string.app_name));
    }
}
