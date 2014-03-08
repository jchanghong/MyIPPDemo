package com.changhong.mscreensynergy.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.changhong.ippmodel.IPPDTVChannelInfo;
import com.changhong.ippmodel.IPPEPGEvent;
import com.changhong.ippmodel.IPPUTCDate;
import com.changhong.ippmodel.IPPUTCTime;
import com.changhong.ippmodel.IppTelevision;
import com.changhong.ippmodel.OrderChannel;

public class LANTvControl {

	private String TAG = this.getClass().getSimpleName().toString();
	public static int mute = 1;
	public static int cancelMute = 0;
	public static final int  mainPageMode = 0;
	public static final int dtvMode = 1;
	public static final int stbMode = 2;
	public static final int updateSTBProgramme = 3;
	public static final int updateDTVProgramme = 4;
	public static final int bundingTV = 6;
	public static List<IPPDTVChannelInfo> allEPGList = new ArrayList<IPPDTVChannelInfo>();
	public static List<IPPDTVChannelInfo> commonEPGList = new ArrayList<IPPDTVChannelInfo>();
	public static List<OrderChannel> myOrderList = new ArrayList<OrderChannel>();
	public static int commonEPGType = 1;
	public static int allEPGType = 0;
	public static Integer allEPGNumber = new Integer(0);
	public static Integer commonEPGNumber = new Integer(0);
	
	public static IppTelevision TvControler = null;
	
	//声音+
	public static boolean addVolume(int addNumber){
		
		if( isConnectDevice() ) {
			return LANTvControl.TvControler.addVolume(addNumber);
		}else {
			return false;
		}
	}
	
	//频道+
	public static boolean addChannel(int addNumber) {
		
		if( isConnectDevice() ) {
			return LANTvControl.TvControler.addChannel(addNumber);
		}else{
			return false;
		}
	}

	//声音-
	public static boolean subVolume(int subNumber) {
		
		if( isConnectDevice() ) {
			return LANTvControl.TvControler.subVolume(subNumber);
		}else{
			return false;
		}
	}
	
	//频道-
	public static boolean subChannel(int subNumber) {
		
		if( isConnectDevice() ) {
			return LANTvControl.TvControler.subChannel(subNumber);
		}else{
			return false;
		}
	}
	
	//设置静音
	public static boolean setMute(int type) {
		
		if( isConnectDevice() ) {
			return LANTvControl.TvControler.setMute(type);
		}else{
			return false;
		}
	}
	
	/*
	 * 获取音量
	 */
	public static int getVolume(int type){
		
		if( isConnectDevice() ) {
			return LANTvControl.TvControler.getVolume(type);
		}else{
			return -1;
		}
	}
	
	/**
	 * @param x
	 * @param y
	 * @param action action 定义如下
	 * 	MOUSE_ACTION_MOVE =1, //鼠标移动
	 *	MOUSE_RIGHT_SINGLE_CLICK =2, //右键单击
	 *	MOUSE_RIGHT_DOWN=4, //按下右键
	 *	MOUSE_RIGHT_UP=5, //松开右键
	 *	MOUSE_LEFT_SINGLE_CLICK =7,
	 *	MOUSE_LEFT_DOWN=9,
	 *	MOUSE_LEFT_UP=10, 
	 *	MOUSE_WHEEL=12, //滚轮事件
	 * @return
	 */
	public static boolean setMouse(int x, int y, int action) {
		
		if( isConnectDevice() ) {
			return LANTvControl.TvControler.setTVMouse(x, y, action);
		}else{
			return false;
		}
	}
	
	/**
	 * 获取电视模式
	 * @return
	 */
	public static int getMode() {
		
		if( isConnectDevice() ) {
			return LANTvControl.TvControler.getMode();
		}else{
			return -1;
		}
	}
	/*
	 *向电视发送连接手机 
	 */
	public static boolean sendConnectTv(String info){
		if(isConnectDevice()){
			System.out.println("发送手机信息给电视！"+info);
			return LANTvControl.TvControler.showPhoneCtrl(info);
		}else{
			System.out.println("手机未连接电视！");
			return false;
		}
	}
	
	
	/**
	 * 获取设备id
	 * @return
	 */
	public static int getDeviceID() {
		
		if( isConnectDevice() ) {
			return LANTvControl.TvControler.getDeviceID();
		}else{
			return -1;
		}
	}
	
	/**
	 * 获取UUID
	 */
	public static String getUUID() {
		
		if( isConnectDevice() ) {
			return LANTvControl.TvControler.getUUID();
		}else{
			return "";
		}
	}
	
	/**
	 * 功能：发送绑定申请
	 */
	public static void sendBundingRequest() {
		
		if( isConnectDevice() ) {
			LANTvControl.TvControler.requiredBind();
		}
	}
	
