package com.example.george.mygallery.misc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.george.mygallery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import timber.log.Timber;

/**
 * Created by george on 2014/11/5.
 */
public class AlbumAdapter extends ArrayAdapter<Album> {

    private Context mContext;
    private int mLayoutResourceId;
    List<Album> mAlbums = null;

    public AlbumAdapter(Context context, int resource, List<Album> objects) {
        super(context, resource, objects);
        mContext = context;
        mLayoutResourceId = resource;
        mAlbums = objects;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        Timber.d("getView");
        AlbumLayoutHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);

            holder = new AlbumLayoutHolder();
            holder.ivAlbumCover = (ImageView) row.findViewById(R.id.iv_album_cover);
            holder.tvAlbumInfo = (TextView) row.findViewById(R.id.tv_album_info);
            holder.btnEdit = (Button) row.findViewById(R.id.btn_edit_album);

            row.setTag(holder);
        } else {
            holder = (AlbumLayoutHolder) row.getTag();
        }

        final Album album = mAlbums.get(position);
        String albumInfo = String.format("%S\n(%d)", album.getTitle(), album.getPhotoNum());
        holder.tvAlbumInfo.setText(albumInfo);
        Picasso.with(mContext).load(R.drawable.pug).centerCrop().fit().into(holder.ivAlbumCover);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, album.getTitle() + "Edit clicked!",
                    Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }

    private static class AlbumLayoutHolder {

        TextView tvAlbumInfo;
        ImageView ivAlbumCover;
        Button btnEdit;
    }
}
