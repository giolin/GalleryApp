package com.example.george.mygallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.george.mygallery.misc.Album;
import com.example.george.mygallery.ui.HackyViewPager;
import com.example.george.mygallery.util.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.List;

import timber.log.Timber;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * The activity contains a pager that show the full screen image that lets user to swipe view the
 * pictures.
 *
 * @author George Lin
 */
public class FullscreenActivity extends ActionBarActivity {

    Album mAlbum;
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Hide the notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        mAlbum = getIntent().getExtras().getParcelable(AppConstant.KEY_ALBUM);

        // Hide the action bar first (will show up again when the image listens to a click action)
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mAlbum.getTitle());
        getSupportActionBar().hide();

        mViewPager = (HackyViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new PhotoViewAdapter(this, mAlbum.getImageUrls()));
        mViewPager.setCurrentItem(getIntent().getExtras().getInt(AppConstant.KEY_POSITION));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fullscreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        } else if (id == R.id.action_credits) {
//            Utils.showLicenseDialog(this);
//        } else if (id == R.id.action_share) {
//            // Share the picture here
//            // Get the view with its tag name
//            Timber.d("getting view with tag: " + "myView" + mViewPager.getCurrentItem());
//            ImageView view = (ImageView) mViewPager
//                .findViewWithTag("myView" + mViewPager.getCurrentItem());
//            Bitmap bitmap = ((BitmapDrawable) view.getDrawable()).getBitmap();
//            shareImage(bitmap);
//        }
        return super.onOptionsItemSelected(item);
    }

    private void shareImage(Bitmap bitmap) {
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("image/jpg");
//        String tempPath = MediaStore.Images.Media.insertImage(
//            getContentResolver(), bitmap, "forum-reader-tmp", null);
//        Uri bitmapUri = Uri.parse(tempPath);
//        intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
//        startActivity(Intent.createChooser(intent, getResources().getString(R.string.share_via)));
    }

    static class PhotoViewAdapter extends PagerAdapter {

        private ActionBarActivity mActivity;
        private List<String> mImageUrls;

        PhotoViewAdapter(ActionBarActivity activity, List<String> imageURLs) {
            this.mActivity = activity;
            this.mImageUrls = imageURLs;
        }

        @Override
        public int getCount() {
            return mImageUrls.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                @Override
                public void onViewTap(View view, float v, float v2) {
                    if (mActivity.getSupportActionBar().isShowing()) {
                        mActivity.getSupportActionBar().hide();
                    } else {
                        mActivity.getSupportActionBar().show();
                    }
                }
            });
            Picasso.with(mActivity)
                .load(R.drawable.pug)
//                .load(mImageUrls.get(position))
//                .placeholder(R.drawable.placeholder)
//                .error(R.drawable.error)
                .into(photoView);
            container.addView(photoView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
            // Set the tag of the view for the pager to find view with tag
            photoView.setTag("myView" + position);
            Timber.d("Set tag: " + "myView" + position);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

}
