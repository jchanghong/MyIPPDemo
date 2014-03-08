package com.changhong.mscreensynergy.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.changhong.ippmodel.IppDevice;
import com.changhong.ippservice.IppCoreService.IppCoreBinder;
import com.changhong.mscreensynergy.common.Config;
import com.changhong.mscreensynergy.service.DeviceDiscoverService;

/**
 * 该对库函数进行一次封装，目的是当库发生修改时，手机端只需改动该类即可，不用修改其他类
 * 以求达到控制代码修改范围的目的
 * @author fanfan
 *
 */

public class MyIppCoreService {
	
	private static final String TAG = "MyIppCoreService";
	
	public static Intent back_Intent = null;
	public static PendingIntent	back_PendingIntent = null;
	//声明Notification对象
	public static Notification back_Notification = null;
	//声明通知（消息）管理器
	public static NotificationManager back_NotificationManager = null;
	
	public static final String IPPPHOME_DOWNLOAD_URL = Config.IPPPHOME_DOWNLOAD_URL;//"http://ipp.changhong.com:8081/IPP/android/IPPPHONE.apk";
	
	public MyIppCoreService() {
		
	}
	
	/**
	 * 返回绑定的IPPCore服务;程序崩溃
	 */
	public static IppCoreBinder getIPPService() {
		
		if (DeviceDiscoverService.mIppSystem != null && 
				DeviceDiscoverService.mIppSystem.getService() != null)
			return DeviceDiscoverService.mIppSystem.getService();
		else {
			Log.e("DeviceDiscoverActivity", "mbinder null");
			return null;
		}

	}
	
	/**
	 * 函数：getIPPDevice
	 * 功能：获取局域网内的设备
	 * @return
	 */
	public static List<IppDevice> getIppDevices() {
		
		try{
			List<IppDevice> tmp = getIPPService().getIppDevices();
			
			if(tmp != null) {
				return tmp;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ArrayList<IppDevice>();
		}
		
		return new ArrayList<IppDevice>();
		
	}
	
	/**
	 * 函数：getIPPDevice
	 * 功能：绑定设备
	 * @return
	 */
	public static String deviceInitBinding(String userId, String phoneNumber, 
			String authDeviceType, String uuid, String bundingPassword) {
		
		try{
			return getIPPService().deviceInitBinding( userId, phoneNumber, 
				authDeviceType, uuid, bundingPassword);
		}catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 函数：deviceAutho
	 * 功能：授权
	 * @param userid
	 * @param authorUserID
	 * @param deviceType
	 * @param deviceSn
	 * @return
	 */
	public static boolean deviceAutho( String userid, String authorUserID, String deviceType, String deviceSn, int authorFlag) {
		
		try{
			//1 只有查看节目权限 2 有控制权限
			return getIPPService().deviceAutho(userid, authorUserID, deviceType, deviceSn, authorFlag);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 函数：runBackgroundNotify
	 * 功能：让程序进入后台运行
	 * @param mcontext
	 * @param mclass
	 */
	
	public  static void runBackgroundNotify(Context mcontext, Class<?> mclass){
		if(Locale.getDefault().getLanguage().contains("zh")){
			back_NotificationManager = (NotificationManager)mcontext.getSystemService(Service.NOTIFICATION_SERVICE);
			//点击通知时转移内容
			back_Intent = new Intent(mcontext, mclass);  
			
			//主要是设置点击通知时显示内容的类
			back_PendingIntent = PendingIntent.getActivity(mcontext, 1, back_Intent, 0);
			
			//构造Notification对象
			back_Notification = new Notification();
			
			//设置通知在状态栏显示的图标
//			back_Notification.icon = R.drawable.ic_launcher;
			//自动清除消息提醒（点击下拉状态栏之后）
			back_Notification.flags |= back_Notification.FLAG_NO_CLEAR;
			back_Notification.flags |= back_Notification.FLAG_AUTO_CANCEL;
			//通知时发出默认的声音
			//back_Notification.defaults = Notification.DEFAULT_SOUND;
			//设置通知显示的参数
			back_Notification.setLatestEventInfo(mcontext, "手机IPP", "应用正在运行，点击进入", back_PendingIntent);
			//可以理解为执行这个通知
			back_NotificationManager.notify(1, back_Notification);       
		}else if(Locale.getDefault().getLanguage().contains("en")){
			back_NotificationManager = (NotificationManager)mcontext.getSystemService(Service.NOTIFICATION_SERVICE);
			//点击通知时转移内容
			back_Intent = new Intent(mcontext, mclass);  
			
			//主要是设置点击通知时显示内容的类
			back_PendingIntent = PendingIntent.getActivity(mcontext, 1, back_Intent, 0);
			
			//构造Notification对象
			back_Notification = new Notification();
			
			//设置通知在状态栏显示的图标
//			back_Notification.icon = R.drawable.ic_launcher;
			//自动清除消息提醒（点击下拉状态栏之后）
			back_Notification.flags |= back_Notification.FLAG_NO_CLEAR;
			back_Notification.flags |= back_Notification.FLAG_AUTO_CANCEL;
			//通知时发出默认的声音
			//back_Notification.defaults = Notification.DEFAULT_SOUND;
			//设置通知显示的参数
			back_Notification.setLatestEventInfo(mcontext, "ChanghongIPP", "App is running, click to open!", back_PendingIntent);
			//可以理解为执行这个通知
			back_NotificationManager.notify(1, back_Notification);       
		}
	}
	
	public static void cacelRunBackNotify(){
		if(back_NotificationManager != null){
			back_NotificationManager.cancel(1);
			back_NotificationManager = null;
		}
	}
	
	public static String getTelType(){
//		TelephonyManager tm = (TelephonyManager) tx.getSystemService(Context.TELEPHONY_SERVICE);   
//		Build bd = new Build();
//		return bd.
		return android.os.Build.PRODUCT;
		
	}
	
}
