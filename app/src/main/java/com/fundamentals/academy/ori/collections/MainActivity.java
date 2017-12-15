package com.fundamentals.academy.ori.collections;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ScreenSlidePagerAdapter screenSlidePagerAdapter;
    private ViewPager viewPager;
    private UnsplashServiceGenerator.UnsplashService unsplashService;
    private LinearLayout baseLayout;
    private Integer currentColor;
    private Integer mCurrentSelectedScreen;
    private static final List<Integer> fragmentsColors =  new ArrayList<>(Arrays.asList(
            R.color.firstFragmentColor,
            R.color.secondFragmentColor,
            R.color.thirdFragmentColor,
            R.color.fourthFragmentColor,
            R.color.fifthFragmentColor,
            R.color.sixthFragmentColor));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseLayout = findViewById(R.id.base_layout);
        viewPager = findViewById(R.id.pager);
        currentColor = fragmentsColors.get(0);
        mCurrentSelectedScreen = 0;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public int mixTwoColors( int color1, int color2, float amount )
            {
                final byte ALPHA_CHANNEL = 24;
                final byte RED_CHANNEL   = 16;
                final byte GREEN_CHANNEL =  8;
                final byte BLUE_CHANNEL  =  0;

                final float inverseAmount = 1.0f - amount;

                int a = ((int)(((float)(color1 >> ALPHA_CHANNEL & 0xff )*amount) +
                        ((float)(color2 >> ALPHA_CHANNEL & 0xff )*inverseAmount))) & 0xff;
                int r = ((int)(((float)(color1 >> RED_CHANNEL & 0xff )*amount) +
                        ((float)(color2 >> RED_CHANNEL & 0xff )*inverseAmount))) & 0xff;
                int g = ((int)(((float)(color1 >> GREEN_CHANNEL & 0xff )*amount) +
                        ((float)(color2 >> GREEN_CHANNEL & 0xff )*inverseAmount))) & 0xff;
                int b = ((int)(((float)(color1 & 0xff )*amount) +
                        ((float)(color2 & 0xff )*inverseAmount))) & 0xff;

                return a << ALPHA_CHANNEL | r << RED_CHANNEL | g << GREEN_CHANNEL | b << BLUE_CHANNEL;
            }

            private void setMiddleBackgroundColor(Integer fromColor, Integer toColor, float percent){
                Integer color1 = ResourcesCompat.getColor(getResources(), fromColor, null);
                Integer color2 = ResourcesCompat.getColor(getResources(), toColor, null);
                baseLayout.setBackgroundColor(mixTwoColors(color1, color2, percent));
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0){
                    baseLayout.setBackgroundResource(fragmentsColors.get(mCurrentSelectedScreen));
                }
                else {
                    if (position == mCurrentSelectedScreen) {
                        // We are moving to next screen on right side
                        if (mCurrentSelectedScreen + 1 < fragmentsColors.size()) {
                            setMiddleBackgroundColor(
                                    fragmentsColors.get(mCurrentSelectedScreen),
                                    fragmentsColors.get(mCurrentSelectedScreen + 1),
                                    1.0f - positionOffset);
                        }
                    } else {
                        // We are moving to next screen left side
                        if (mCurrentSelectedScreen - 1 >= 0) {
                            setMiddleBackgroundColor(
                                    fragmentsColors.get(mCurrentSelectedScreen),
                                    fragmentsColors.get(mCurrentSelectedScreen - 1),
                                    positionOffset);
                        }
                    }
                }
            }

            @Override
            public void onPageSelected(int arg0) {
                mCurrentSelectedScreen = arg0;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        screenSlidePagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(screenSlidePagerAdapter);

        unsplashService = UnsplashServiceGenerator.generateService();
        Call<List<CollectionsResult>> collectionsCall = unsplashService.getCollectionsCurated(1,10);

        collectionsCall.enqueue(new Callback<List<CollectionsResult>>() {
            @Override
            public void onResponse(Call<List<CollectionsResult>> call, Response<List<CollectionsResult>> response) {
                List<CollectionsResult> data = response.body();
                Log.w("MainActivity", String.valueOf(data.size()));
            }

            @Override
            public void onFailure(Call<List<CollectionsResult>> call, Throwable t) {
                Log.w("MainActivity", "Getting collection from internet failed.");
            }
        });
    }
}
