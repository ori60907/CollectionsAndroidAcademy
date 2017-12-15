package com.fundamentals.academy.ori.collections;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by User on 15/12/2017.
 */

public class MockFragment extends android.support.v4.app.Fragment {

    static public String messageKey = "MOCK_MESSAGE_KEY";
    static public String colorKey = "MOCK_COLOR_KEY";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View mock_fragment_view = inflater.inflate(R.layout.mock_fragment, container, false);

        Bundle arguments = getArguments();
        TextView textView = mock_fragment_view.findViewById(R.id.main_mock_text);
        textView.setText(arguments.getString(messageKey));
        RelativeLayout relativeLayout = mock_fragment_view.findViewById(R.id.mock_body);
        relativeLayout.setBackgroundResource(arguments.getInt(colorKey));
        return mock_fragment_view;
    }
}