	/**
	 * 获取EPG
	 * @return
	 */
	public static void getEPG( final int start, final int requestSize) {

		if( isConnectDevice() ) {
			IPPDTVChannelInfo[] channelInfoArray = LANTvControl.TvControler.getEPGInfo(start, requestSize, allEPGNumber);
			if(channelInfoArray!=null) {
				for(int i=0;i<channelInfoArray.length;i++){
					allEPGList.add(channelInfoArray[i]);
				}
			}
		}		
		
		printEPG(allEPGList);
	}
	
	/**
	 * 获取常用频道
	 */
	public static void getCommonUsedEPG(final int start, final int requestSize) {
			
		if( isConnectDevice() ) {
			IPPDTVChannelInfo[] commonEPGArray = LANTvControl.TvControler
					.getSortedEPGInfo(start, requestSize, commonEPGNumber);
			if(commonEPGArray!=null) {
				for(int i=0;i<commonEPGArray.length;i++){
					commonEPGList.add(commonEPGArray[i]);
				}
			}
		}
		
		printEPG(commonEPGList);
	}
	
	/**
	 * 功能：验证常用频道是否存在
	 * @return
	 */
	public static boolean isCommonEPGExit() {
		
		boolean isCommonEPGExit = false;
		if(commonEPGList != null && commonEPGList.size() > 0) {
			isCommonEPGExit = true;
		}
		
		return isCommonEPGExit;
	}
	
	/**
	 * 功能：验证全部频道的EPG是否存在
	 * @param epgList
	 */
	public static boolean isAllEpgExit() {
		
		boolean isAllEPGExit = false;
		if(allEPGList != null && allEPGList.size() > 0) {
			isAllEPGExit = true;
		}
		
		return isAllEPGExit;
	}
	
	public static void printEPG( List<IPPDTVChannelInfo> epgList ) {
		
		int size = epgList.size();
		System.out.println("printEPG=size=="+size);
		IPPDTVChannelInfo channelInfo = null;
		
		for(int i = 0; i < size; i++ ) {
			
			channelInfo = epgList.get(i);
			IPPEPGEvent[] epgEventArray = channelInfo.mEvents;
			int eventSize = epgEventArray.length;
			IPPEPGEvent epgEvent = null;
			System.out.println("channelName=" + channelInfo.mChanneName);
			
//			for(int m = 0; m < eventSize; m++) {
//				epgEvent = epgEventArray[m];
//				System.out.println("mEventName==="+epgEvent.mEventName);
//				System.out.println("mShortText==="+epgEvent.mShortText);
//				System.out.println("mText==="+epgEvent.mText);
//				System.out.println("mStartDate==="+epgEvent.mStartDate);
//				System.out.println("mStartTime==="+epgEvent.mStartTime);
//				System.out.println("durationTime==="+epgEvent.mDurationTime);
//			}
		}
	}
	
	/**
	 * 功能：获取channelName的首字母
	 * 例如：长虹 首字母是  C
	 * @param channelName
	 * @return
	 */
	public static String getFistspellOfChannelName(String channelName) {
		
		String catalog = "";
		String firstName = channelName.substring(0, 2);
		if(firstName.equals("长虹") || firstName.equals("重庆")) {
			catalog = "C";
		}else{
//			catalog = SpellUtils.converterToFirstSpell( channelName ).substring(0, 1);
		}
		
		return catalog;
	}
	
	/**
	 * 功能：获取正在观看的界面
	 * @return
	 */
	public static String getWatchingChannelName() {
		
		String watchingChannelName = "";
		if( isConnectDevice() ) {
			watchingChannelName = LANTvControl.TvControler.getChannelName();
		}
		
		return watchingChannelName;
	}
	
	/**
	 * 功能：换台
	 * @param channelName
	 * @param ChannelIndex
	 */
	public static void changeChannel(final String channelName, final int ChannelIndex) {
		
		if( isConnectDevice() ) {
			new Thread() {
				@Override
				public void run() {
					LANTvControl.TvControler.changeChannel(channelName, String.valueOf(ChannelIndex));
				}
			}.start();
			
		}
		
	}
	
	/**
	 * 功能设置电视模式
	 * @return
	 */
	public static boolean setMode(final int mode) {
		
		if( isConnectDevice() ) {
			
//			new Thread() {
//				@Override
//				public void run() {
//					LANTvControl.TvControler.setMode( mode );
//					if(mode == LANTvControl.dtvMode) {
//						MainActivity.sendShowDTVControlUICommand();
//					}
//					if(mode == LANTvControl.stbMode) {
//						MainActivity.sendShowSTBControlUICommand();
//					}
//					if(mode == LANTvControl.mainPageMode) {
//						MainActivity.sendMainPageCommand();
//					}
//				}
//			}.start();
			
		}

		return true;
	}
	
