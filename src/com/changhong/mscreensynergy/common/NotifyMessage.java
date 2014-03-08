package com.changhong.mscreensynergy.common;

import java.util.Locale;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.changhong.ippmodel.IppTelevision;

public class NotifyMessage {

	//声明通知（消息）管理器
	public static NotificationManager newdevice_NotificationManager;
	//声明Notification对象
	public static Notification newdevice_Notification;
	public static Intent newdevice_Intent;

	/**
	 * 函数：notifyNewMessage
	 * 功能：通知发现电视
	 */
	public static void notifyDeciceDisMessage(Context mcontext, PendingIntent newdevice_PendingIntent, int drawable){
		
		if(Locale.getDefault().getLanguage().contains("zh")){
			newdevice_NotificationManager = (NotificationManager) mcontext.getSystemService(Context.NOTIFICATION_SERVICE);
			//构造Notification对象
			newdevice_Notification = new Notification();
			//设置通知在状态栏显示的图标
//			newdevice_Notification.icon = R.drawable.newmessage;
			//当我们点击通知时显示的内容
			newdevice_Notification.tickerText = "手机IPP消息提醒";
			//自动清除消息提醒（点击下拉状态栏之后）
			newdevice_Notification.flags |= newdevice_Notification.FLAG_NO_CLEAR;
			newdevice_Notification.flags |= newdevice_Notification.FLAG_AUTO_CANCEL;
			//通知时发出默认的声音
			newdevice_Notification.defaults = Notification.DEFAULT_SOUND;
			//设置通知显示的参数
			newdevice_Notification.setLatestEventInfo(mcontext, "手机IPP消息提醒", 
					"发现电视设备，点击进入",newdevice_PendingIntent);
			//可以理解为执行这个通知
			newdevice_NotificationManager.notify(10, newdevice_Notification);  
		}else if(Locale.getDefault().getLanguage().contains("en")){
			newdevice_NotificationManager = (NotificationManager) mcontext.getSystemService(Context.NOTIFICATION_SERVICE);
			newdevice_Notification = new Notification();
			//设置通知在状态栏显示的图标
			newdevice_Notification.icon = drawable;
			//当我们点击通知时显示的内容
			newdevice_Notification.tickerText = "ChanghongIPP Notifications";
			//自动清除消息提醒（点击下拉状态栏之后）
			newdevice_Notification.flags |= newdevice_Notification.FLAG_NO_CLEAR;
			newdevice_Notification.flags |= newdevice_Notification.FLAG_AUTO_CANCEL;
			//通知时发出默认的声音
			newdevice_Notification.defaults = Notification.DEFAULT_SOUND;
			//设置通知显示的参数
			newdevice_Notification.setLatestEventInfo(mcontext, "ChanghongIPP Notifications", 
					"Discover TVset, click to open!", newdevice_PendingIntent);
			//可以理解为执行这个通知
			newdevice_NotificationManager.notify(10, newdevice_Notification);  
		}
	
	}
	
	
	public static void cancelDeciceDisMessageNotify(){
        //清除它
		if(newdevice_NotificationManager != null){
			newdevice_NotificationManager.cancel(10);
			newdevice_NotificationManager = null;
		}
	}

}
