package com.example.george.mygallery;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.example.george.mygallery.misc.MyPageAdapter;
import com.example.george.mygallery.ui.AlbumFrag;
import com.example.george.mygallery.ui.AlbumLocationFrag;
import com.example.george.mygallery.ui.AlbumTimeFrag;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


public class MainActivity extends ActionBarActivity {

    ViewPager mViewPager;
    PagerTabStrip mPagerTabStrip;
    MyPageAdapter mPageAdapter;
    List<Fragment> fragments;

    private final int ALBUM_TIME_PAGE = 0;
    private final int ALBUM_PAGE = 1;
    private final int ALBUM_LOCATION_PAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = getFragments();

        mPageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mViewPager.setAdapter(mPageAdapter);
        mViewPager.setCurrentItem(ALBUM_PAGE);
        mViewPager.setOnPageChangeListener(mOnPageChangeListener);

        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tap_strip);
        // TODO - further actions for mPagerTabStrip
    }

    private List<Fragment> getFragments() {

        List<Fragment> fList = new ArrayList<Fragment>();
        fList.add(new AlbumTimeFrag());
        fList.add(new AlbumFrag());
        fList.add(new AlbumLocationFrag());
        return fList;

    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i2) {
            // Do nothing intended
        }

        @Override
        public void onPageSelected(int i) {
            // Update the action bar here
//            updateMenu(i);
            Timber.d("page selected! " + i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            // Do nothing intended
        }
    };

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
