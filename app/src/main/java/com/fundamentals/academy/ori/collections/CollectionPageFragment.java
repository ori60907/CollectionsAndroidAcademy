package com.fundamentals.academy.ori.collections;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

/**
 * Created by User on 23/12/2017.
 */

public class CollectionPageFragment extends Fragment {
    static public String collectionResultKey = "COLLECTION_RESULT_KEY";

    private CollectionsResult collection;
    private TextView collectionTitleTv;
    private ImageView collectionImageIv;
    private TextView collectionDescriptionTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View collection_page_view = inflater.inflate(R.layout.collection_page, container, false);
        Gson gson = new Gson();
        Bundle arguments = getArguments();

        /* Get the collection result object from the json string. */
        collection = gson.fromJson(arguments.getString(collectionResultKey), CollectionsResult.class);

        /* Fill the data in the layout. */
        collectionTitleTv = collection_page_view.findViewById(R.id.collection_title_tv);
        collectionImageIv = collection_page_view.findViewById(R.id.collection_image_iv);
        collectionDescriptionTv = collection_page_view.findViewById(R.id.collection_description_tv);

        collectionTitleTv.setText(collection.getTitle());
        collectionDescriptionTv.setText(collection.getDescription());

        Picasso.with(container.getContext()).load(collection.getCoverPhoto().getUrls().getSmall()).fit().centerCrop().into(collectionImageIv);

        return collection_page_view;
    }
}
