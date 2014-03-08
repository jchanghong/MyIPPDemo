package com.changhong.mscreensynergy.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DeviceDiscoverReceicer extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
			
			System.out.println("[gewancheng]BOOT_COMPLETED !!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("============onReceive");
			System.out.println("============================onReceive");
			
			Toast.makeText(context, "服务已启动！", Toast.LENGTH_LONG).show();
			
			Intent startIntent = new Intent(context, DeviceDiscoverService.class);
			//startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startService(startIntent);
		}
	
	}
}