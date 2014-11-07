package com.example.george.mygallery.misc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.george.mygallery.R;
import com.example.george.mygallery.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


/**
 * Created by george on 2014/11/5.
 */
public class AlbumTimeAdapter extends ArrayAdapter<Album> {

    private Context mContext;
    private int mLayoutResourceId;
    List<Album> mAlbums = null;

    public AlbumTimeAdapter(Context context, int resource, List<Album> objects) {
        super(context, resource, objects);
        mContext = context;
        mLayoutResourceId = resource;
        mAlbums = objects;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        AlbumLayoutHolder holder;

        if (row == null) {
            Timber.d("new a row! " + position);
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);

            holder = new AlbumLayoutHolder();
            holder.ivAlbumCover = (ImageView) row.findViewById(R.id.iv_album_time_cover);
            holder.tvAlbumTitle = (TextView) row.findViewById(R.id.tv_album_time_title);
            holder.tvAlbumPhotoNum = (TextView) row.findViewById(R.id.tv_album_time_photo_number);
            holder.glThumbnails = (GridLayout) row.findViewById(R.id.gl_thumbnails);

            row.setTag(holder);
        } else {
            Timber.d("row " + position + " already exists");
            holder = (AlbumLayoutHolder) row.getTag();
        }

        final Album album = mAlbums.get(position);
        Timber.d("position" + position + " album: " + album.getTitle());
        holder.tvAlbumTitle.setText(album.getTitle());
        holder.tvAlbumPhotoNum.setText("(" + album.getPhotoNum() + ")");
        Picasso.with(mContext).load(R.drawable.newyear).centerCrop().fit().into(holder.ivAlbumCover);
        // Handle the grid View

        // Mock the urls
        List<String> urls = new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            urls.add("url" + i);
        }

        for (int i = 0; i < urls.size(); i++) {
            String strSource = "gl_thumbnail_" + i;
            // Find resource id by string
            int resourceId = mContext.getResources().getIdentifier(strSource, "id", "com.example.george.mygallery");
            // Get the image view from layout and set the params
            ImageView image = (ImageView) row.findViewById(resourceId);
            GridLayout.LayoutParams param = (GridLayout.LayoutParams) image.getLayoutParams();
            int side = Utils.getScreenWidth(mContext) / holder.glThumbnails.getColumnCount();
            param.width = side;
            param.height = side;
            image.setLayoutParams(param);
            Picasso.with(mContext).load(R.drawable.newyear).centerCrop().fit().into(image);
        }
        return row;
    }

    private static class AlbumLayoutHolder {

        TextView tvAlbumTitle;
        TextView tvAlbumPhotoNum;
        ImageView ivAlbumCover;
        GridLayout glThumbnails;

    }

}
