package com.fundamentals.academy.ori.collections;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;

/**
 * Created by User on 23/12/2017.
 */

public class CollectionPreviewFragment extends Fragment {

    static public String collectionResultKey = "COLLECTION_RESULT_KEY";
    static public String collectionColorKey = "COLLECTION_COLOR_KEY";
    private ImagesPreviewAdapter imagesPreviewAdapter;
    private CollectionsResult collection;
    private Button visitCollectionBt;
    private ImageView closeBt;
    private RecyclerView imagesGridRv;
    private LinearLayout detailsFragmentLL;
    private CollectionPreviewFragmentListener collectionPreviewFragmentListener = null;
    private int ColorResource;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson = new Gson();
        Bundle arguments = getArguments();

        /* Get the collection result object from the json string. */
        collection = gson.fromJson(arguments.getString(collectionResultKey), CollectionsResult.class);
        imagesPreviewAdapter = new ImagesPreviewAdapter(getContext());
        imagesPreviewAdapter.updateImages(collection.getPreviewPhotos());
        ColorResource = arguments.getInt(collectionColorKey);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View collectionDetailsView = inflater.inflate(R.layout.collection_details_fragment, container, false);

        imagesGridRv = collectionDetailsView.findViewById(R.id.collection_preview_grid);
        imagesGridRv.setAdapter(imagesPreviewAdapter);
        imagesGridRv.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));

        closeBt = collectionDetailsView.findViewById(R.id.close_collection_preview_bt);

        closeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != collectionPreviewFragmentListener){
                    collectionPreviewFragmentListener.OnCloseButtonClicked();
                }
            }
        });

        detailsFragmentLL = collectionDetailsView.findViewById(R.id.details_fragment_ll);
        detailsFragmentLL.setBackgroundResource(ColorResource);

        visitCollectionBt = collectionDetailsView.findViewById(R.id.visit_collection_bt);
        visitCollectionBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(collection.getLinks().getHtml()));
                startActivity(browserIntent);
            }
        });
        return collectionDetailsView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof CollectionPreviewFragmentListener){
            collectionPreviewFragmentListener = (CollectionPreviewFragmentListener) context;
        }
    }
}
