package april.fool.day.lefish;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    public static final int PAGE_COUNT = 4;

    @Bind(R.id.pager)
    ViewPager mViewPager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;
    GuideAdapter mAdapter;

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //TODO
            //status bar background color
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAdapter = new GuideAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        indicator.setViewPager(mViewPager);

        clearOverScrollEffect(mViewPager);
    }

    void clearOverScrollEffect(View view) {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
            view.setOverScrollMode(View.OVER_SCROLL_NEVER);
            view.setHorizontalFadingEdgeEnabled(false);
            view.setVerticalFadingEdgeEnabled(false);
        }
    }

    static class GuideAdapter extends FragmentPagerAdapter {

        public GuideAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return GuideFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        mViewPager.addOnPageChangeListener(onPageChangeListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewPager.removeOnPageChangeListener(onPageChangeListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