	/**
	 * 判断输入的电视是否是当前连接的电视
	 * @param uuid
	 * @return
	 */
	public static boolean isConnectThisDevice(String uuid) {
		
		boolean isConnectThisDevice = false;
		if( isConnectDevice() && LANTvControl.TvControler.getUUID().equals(uuid)) {
			return true;
		}

		return isConnectThisDevice;
	}
	
	/**
	 * 发送截屏命令
	 */
	public static boolean sendShutScreenCommand() {
		
		boolean res = false;
		if( isConnectDevice() ) {
			res = LANTvControl.TvControler.fnTVGetScreen(0, 0);
		}
		
		return res;
	}
	
	/**
	 * 添加预约
	 */
	public static boolean addOrder(int channelIndex, int programmeEventId) {
		boolean res = false;
		
		res = LANTvControl.TvControler.addOrderProgramme(channelIndex, programmeEventId);
		return res;
	}
	
	/**
	 * 删除预约
	 */
	public static boolean deleteOrder(int channelIndex, int programmeEventId) {
		boolean res = false;
		res = LANTvControl.TvControler.deleteOrder(channelIndex, programmeEventId);
		return res;
	}
	
	/**
	 * 获取所有的预约列表
	 * @return
	 */
	public static List<OrderChannel> getOrderList() {
		
		if( isConnectDevice() ) {// && myOrderList.size() == 0
			
			List<OrderChannel> tmp = LANTvControl.TvControler.getOrderList();
			if(tmp != null) {
				
				for(int i = 0; i < tmp.size(); i++) {
					int channelIndex = tmp.get(i).channelIndex;
					int eventIndex = tmp.get(i).eventIndex;
					System.out.println("channelIndex=="+channelIndex);
					System.out.println("eventIndex=="+eventIndex);
				}
				
				myOrderList = tmp;
			}
		}
		
		return myOrderList;
	}
	
	
	/**
	 * 判断是否连接了设备
	 * @return
	 */
	private static boolean isConnectDevice() {
		
		boolean res = false;
		if(LANTvControl.TvControler != null) {
			res = true;
		}
		
		return res;
	}
	
	public static void printTime() {
		
		Date dt = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");// MM/dd/yyyy HH:mm:ss
		String sDateTime = formatter.format(dt); // 得到精确到秒的表示：08/31/2006 21:08:00
		System.out.println("now time=="+sDateTime);
	}
	
	/**
	 * 功能：根据频道名获取当前播放节目
	 * @return
	 */
	public static List<IPPEPGEvent> getPlayingEventIndexByChannelName(String channelName) {
		
		List<IPPEPGEvent> playingEPGEvent = new ArrayList<IPPEPGEvent>();
		int playingProgrammeIndex = -1;
		int size = LANTvControl.allEPGList.size();
		IPPDTVChannelInfo channelInfo = null;
		for(int i = 0; i < size; i++ ) {
			channelInfo = LANTvControl.allEPGList.get(i);
			if(channelInfo.mChanneName.equals(channelName)) {
				playingProgrammeIndex = getPlayingEventIndex(channelInfo, 0);
				break;
			}
		}
				
		if( playingProgrammeIndex >= 0 && playingProgrammeIndex < channelInfo.mEvents.length ){
			playingEPGEvent.add(channelInfo.mEvents[playingProgrammeIndex]);
			int nextPlayingProgrammeIndex = playingProgrammeIndex + 1;
			if(nextPlayingProgrammeIndex < channelInfo.mEvents.length) {
				playingEPGEvent.add(channelInfo.mEvents[nextPlayingProgrammeIndex]);
			}
		}
		
		return playingEPGEvent;
	}
	
	/**
	 * 功能：获取当前播放节目的 index
	 * @param channelInfo
	 * @return
	 */
	public static int getPlayingEventIndex(IPPDTVChannelInfo channelInfo, int whichDayEPG) {
				
		int playingProgrammeIndex = -1;
		if(channelInfo != null) {
			IPPEPGEvent[] epgEventArray = channelInfo.mEvents;
			int length = epgEventArray.length;
			
			boolean isPlayingProgramme = false;
			for(int i = 0; i < length; i++) {
				isPlayingProgramme = isPlaying(epgEventArray[i], whichDayEPG);
				if( isPlayingProgramme ) {
					playingProgrammeIndex = i;
					break;
				}
			}
		}
				
		return playingProgrammeIndex;
	}
	
