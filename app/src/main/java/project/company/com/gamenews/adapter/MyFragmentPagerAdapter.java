package project.company.com.gamenews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import project.company.com.gamenews.fragment.FavouritesFragment;
import project.company.com.gamenews.fragment.StoriesFragment;
import project.company.com.gamenews.fragment.VideoFragment;

/**
 * Created by Pahan on 07.10.2017.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTableTitles;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] mTableTitles) {
        super(fm);
        this.mTableTitles =mTableTitles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StoriesFragment();
            case 1:
                return new VideoFragment();
            case 2:
                return new FavouritesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 0;
    }
}
