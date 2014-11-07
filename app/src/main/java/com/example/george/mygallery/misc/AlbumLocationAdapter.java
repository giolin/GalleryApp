package com.example.george.mygallery.misc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.george.mygallery.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by george on 2014/11/5.
 */
public class AlbumLocationAdapter extends ArrayAdapter<Album> {

    private Context mContext;
    private int mLayoutResourceId;
    List<Album> mAlbums = null;

    public AlbumLocationAdapter(Context context, int resource, List<Album> objects) {
        super(context, resource, objects);
        mContext = context;
        mLayoutResourceId = resource;
        mAlbums = objects;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        AlbumLayoutHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);

            holder = new AlbumLayoutHolder();
            holder.ivAlbumCover = (ImageView) row.findViewById(R.id.iv_album_location_cover);
            holder.tvAlbumInfo = (TextView) row.findViewById(R.id.tv_album_location_info);

            row.setTag(holder);
        } else {
            holder = (AlbumLayoutHolder) row.getTag();
        }
        final Album album = mAlbums.get(position);
        String albumInfo = String.format("%S\n(%d)", album.getTitle(), album.getPhotoNum());
        holder.tvAlbumInfo.setText(albumInfo);
        // TODO - get urls from album
        Picasso.with(mContext).load(R.drawable.stockholm).centerCrop().fit().into(holder.ivAlbumCover);
        return row;
    }

    private static class AlbumLayoutHolder {

        TextView tvAlbumInfo;
        ImageView ivAlbumCover;

    }
}
