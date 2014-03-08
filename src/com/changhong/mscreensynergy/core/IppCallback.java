package com.changhong.mscreensynergy.core;

import java.io.Serializable;
import java.util.List;
import com.changhong.ippmodel.IIppEvent;
import com.changhong.ippmodel.IPPMsg;
import com.changhong.ippmodel.IPPVideoPlayerStatus;
import com.changhong.ippmodel.IppDMR;
import com.changhong.ippmodel.IppDevice;
import com.changhong.ippmodel.IppEventMsg;
import com.changhong.ippmodel.IppTelevision;
import com.changhong.ippmodel.VoiceMsg;
import com.changhong.mscreensynergy.common.Config;
import com.changhong.mscreensynergy.mainui.fragment.ViewPageFragment;
import com.changhong.mscreensynergy.service.DeviceDiscoverService;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;

/**
 * @author NinaChen 2013-3-5 上午10:03:07
 */
public class IppCallback extends Binder implements IIppEvent {

	private static final String TAG = IppCallback.class.getSimpleName();
	public static int deviceNum = 0;
	
	@Override
	public void onUpdateValuesList(List<IppEventMsg> events) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDeviceRemove(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeviceAdd(IppDevice newdevice) {
		// TODO Auto-generated method stub
		System.out.println("===发现电视=====");
		System.out.println("[gewancheng IPPCallbkck]电视名字是："+newdevice.mName);
		if(newdevice instanceof IppTelevision){   //是电视才发消息
			
			Bundle devBundle = new Bundle();
			devBundle.putInt("deviceType", newdevice.mType);
			devBundle.putString("deviceName", newdevice.mName);
			devBundle.putString("deviceUUID", newdevice.getUUID());
			devBundle.putInt("deviceID", newdevice.mDeviceID);
			devBundle.putString("deviceIP", newdevice.getDeviceIP());
			devBundle.putBoolean("isRenameMark", newdevice.mRenameMark);
			//devBundle.putSerializable("newDeviceObj", (Serializable) newdevice);
			Message addDevMsg = new Message();
			addDevMsg.setData(devBundle);
			addDevMsg.obj = newdevice;
			
			DeviceDiscoverService.newDeviceHandler.sendEmptyMessage(DeviceDiscoverService.INNER_NEW_DEVICE);
		}
		
	}

	@Override
	public void onDeviceEventUpdate(int eventtype, int eventvalue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onIPPDeviceSetAck(IppDevice dev, int funid, int isok) {
		
		
	}

	@Override
	public void onIPPNetworkDisconnect() {
		// TODO Auto-generated method stub
		System.out.println("===============平台断开==================");
		Intent intent = new Intent().setAction("com.changhong.ipp.platformDisconnected");
		// 广播出去
//		FirstScreen.bContext.sendBroadcast(intent);
	}

	/**
	 * 功能：接受电视状态回调
	 */
	@Override
	public void onDeviceEventReport(IppDevice dev, int eventtype, int param1,
			String param2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRecvVoiceMsg(VoiceMsg msg) {
		// TODO Auto-generated method stub

		System.out.println("收到语音消息" + msg.m_sendID);
	}

	@Override
	public void onSendVoiceMsgResponse(int recvusr, boolean success) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMsgReceived(IPPMsg msg) {
		// TODO Auto-generated method stub

		System.out.println("收到消息" + msg);
	}

	@Override
	public void onIPPDeviceChannelChange(String ownerID, String devsn,
			String channelid, String channelname) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFriendOnline(String friendID, boolean online) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeviceOnline(String ownerID, String devsn, int devtype,
			boolean online) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKickOff(String userid, int logintype, String msg) {
		// TODO Auto-generated method stub
		System.out.println("被新用户挤掉~~~~~~~~~~");
		Intent intent = new Intent().setAction("com.changhong.ipp.isKickedOff");
		// 广播出去
//		FirstScreen.bContext.sendBroadcast(intent);
	}

	@Override
	public void onMinaReturn(String json) {
		// TODO Auto-generated method stub

	}

	/**
	 * 接受到截屏的图片
	 */
	@Override
	public void onGetScreen(int devid, byte[] bitmap) {
		// TODO Auto-generated method stub
			
	}

	@Override
	public void OnVODUpdatePlayerStart(IppDevice dev, boolean isStart) {
		
	}

	@Override
	public void OnVODUpdateVideoDesctiption(IppDevice dev, String jsonDescription) {
		
	}

	@Override
	public void OnVODUpdateRecommendVideo(IppDevice dev, String jsonRecommendVideo) {
		
	}

	@Override
	public int OnVODUpdatePlayScale(IppDevice dev, int command) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int OnVODUpdateFastForwardPlay(IppDevice dev, long currentDuration) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int OnVODUpdateVideoDefinition(IppDevice dev, int command) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int OnVODUpdateVideoLanguage(IppDevice dev, String language) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int OnVODUpdateCurrentVolume(IppDevice dev, int volume) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void OnVODUpdateVideoPlayerStatus(IppDevice dev,	IPPVideoPlayerStatus videoPlayerStatus) {
		
	}

	@Override
	public void OnVODUpdateSupportLanguageList(IppDevice dev, String[] language) {
		
	}

	@Override
	public void OnVODUpdateSupportDefinition(IppDevice dev, int[] definition) {
		

	}
}
