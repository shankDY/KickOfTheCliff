package com.success.kickofthecliff.BottomActivities;

import android.os.Bundle;

import com.success.kickofthecliff.R;

public class FavoriteActivity extends BaseActivity {

    private static final int LAYOUT = R.layout.activity_favorite;

    @Override
    int getContentViewId() {
        return LAYOUT;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_favorites;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
