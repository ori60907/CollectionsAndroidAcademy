package com.fundamentals.academy.ori.collections;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by User on 23/12/2017.
 */

public class ImagesPreviewAdapter extends RecyclerView.Adapter<ImagesPreviewAdapter.ImagePreviewViewHolder> {

    private List<PreviewPhoto> previewPhotoList;
    private Context context;

    public ImagesPreviewAdapter(Context context){
        previewPhotoList = null;
        context = context;
    }

    public void updateImages(List<PreviewPhoto> previewPhotos){
        previewPhotoList = previewPhotos;
    }

    @Override
    public ImagePreviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.collection_images_item, parent, false);

        return new ImagePreviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImagePreviewViewHolder holder, int position) {
        Picasso.with(context)
                .load(previewPhotoList.get(position).getUrls().getSmall())
                .fit()
                .centerCrop()
                .into(holder.previewImageIV);
    }

    @Override
    public int getItemCount() {
        return previewPhotoList.size();
    }

    public class ImagePreviewViewHolder extends RecyclerView.ViewHolder{
        private ImageView previewImageIV;
        public ImagePreviewViewHolder(View itemView) {
            super(itemView);
            previewImageIV = itemView.findViewById(R.id.preview_image_iv);
        }
    }
}
