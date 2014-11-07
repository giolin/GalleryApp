package com.example.george.mygallery.misc;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 2014/11/5.
 */
public class Album implements Parcelable {
    private String mTitle;
    private int mPhotoNum;
    private List<String> mImageUrls;

    public Album(String title, int photoNum, List<String> imageUrls) {
        mTitle = title;
        mPhotoNum = photoNum;
        mImageUrls = imageUrls;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getPhotoNum() {
        return mPhotoNum;
    }

    public List<String> getImageUrls() {
        return mImageUrls;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setPhotoNum(int photoNum) {
       this.mPhotoNum = photoNum;
    }

    public void setImageUrls(List<String> imageUrls) {
        mImageUrls = imageUrls;
    }

    // The following is the implementation of parcelable object
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeInt(mPhotoNum);
        dest.writeList(mImageUrls);
    }

    public static final Parcelable.Creator<Album> CREATOR
        = new Parcelable.Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public Album(Parcel in) {
        mTitle = in.readString();
        mPhotoNum = in.readInt();
        mImageUrls = new ArrayList<String>();
        in.readList(mImageUrls, null);
    }

}
