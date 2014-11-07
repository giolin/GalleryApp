package com.example.george.mygallery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.george.mygallery.util.AppConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 2014/11/6.
 */
public class AlbumFrag extends Fragment {

    AlbumAdapter mAlbumAdapter;

    ArrayList<Album> mAlbums;

    ListView mLvAlbum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View v = inflater.inflate(R.layout.album, container, false);
        mLvAlbum = (ListView) v.findViewById(R.id.lv_album);
        // TODO - use getAlbums method in presenter and assign the result to mAlbums
        List<String> urls = new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            urls.add("url" + i);
        }
        mAlbums = new ArrayList<Album>();
        for (int i = 0; i < 10; i++) {
            mAlbums.add(new Album("Album" + i, urls.size(), urls));
        }

        mAlbumAdapter = new AlbumAdapter(inflater.getContext(),
            R.layout.album_list_item,
            mAlbums);

        mLvAlbum.setAdapter(mAlbumAdapter);
        mLvAlbum.setOnItemClickListener(mOnItemClickListener);
        mLvAlbum.setOnItemLongClickListener(mOnItemLongClickListener);
        return v;
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(), "Pug#" + position + ": Woof!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), GalleryActivity.class);
            intent.putExtra(AppConstant.KEY_ALBUM, mAlbums.get(position));
            getActivity().startActivity(intent);
        }
    };

    private AdapterView.OnItemLongClickListener mOnItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(), "Pug#" + position + ": Woooof!", Toast.LENGTH_SHORT).show();
            return true;
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_album, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