	/**
	 * 功能：获取 给定天数 的最后一个节目
	 */
	public static int getLastProgrammeOfDay(IPPDTVChannelInfo channelInfo, int whichDayEPG) {
		
		int lastProgrammeIndex = -1;
		//检测参数合法性
		int maxDay = 6;//仅仅获取最近1周的EPG
		if( whichDayEPG >= 0 && whichDayEPG <= maxDay && channelInfo != null){
			Calendar myCalendar = Calendar.getInstance();
			myCalendar.add(Calendar.DATE, whichDayEPG);
			
			int year = myCalendar.get(Calendar.YEAR);
			int mouth = myCalendar.get(Calendar.MONTH) + 1;
			int day = myCalendar.get(Calendar.DAY_OF_MONTH);
			
			IPPEPGEvent[] epgEventArray = channelInfo.mEvents;
			int length = epgEventArray.length;
			
			for(int i = 0; i < length; i++) {
				//i是当天，而 i+1 日期发生变化，则认为该节目是当天最后一个节目
				if( epgEventArray[i].mStartDate.mYears == year && epgEventArray[i].mStartDate.mMonths == mouth
						&& epgEventArray[i].mStartDate.mDays == day) {
					
					if(i == length -1 || epgEventArray[i+1].mStartDate.mDays != day) {
						lastProgrammeIndex = i;
					}
				}
				
			}
		}
		
		return lastProgrammeIndex;
	}
	
	/**
	 * 功能：判断epgEvent 是否正在播放
	 * @param epgEvent
	 * @return
	 */
	private static boolean isPlaying(IPPEPGEvent epgEvent, int whichDayEPG) {
		
		Calendar time = Calendar.getInstance();//使用默认时区和语言环境获得一个日历
		time.add(Calendar.DATE, whichDayEPG);
		int year = time.get(Calendar.YEAR);
		int mouth = time.get(Calendar.MONTH) + 1;
		int day = time.get(Calendar.DAY_OF_MONTH);
		int hour = time.get(Calendar.HOUR_OF_DAY);
		int mini = time.get(Calendar.MINUTE);
		
		long nowTime = hour * 60 + mini;
		
		boolean res = false;
		if(epgEvent != null) {
			IPPUTCTime startTime = epgEvent.mStartTime;
			IPPUTCTime durationTime = epgEvent.mDurationTime;
			IPPUTCDate date = epgEvent.mStartDate;
			int startSecondTime = startTime.mHours * 60 + startTime.mMinutes;
			int endSecondTime = startTime.mHours * 60 + startTime.mMinutes
					+ durationTime.mHours * 60 + durationTime.mMinutes;
						
			if(date.mYears == year && date.mMonths == mouth && date.mDays == day ) {
				
				if(whichDayEPG == 0) {
					if( startSecondTime <= nowTime && nowTime <= endSecondTime ) {
						res = true;
					}
				}else{
					res = true;
				}
				
			}
		}
		
		return res;
	}
	
	//获取结束时间
	public static String getEndTime(int startHours, int startMini, int duratHours, int duratMini) {
		
		int endMini = startMini + duratMini;
		int endHouse = startHours + duratHours;
		
		String endTime = "";
		
		if( endMini >= 60 ){
			endMini = endMini - 60;
			endHouse = endHouse + 1;
		}
		
		if(endHouse >= 24) {
			if(endMini < 10) {
				endTime = "00" + ":0" + endMini;
			}else {
				endTime = "00" + ":" + endMini;
			}
		}else{
			if(endHouse < 10) {
				endTime = "0" + endHouse;
			}else {
				endTime = String.valueOf(endHouse);
			}
			
			if(endMini < 10) {
				endTime += ":0" + endMini;
			}else {
				endTime += ":" + endMini;
			}
		}
		
		return endTime;
	}
	
	/**
	 * 功能:获取时间
	 * @param hour
	 * @param mini
	 * @return
	 */
	public static String getStartTime(int hour, int mini) {
		
		String playTime = "";
		if(hour < 10) {
			playTime = "0"+hour;
		}else{
			playTime = String.valueOf(hour);
		}
		
		if(mini < 10) {
			playTime += ":0"+mini;
		}else{
			playTime += ":"+mini;
		}
		
		return playTime;
	}
	
	
	/**
	 * 功能:获取时间
	 * @param hour
	 * @param mini
	 * @return
	 */
	public static String getStartDate(int mounth, int date) {
		
		String playTime = "";
		if(mounth < 10) {
			playTime = "0" + mounth;
		}else{
			playTime = String.valueOf(mounth);
		}
		
		if(date < 10) {
			playTime += ".0"+date;
		}else{
			playTime += "."+date;
		}
		
		return playTime;
	}
	
	
	public static String getWeekDay( int dayOfWeek ){
		
		String weekDay = "";
		switch( dayOfWeek ) {
		case 1:
			weekDay = "周天";
			break;
		case 2:
			weekDay = "周一";
			break;
		case 3:
			weekDay = "周二";
			break;
		case 4:
			weekDay = "周三";
			break;
		case 5:
			weekDay = "周四";
			break;
		case 6:
			weekDay = "周五";
			break;
		case 7:
			weekDay = "周六";
			break;
		}
		
		return weekDay;
	}
}
