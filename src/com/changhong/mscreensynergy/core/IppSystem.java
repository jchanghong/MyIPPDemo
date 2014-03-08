package com.changhong.mscreensynergy.core;

import com.changhong.ippservice.IppCoreService.IppCoreBinder;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * IppSystem.java
 * @author NinaChen
 * 2013-3-5 上午10:02:45
 */
public class IppSystem {
	
	private static final String LOG_TAG = "IppSystem";
	private static String ACTION = "com.changhong.ippservice.service";
	private IppConnection mConnection = null;
	private Context mContext = null;

	public IppSystem(IppConnection connection, Context context) {
		
		this.mConnection = connection;
		this.mContext = context;
	}

	public void bindToService() {
		
		if (mConnection != null && mContext != null) {
			Intent intent = new Intent(ACTION);
			try {
				boolean res = mContext.bindService(intent, mConnection,
						Context.BIND_AUTO_CREATE);
				
				Toast.makeText(mContext, "服务已启动！ "+ res, Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				e.printStackTrace();
//				Toast.makeText(mContext, mContext.getResources().getString(R.string.service_bond), Toast.LENGTH_LONG).show();
			}
			
			Log.d(LOG_TAG, "bindToService绑定结果：" + mConnection.mBinder);
		}
	}

	/**
	 * 解除绑定
	 */
	public void unBindFromService() {
		
		Log.d(LOG_TAG, "unBindFromService" );
		if (mConnection != null && mContext != null)
			mContext.unbindService(mConnection);
	}

	/**
	 * 获得服务
	 * @return
	 */
	public IppCoreBinder getService() {
		if(mConnection != null &&mConnection.getService() != null)
			return mConnection.getService();
		return null;
	}
	
	/**
	 * 设置服务器地址
	 * @param ip
	 * @return
	 */
	public boolean setIppServiceIP(String ip){
		return mConnection.setIppConnectionServiceIP(ip);
	} 
	
	/**
	 * 设置callback
	 * @param callback
	 * @return
	 */
	public void setIppCallback(IppCallback callback){
		mConnection.setCallback(callback);
	} 
	
}
