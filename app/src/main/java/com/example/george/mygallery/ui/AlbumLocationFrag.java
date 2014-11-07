package com.example.george.mygallery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.george.mygallery.GalleryActivity;
import com.example.george.mygallery.R;
import com.example.george.mygallery.misc.Album;
import com.example.george.mygallery.misc.AlbumAdapter;
import com.example.george.mygallery.misc.AlbumLocationAdapter;
import com.example.george.mygallery.util.AppConstant;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by george on 2014/11/6.
 */
public class AlbumLocationFrag extends Fragment {

    AlbumLocationAdapter mAlbumLocationAdapter;

    ArrayList<Album> mAlbums;

    ListView mLvAlbum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.album_location, container, false);
        mLvAlbum = (ListView) v.findViewById(R.id.lv_album_location);
        // TODO - use getAlbums method in presenter and assign the result to mAlbums
        List<String> urls = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            urls.add("url" + i);
        }
        mAlbums = new ArrayList<Album>();
        for(int i = 0; i < 5 ; i ++) {
            mAlbums.add(new Album("Stockholm - " + i, urls.size(), urls));
        }

        mAlbumLocationAdapter = new AlbumLocationAdapter(inflater.getContext(),
            R.layout.album_location_list_item,
            mAlbums);
        mLvAlbum.setAdapter(mAlbumLocationAdapter);
        mLvAlbum.setOnItemClickListener(mOnItemClickListener);
        return v;
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(), "Stockholm#" + position + " clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), GalleryActivity.class);
            intent.putExtra(AppConstant.KEY_ALBUM, mAlbums.get(position));
            getActivity().startActivity(intent);
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_album_location, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
