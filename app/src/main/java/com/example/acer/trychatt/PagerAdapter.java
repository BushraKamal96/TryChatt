package com.example.acer.trychatt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.acer.trychatt.Fragments.ChatFragment;
import com.example.acer.trychatt.Fragments.CallFragment;
import com.example.acer.trychatt.Fragments.StoryFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    myInterface myInterface;
    public PagerAdapter(FragmentManager fm, Home myInterface) {
        super(fm);
        this.myInterface= myInterface;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                ChatFragment chatFragment = new ChatFragment();
                chatFragment.setMyinterfaced(this.myInterface);
                return chatFragment;

            case 1:
                CallFragment callFragment = new CallFragment();
                callFragment.setMyinterfaced(this.myInterface);

                return callFragment;
            case 2:
                StoryFragment storyFragment = new StoryFragment();
                storyFragment.setMyinterfaced(this.myInterface);

                return storyFragment;

        }


        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {

            case 0:
                return "Chat";
            case 1:
                return "History";
            case 2:
                return "Story";
            default:
                return null;

        }


    }
}
