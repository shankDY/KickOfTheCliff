package com.success.kickofthecliff.BottomActivities;


/** Base Activity which takes care of two thing
 Taking the layout element and setting in the activity.**/

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.success.kickofthecliff.R;


public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigation_battom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        navigation_battom = (BottomNavigationView) findViewById(R.id.navigation_bottom);
        navigation_battom.setOnNavigationItemSelectedListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    // Удаляем анимацию перехода между активити при нажатии на элементы navigationBottom
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }


    //обработчик нажатия на элемент bottonNavigation
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.navigation_landscape:
                startActivity(new Intent(this, LandscapeActivity.class));
                break;
            case R.id.navigation_favorites:
                startActivity(new Intent(this, FavoriteActivity.class));
                break;

        }
        finish();
        return true;
    }


    private void updateNavigationBarState(){
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        Menu menu = navigation_battom.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            boolean shouldBeChecked = item.getItemId() == itemId;
            if (shouldBeChecked) {
                item.setChecked(true);
                break;
            }
        }
    }

    abstract int getContentViewId();

    abstract int getNavigationMenuItemId();

}
