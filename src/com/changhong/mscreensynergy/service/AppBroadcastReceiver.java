package com.changhong.mscreensynergy.service;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Locale;

import com.changhong.ippmodel.IppUser;
import com.changhong.mscreensynergy.core.IppConnection;
import com.changhong.mscreensynergy.mainui.fragment.ViewPageFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class AppBroadcastReceiver extends BroadcastReceiver {
	
	Context mContext;
	public static String broadcastAddress = null;
	@Override
	public void onReceive(final Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		mContext = context;

		if(intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")){
			System.out.println("网络状态变化============");
			
			WifiManager mWm; 
            mWm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE); 
                if (mWm != null && mWm.isWifiEnabled()) { //wifi打开状态
                    
                } 
                else { 
    				if(IppConnection.mBinder != null){
    					
//    					ViewPageFragment.showUnSelectableStatu();
    					
//    			  		if(ViewPageFragment.ippDeviceList != null && ViewPageFragment.ippDeviceList.size() > 0){
//    			  			ViewPageFragment.ippDeviceList.clear();
//    			  		}
//    			  		if(ViewPageFragment.tv_list != null && ViewPageFragment.tv_list.size() > 0){
//    			  			ViewPageFragment.tv_list.clear();
//    			  		}
    					broadcastAddress = null;
    					IppConnection.mBinder.setWLANIP(null);
    				}
                } 
			
			ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); 
			
			if(networkInfo==null){ 
				System.out.println("网络已断开==========");					
				//进行网络断开的操作
				Toast.makeText(context, "当前网络断开", Toast.LENGTH_SHORT).show();
				
//				ViewPageFragment.showUnSelectableStatu();
				
				if(IppConnection.mBinder != null){
					
//			  		if(ViewPageFragment.ippDeviceList != null && ViewPageFragment.ippDeviceList.size() > 0){
//			  			ViewPageFragment.ippDeviceList.clear();
//			  		}
//			  		if(ViewPageFragment.tv_list != null && ViewPageFragment.tv_list.size() > 0){
//			  			ViewPageFragment.tv_list.clear();
//			  		}
					broadcastAddress = null;
					IppConnection.mBinder.setWLANIP(null);
				}

			} else{
				System.out.println("网络已连接=========");
				if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){  //移动网络
					if(networkInfo.getExtraInfo().toLowerCase().equals("cmnet")){
						Toast.makeText(context, "当前为GPRS网络", Toast.LENGTH_SHORT).show();
					}else /*if(networkInfo.getExtraInfo().toLowerCase().equals("cnwap"))*/{
						Toast.makeText(context, "当前为3G网络", Toast.LENGTH_SHORT).show();
					}
				}else if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){   //WIFI网络
					Toast.makeText(context, "当前为WIFI网络", Toast.LENGTH_SHORT).show();
					try {
						broadcastAddress = getBroadcastAddress().toString();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(IppConnection.mBinder != null){
						try {
							System.out.println("=====广播地址:"+getBroadcastAddress().toString());
							
							IppConnection.mBinder.setWLANIP(getBroadcastAddress().toString());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}		
		

	}
	
	private InetAddress getBroadcastAddress() throws IOException {
		WifiManager myWifiManager = (WifiManager) mContext.getSystemService(mContext.WIFI_SERVICE);
		DhcpInfo myDhcpInfo = myWifiManager.getDhcpInfo();
		if (myDhcpInfo == null) {
		System.out.println("Could not get broadcast address");
		return null;
		}
		int broadcast = (myDhcpInfo.ipAddress & myDhcpInfo.netmask)
		| ~myDhcpInfo.netmask;
		byte[] quads = new byte[4];
		for (int k = 0; k < 4; k++)
		quads[k] = (byte) ((broadcast >> k * 8) & 0xFF);
		return InetAddress.getByAddress(quads);
		}
	
}





