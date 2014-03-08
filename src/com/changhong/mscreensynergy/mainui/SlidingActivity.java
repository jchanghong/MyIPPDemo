package com.changhong.mscreensynergy.mainui;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import com.changhong.mscreensynergy.R;
import com.changhong.mscreensynergy.changdevice.SelectDeviceActivity;
import com.changhong.mscreensynergy.mainui.fragment.LeftFragment;
import com.changhong.mscreensynergy.mainui.fragment.RightFragment;
import com.changhong.mscreensynergy.mainui.fragment.ViewPageFragment;
import com.changhong.mscreensynergy.mainui.fragment.ViewPageFragment.MyPageChangeListener;
import com.changhong.mscreensynergy.mainui.view.SlidingMenu;
import com.changhong.mscreensynergy.service.AppBroadcastReceiver;
import com.changhong.mscreensynergy.service.DeviceDiscoverService;

public class SlidingActivity extends FragmentActivity {
	
	public static final int MY_ZOOM_EVENT = 0x111;
	public static Handler myUiHandler = null;
	
	SlidingMenu mSlidingMenu;
	LeftFragment leftFragment;
	RightFragment rightFragment;
	ViewPageFragment viewPageFragment;
	public AppBroadcastReceiver aBroadcastReceiver;

	public static SlidingActivity scontext;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main);
		scontext = this;
		init();
		initListener();
		myUiHandler = mainActHandler;
		
		//启动服务
		Intent startIntent = new Intent(this, DeviceDiscoverService.class);
		this.startService(startIntent);
	}

	@Override
  	public void onResume(){
  		
  		
  		System.out.println("--------onResume----receiver---");
  		
		aBroadcastReceiver = new AppBroadcastReceiver();
		IntentFilter filter = new IntentFilter();    
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);  
		getApplicationContext().registerReceiver(aBroadcastReceiver, filter); 
		
		System.out.println("--------onResume----receiver end--");
		
		super.onResume();
  	}
	
	private void init() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
		mSlidingMenu.setLeftView(getLayoutInflater().inflate(
				R.layout.left_frame, null));
		mSlidingMenu.setRightView(getLayoutInflater().inflate(
				R.layout.right_frame, null));
		mSlidingMenu.setCenterView(getLayoutInflater().inflate(
				R.layout.center_frame, null));
		
		FragmentTransaction t = this.getSupportFragmentManager()
				.beginTransaction();
		leftFragment = new LeftFragment();
		t.replace(R.id.left_frame, leftFragment);

		rightFragment = new RightFragment();
		t.replace(R.id.right_frame, rightFragment);

		viewPageFragment = new ViewPageFragment();
		t.replace(R.id.center_frame, viewPageFragment);
		t.commit();
	}

	private void initListener() {
		
		viewPageFragment.setMyPageChangeListener(new MyPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				if(viewPageFragment.isFirst()){
					mSlidingMenu.setCanSliding(true, false);
				}else if(viewPageFragment.isEnd()){
					mSlidingMenu.setCanSliding(true, false);
				}else{
					mSlidingMenu.setCanSliding(true, false);
				}
			}
		});
	}

	public void showLeft() {
		mSlidingMenu.showLeftView();
	}
	
	Handler mainActHandler = new Handler() {

		public void handleMessage(Message msg) {

			switch (msg.what) {
			case SlidingActivity.MY_ZOOM_EVENT:
				
				System.out.println("xxmainActHandlerxxx");
				GenerateBitmapByCurrentView();
				startSelectDeviceActivity();
//				changeMainFrame();
				break;

			}

			super.handleMessage(msg);
		}

	};
    
	/**
	 * 功能：把当前activity转成Bitmap
	 */
	private void GenerateBitmapByCurrentView() {

//		LinearLayout myimageviewlinearLayout = (LinearLayout) this
//				.findViewById(R.id.slidingmenu_main_layout);
//				
//		View view = myimageviewlinearLayout.getRootView();
		
		View view = viewPageFragment.getView();
		
		Bitmap currentBitmap = getViewBitmap(view);
		if (currentBitmap != null) {
			SelectDeviceActivity.myBitmap = currentBitmap;
			System.out.println("截图成功");
		} else {
			System.out.println("截图失败！！");
		}
	}

	/**
	 * 功能：把Activity 转成Imageview
	 */
	private Bitmap getViewBitmap(View view) {

		// 必须调用measure和layout方法才能成功保存可视组件的截图到png图像文件
		// // 测量View大小
		// view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
		// MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		// // 发送位置和尺寸到View及其所有的子View

		view.setDrawingCacheEnabled(true);
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		Bitmap bitmap = null;

		try {
			if (null != view.getDrawingCache()) {
				bitmap = Bitmap.createScaledBitmap(view.getDrawingCache(),
						view.getWidth(), view.getHeight(), false);
			}
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		} finally {
			view.setDrawingCacheEnabled(false);
			view.destroyDrawingCache();
		}

		return bitmap;
	}
	
	/**
	 * 功能：启动选择设备的activity
	 */
	private void startSelectDeviceActivity() {

		Intent myIntent = new Intent();
		myIntent.setClass(SlidingActivity.this, SelectDeviceActivity.class);
		this.startActivity(myIntent);
		//overridePendingTransition(R.anim.popup_enter, R.anim.out_to_left);
	}

	//销毁工程时，保存图片点击次数
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		getApplicationContext().unregisterReceiver(aBroadcastReceiver);
	}
	
	/**
	 * 菜单、返回键响应, 同时监听音量键
	 */
	@Override
	public boolean onKeyDown (int keyCode, KeyEvent event) {
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			finish();
		}
		return true;
	}
}
