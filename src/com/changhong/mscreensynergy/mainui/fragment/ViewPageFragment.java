/*
 * Copyright (C) 2012 yueyueniao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.changhong.mscreensynergy.mainui.fragment;

import java.util.ArrayList;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.changhong.mscreensynergy.R;
import com.changhong.mscreensynergy.mainui.SlidingActivity;
import com.changhong.mscreensynergy.mainui.view.SlidingMenu;
import com.changhong.mscreensynergy.mainui.view.SyncHorizontalScrollView;

public class ViewPageFragment extends Fragment {

	private Button showLeft;
	private MyAdapter mAdapter;
	private ViewPager mPager;
	private ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();
	
	private RelativeLayout rl_nav;
	private SyncHorizontalScrollView mHsv;
	private RadioGroup rg_nav_content;
	private ImageView iv_nav_indicator;
	private ImageView iv_nav_left;
	private ImageView iv_nav_right;
	private LayoutInflater mInflater;
	private int indicatorWidth;
	private int currentIndicatorLeft = 0;
	public static String[] tabTitle = { "选项1", "选项2", "选项3", "选项4", "选项5" };	//标题
	
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//mInflater = inflater;
		View mView = inflater.inflate(R.layout.view_pager, null);
		showLeft = (Button) mView.findViewById(R.id.showLeft);
		mPager = (ViewPager) mView.findViewById(R.id.pager);
		PageFragment1 page1 = new PageFragment1();
		PageFragment2 page2 = new PageFragment2();
		PageFragment3 page3 = new PageFragment3();
//		PageFragment4 page4 = new PageFragment4();
//		PageFragment5 page5 = new PageFragment5();
		pagerItemList.add(page1);
		pagerItemList.add(page2);
		pagerItemList.add(page3);
//		pagerItemList.add(page4);
//		pagerItemList.add(page5);
		mAdapter = new MyAdapter(getFragmentManager());
		mPager.setAdapter(mAdapter);
		mPager.setOffscreenPageLimit(3);  //缓存3页
		mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

				if (myPageChangeListener != null)
					myPageChangeListener.onPageSelected(position);
				
				if(rg_nav_content!=null && rg_nav_content.getChildCount()>position){
					((RadioButton)rg_nav_content.getChildAt(position)).performClick();
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				

			}

			@Override
			public void onPageScrollStateChanged(int position) {

				

			}
		});

		
		/*
		 * 标题的初始化
		 */
		rl_nav = (RelativeLayout)mView.findViewById(R.id.rl_nav);
		
		mHsv = (SyncHorizontalScrollView) mView.findViewById(R.id.mHsv);
		
		rg_nav_content = (RadioGroup) mView.findViewById(R.id.rg_nav_content);
		
		
		rg_nav_content.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				if(rg_nav_content.getChildAt(checkedId)!=null){
					
					TranslateAnimation animation = new TranslateAnimation(
							currentIndicatorLeft ,
							((RadioButton) rg_nav_content.getChildAt(checkedId)).getLeft(), 0f, 0f);
					animation.setInterpolator(new LinearInterpolator());
					animation.setDuration(100);
					animation.setFillAfter(true);
					
					//执行位移动画
					iv_nav_indicator.startAnimation(animation);
					
					mPager.setCurrentItem(checkedId);	//ViewPager 跟随一起 切换
					
					//记录当前 下标的距最左侧的 距离
					currentIndicatorLeft = ((RadioButton) rg_nav_content.getChildAt(checkedId)).getLeft();
					
					mHsv.smoothScrollTo(
							(checkedId > 1 ? ((RadioButton) rg_nav_content.getChildAt(checkedId)).getLeft() : 0) - ((RadioButton) rg_nav_content.getChildAt(2)).getLeft(), 0);
				}
			}
		});
		
		iv_nav_indicator = (ImageView) mView.findViewById(R.id.iv_nav_indicator);
		iv_nav_left = (ImageView) mView.findViewById(R.id.iv_nav_left);
		iv_nav_right = (ImageView) mView.findViewById(R.id.iv_nav_right);
		
		LayoutParams cursor_Params = iv_nav_indicator.getLayoutParams();
		cursor_Params.width = indicatorWidth;// 初始化滑动下标的宽
		iv_nav_indicator.setLayoutParams(cursor_Params);
		
		mHsv.setSomeParam(rl_nav, iv_nav_left, iv_nav_right, SlidingActivity.scontext);
		
		mInflater = (LayoutInflater)SlidingActivity.scontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		indicatorWidth = SlidingMenu.screenWidth / 4;
		
		initNavigationHSV();
		
		
		return mView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		showLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((SlidingActivity) getActivity()).showLeft();
			}
		});

	}
	
	
	
	private void initNavigationHSV() {
	
	rg_nav_content.removeAllViews();
	
	for(int i=0;i<tabTitle.length;i++){
		
		RadioButton rb = (RadioButton) mInflater.inflate(R.layout.nav_radiogroup_item, null);
		rb.setId(i);
		rb.setText(tabTitle[i]);
		rb.setLayoutParams(new LayoutParams(indicatorWidth,
				LayoutParams.MATCH_PARENT));
		
		rg_nav_content.addView(rb);
	}
	
}
	

	public boolean isFirst() {
		if (mPager.getCurrentItem() == 0)
			return true;
		else
			return false;
	}

	public boolean isEnd() {
		if (mPager.getCurrentItem() == pagerItemList.size() - 1)
			return true;
		else
			return false;
	}

	public class MyAdapter extends FragmentPagerAdapter {
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return pagerItemList.size();
		}

		@Override
		public Fragment getItem(int position) {

			Fragment fragment = null;
			if (position < pagerItemList.size())
				fragment = pagerItemList.get(position);
			else
				fragment = pagerItemList.get(0);

			return fragment;

		}
	}

	private MyPageChangeListener myPageChangeListener;

	public void setMyPageChangeListener(MyPageChangeListener l) {

		myPageChangeListener = l;
		

	}

	public interface MyPageChangeListener {
		public void onPageSelected(int position);
	}

}
