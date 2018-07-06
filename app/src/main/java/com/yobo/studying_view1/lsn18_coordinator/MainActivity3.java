package com.yobo.studying_view1.lsn18_coordinator;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yobo.studying_view1.R;

public class MainActivity3 extends AppCompatActivity {

	private TabLayout tabLayout;
	private String[] title = {
		"娱乐",
		"新闻",
		"军事",
		"秘闻",
		"体育",
		"活动",
		"哈哈",
		"横",
		"hi hi是",
		"妮妮"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b_activity_main2);
		
		final ViewPager viewPager = (ViewPager) findViewById(R.id.vp);
		tabLayout = (TabLayout)findViewById(R.id.tablayout);
		MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
		//1.TabLayout��Viewpager����
		tabLayout.setOnTabSelectedListener(new OnTabSelectedListener() {
			
			@Override
			public void onTabUnselected(Tab arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(Tab tab) {
				// ��ѡ�е�ʱ��ص�
				viewPager.setCurrentItem(tab.getPosition(),true);
			}
			
			@Override
			public void onTabReselected(Tab arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//2.ViewPager��������tabLayout
		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

		//����tabLayout�ı�ǩ������PagerAdapter
		tabLayout.setTabsFromPagerAdapter(adapter);
		viewPager.setAdapter(adapter);
	}
	
	private class MyPagerAdapter extends FragmentPagerAdapter{

		MyPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return title[position];
		}

		@Override
		public Fragment getItem(int position) {
			Fragment f = new NewsDetailFragment();
			Bundle bundle = new Bundle();
			bundle.putString("title", title[position]);
			f.setArguments(bundle);
			return f;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return title.length;
		}
		
	}
	
	
	
}
