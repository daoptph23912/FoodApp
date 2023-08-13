package com.example.food_app_design.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.food_app_design.fragment.CartFragment;
import com.example.food_app_design.fragment.ContactFragment;
import com.example.food_app_design.fragment.HistoryFragment;
import com.example.food_app_design.fragment.HomeFragment;
import com.example.food_app_design.fragment.FeedBackFragment;


public class MyViewPagerAdapter extends FragmentStateAdapter {

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();

            case 1:
                return new CartFragment();
            case 2:
                return new FeedBackFragment();

            case 3:
                return new ContactFragment();
            case 4:
                return new HistoryFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
