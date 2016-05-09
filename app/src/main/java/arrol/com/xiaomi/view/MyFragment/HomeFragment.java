package arrol.com.xiaomi.view.MyFragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import arrol.com.xiaomi.R;
import arrol.com.xiaomi.view.activity.AddRecordActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/4/7.
 *
 */
public class HomeFragment extends Fragment{

    @Bind(R.id.tabLayout_fragment_home)
    TabLayout tabLayout;
    @Bind(R.id.viewPager_fragment_main)
    ViewPager viewPager;
    @Bind(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    private List<View> listPager;
    private List<String> listTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvent();
    }

    private void initView(){
        listTitle=new ArrayList<>();
        listPager=new ArrayList<>();

        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View view1 = mInflater.inflate(R.layout.item_viewpager, null);
        View view2= mInflater.inflate(R.layout.fragment_setting, null);
        listPager.add(view1);
        listPager.add(view2);
        listTitle.add("hello");
        listTitle.add("world");

        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(0)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(1)));

        MyPagerAdapter mAdapter = new MyPagerAdapter(listPager);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(mAdapter);
    }

    private void initEvent(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), AddRecordActivity.class);
                startActivity(intent);
            }
        });
    }

    private class MyPagerAdapter extends PagerAdapter {
        private List<View> mViewList;

        public MyPagerAdapter(List<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();//页卡数
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;//官方推荐写法
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));//添加页卡
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));//删除页卡
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return listTitle.get(position);//页卡标题
        }

    }


}
