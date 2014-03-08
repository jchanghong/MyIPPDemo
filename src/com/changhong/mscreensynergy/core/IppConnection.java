package com.changhong.mscreensynergy.core;

import com.changhong.ippservice.IppCoreService.IppCoreBinder;
import com.changhong.mscreensynergy.common.Config;
import com.changhong.mscreensynergy.service.AppBroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * IppConnection.java
 * 
 * @author NinaChen 2013-3-5 上午10:02:57
 */
public class IppConnection implements ServiceConnection {

	private static final String LOG_TAG = "IppConnection";

	private void LOGI(String info) {
		Log.v("LOG_TAG", info);
	}

	public boolean connOK = false;

	public static IppCoreBinder mBinder;
	public IppCallback mCallback;
	
	private Context context;
	
	public IppConnection() {
		mBinder = null;
		mCallback = null;
	}

	public IppConnection(IppCallback callback, Context context) {
		this.mCallback = callback;
		this.context = context;
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {

		System.out.println("------------onServiceConnected------------------");
		mBinder = ((IppCoreBinder) service).getService();

		if (mBinder == null) {
			Log.d(LOG_TAG, "binder null");
			return;
		} else {
			Log.d(LOG_TAG, "binder not null");
			connOK = true;
		}

		if (mCallback != null) {
			mBinder.registerEventHandler(mCallback);
		}
		
		if(mBinder != null){
			mBinder.setWLANIP(AppBroadcastReceiver.broadcastAddress);
			//NetSpeedFloatWindowManager.createnetSpeedWindow(context);
		}

		// 设置服务器地址
		mBinder.setPlatformIP(Config.plantformIP + ":8080", Config.plantformIP
				+ ":10000", Config.LANGUAGE);
	}

	/**
	 * 设置服务器地址
	 * 
	 * @param serviceIP
	 * @return
	 */
	public boolean setIppConnectionServiceIP(String serviceIP) {
		return mBinder.setPlatformIP(Config.plantformIP + ":8080",
				Config.plantformIP + ":10000", Config.LANGUAGE);
	}

	/**
	 * 设置callback
	 * 
	 * @param callback
	 */
	public void setCallback(IppCallback callback) {
		this.mCallback = callback;
	}

	/**
	 * 获取服务
	 * 
	 * @return
	 */
	public IppCoreBinder getService() {
		if (mBinder == null) {
			Log.d(LOG_TAG, "2 binder null");
		}
		return mBinder;
	}

	public void registerEventHandler(IppCallback callback) {
		mCallback = null;
		if (mBinder != null)
			mBinder.registerEventHandler(callback);
	}

	@Override
	public void onServiceDisconnected(ComponentName arg0) {
		// TODO Auto-generated method stub

	}

}
