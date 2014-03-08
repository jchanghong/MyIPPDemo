package com.changhong.mscreensynergy.service;

import com.changhong.ippmodel.IppDevice;
import com.changhong.mscreensynergy.R;
import com.changhong.mscreensynergy.common.NotifyMessage;
import com.changhong.mscreensynergy.core.IppCallback;
import com.changhong.mscreensynergy.core.IppConnection;
import com.changhong.mscreensynergy.core.IppSystem;
import com.changhong.mscreensynergy.mainui.SlidingActivity;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class DeviceDiscoverService extends Service{
	
	public static IppSystem mIppSystem = null;
	public static IppConnection mIppConnection = null;
	private static final String LOG_TAG = "DeviceDiscoverService";
	public static Handler newDeviceHandler = null;
	final public static int INNER_NEW_DEVICE = 0;
	
	//声明通知（消息）管理器
	public static Intent newdevice_Intent;
	public static PendingIntent	 newdevice_PendingIntent;
	static IppDevice newdevice;
	
	@Override
	public void onCreate() {
		
		mIppConnection = new IppConnection(new IppCallback(), DeviceDiscoverService.this);
		mIppSystem = new IppSystem(mIppConnection, DeviceDiscoverService.this);
		
		System.out.println("mIppSystem:" + mIppSystem);
		
		//绑定服务
		bindIPPService();
		
		creatHandler();
	}
	
	private void creatHandler() {
		
		newDeviceHandler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case INNER_NEW_DEVICE:
					Toast.makeText(DeviceDiscoverService.this, "发现电视,num:", Toast.LENGTH_LONG).show();
//					newdevice = (IppDevice)msg.obj;
					notifyFindDevice();
					break;
				default:
					break;
				}
			}
		};
	}
	
	private void notifyFindDevice() {
		
//		//点击通知时转移内容
//		if(IppCallback.deviceNum > 1 ){
			newdevice_Intent = new Intent(this, SlidingActivity.class);
//		}else if(IppCallback.deviceNum ==1 ){
//			if(newdevice!=null && newdevice instanceof IppTelevision ){
//				MyTvControlActivity.TvControler = (IppTelevision) newdevice;
//				newdevice_Intent = new Intent(mcontext, MainActivity.class);
//			}else{
//				Toast.makeText(this, "不是电视设备", Toast.LENGTH_LONG).show();
//			}
//			
//		}			
		//主要是设置点击通知时显示内容的类
		try{
			newdevice_PendingIntent = PendingIntent.getActivity(this, 10, newdevice_Intent, 0);
		}catch (Exception e){
			System.out.println("====[gewancheng]发现设备错误："+e);
		}
		
		NotifyMessage.notifyDeciceDisMessage(DeviceDiscoverService.this, newdevice_PendingIntent, R.drawable.ic_launcher);
	}
	
	/**
	 * 解除绑定IPPCore服务
	 */
	public static void bindIPPService() {
		
		if (mIppSystem != null){
			System.out.println("【gewancheng】绑定服务");
			mIppSystem.bindToService();
		}else {
			Log.e(LOG_TAG, "mippSystem error");
		}		
	}
	
	/**
	 * 解除绑定IPPCore服务
	 */
	public static void unBindIPPService() {
		
		if (mIppSystem.getService() != null)
			mIppSystem.unBindFromService();
		else {
			Log.e(LOG_TAG, "mbinder error");
		}		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}