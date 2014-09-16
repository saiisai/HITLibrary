package cn.edu.hit.lib.ui;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import cn.edu.hit.lib.R;
import cn.edu.hit.lib.model.ColorSet.ColorType;
import cn.edu.hit.lib.ui.widget.ActionBar;
import cn.edu.hit.lib.ui.widget.ActionBar.OnItemClickListener;
import cn.edu.hit.lib.ui.widget.ActionBarItem;
import cn.edu.hit.lib.ui.widget.PopupTipWindow;

public class MainActivity extends FragmentActivity {
	private ActionBar actionbar;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionbar = (ActionBar)findViewById(R.id.actionbar);
		viewPager = (ViewPager)findViewById(R.id.viewPager);
		
		//new SessionIDGetter().execute();
		
		ArrayList<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add((Fragment)(new HomeFragment()));
		fragments.add((Fragment)(new SearchFragment()));
		fragments.add((Fragment)(new UserCenterFragment()));
		viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragments));
		viewPager.setOnPageChangeListener(new MainFragmentPagerChangeListener());
		viewPager.setOffscreenPageLimit(fragments.size());

		ActionBarItem homePage = new ActionBarItem(this, null).init(R.string.homepage, R.drawable.ic_home);
		ActionBarItem recommend = new ActionBarItem(this, null).init(R.string.search, R.drawable.ic_search);
		ActionBarItem userCenter = new ActionBarItem(this, null).init(R.string.user_center, R.drawable.ic_user);
		actionbar.addItem(homePage);
		actionbar.addItem(recommend);
		actionbar.addItem(userCenter);
		actionbar.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(ActionBarItem view, int position) {
				viewPager.setCurrentItem(position);
			}
		});
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				new PopupTipWindow(MainActivity.this, ColorType.SUCCESS, getString(R.string.hello_world)).show(viewPager);
			}
		}, 1000);
	}
	
	private class MainFragmentPagerAdapter extends FragmentPagerAdapter{
		private ArrayList<Fragment> fragments;

		public MainFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}
		
	}
	
	private class MainFragmentPagerChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int position) {
			actionbar.setCurrentItem(position);
		}
		
	}

}
