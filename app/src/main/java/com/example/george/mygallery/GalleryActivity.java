package com.example.george.mygallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.george.mygallery.misc.Album;
import com.example.george.mygallery.misc.GridViewImageAdapter;
import com.example.george.mygallery.ui.HeaderGridView;
import com.example.george.mygallery.util.AppConstant;
import com.squareup.picasso.Picasso;

import timber.log.Timber;

/**
 * Created by george on 2014/11/7.
 */
public class GalleryActivity extends ActionBarActivity {

    private Album mAlbum;
    private GridViewImageAdapter mImageAdapter;
    private HeaderGridView mHeaderGridView;
    private ImageView mIvHeaderImage;
    private TextView mTvHeaderTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAlbum = getIntent().getExtras().getParcelable(AppConstant.KEY_ALBUM);

        setTitle(mAlbum.getTitle() + "\n(" + mAlbum.getPhotoNum() + ")");
        initializeHeaderGridView();

        mImageAdapter = new GridViewImageAdapter(this, mAlbum.getImageUrls());

        mHeaderGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Timber.d("On Item " + position + " clicked!");
                startFullscreenImageView(position - mHeaderGridView.getNumColumns());
            }
        });

        mHeaderGridView.setAdapter(mImageAdapter);
    }

    private void initializeHeaderGridView() {
        mHeaderGridView = (HeaderGridView) findViewById(R.id.header_grid_view);
        View vHeader = getLayoutInflater().inflate(R.layout.gallery_header, null);

        mTvHeaderTitle = (TextView) vHeader.findViewById(R.id.tv_gallery_header_title);
        mIvHeaderImage = (ImageView) vHeader.findViewById(R.id.iv_gallery_header_image);
        mTvHeaderTitle.setText(mAlbum.getTitle());
        Picasso.with(this)
            .load(R.drawable.pug)
//            .placeholder(R.drawable.placeholder)
//            .error(R.drawable.error)
            .centerCrop()
            .fit()
            .into(mIvHeaderImage);

        mHeaderGridView.addHeaderView(vHeader);
    }

    private void startFullscreenImageView(int position) {
        Intent intent = new Intent(this, FullscreenActivity.class);
        Bundle extras = new Bundle();
        extras.putInt(AppConstant.KEY_POSITION, position);
        extras.putParcelable(AppConstant.KEY_ALBUM, mAlbum);
        intent.putExtras(extras);
        this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
