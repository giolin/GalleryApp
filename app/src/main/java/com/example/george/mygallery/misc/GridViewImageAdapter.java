package com.example.george.mygallery.misc;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.george.mygallery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import timber.log.Timber;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

/**
 * The adapter for GridView in the GalleryActivity.
 *
 * @author George Lin
 */
public class GridViewImageAdapter extends BaseAdapter {

    private final List<String> mUrls;
    private Context mContext;

    public GridViewImageAdapter(Context context, List<String> urls) {
        mContext = context;
        mUrls = urls;
    }

    @Override
    public int getCount() {
        if (this.mUrls.size() <= 21) {
            return this.mUrls.size();
        } else {
            return 21;
        }
    }

    @Override
    public Object getItem(int position) {
        if (position >= 0 && position < mUrls.size()) {
            return this.mUrls.get(position);
        } else {
            return "";
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SquaredImageView view = (SquaredImageView) convertView;
        if (view == null) {
            view = new SquaredImageView(mContext);
            view.setScaleType(CENTER_CROP);
        }

        // Get the image URL for the current position.
        String url = mUrls.get(position);
        Timber.d("getView" + url);
        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(mContext)
            .load(R.drawable.pug)
//            .placeholder(R.drawable.placeholder)
//            .error(R.drawable.error)
            .centerCrop()
            .fit()
            .into(view);

        return view;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return false;
    }

    @Override
    public boolean isEnabled(int position)
    {
        return false;
    }

}
