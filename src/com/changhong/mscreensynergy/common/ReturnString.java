package com.changhong.mscreensynergy.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ReturnString {

	private static ReturnString myRetrunStringMap = null;
	private static Map<String, String> errorStringMap = new HashMap<String, String>();
	private static Map<String, String> successStringMap = new HashMap<String, String>();
	
	/**
	 * 功能：私有的构造函数
	 */
	private ReturnString() {
		
		setErrorCodeMap();
		setSuccessCodeInfo();
	}
	
	/**
	 * 功能：获取单例类的对象
	 * @return
	 */
	public static synchronized ReturnString getInstance() {
		
		if( myRetrunStringMap == null ){
			myRetrunStringMap = new ReturnString();
		}
		
		return myRetrunStringMap;
	}
	
	/**
	 * 功能：返回失败的原因
	 * @param Code
	 * @return
	 */
	public String getErrorStringByCode(String code) {
		
		String res = "没有找到对应的错误描述";
		if(code != null && errorStringMap.containsKey(code)) {
			res = errorStringMap.get(code);
		}
		return res;
	}
	
	/**
	 * 功能：成功后的提示
	 * @param code
	 * @return
	 */
	public String getSuccessStringByCode(String code) {
		
		String res = "没有找到操作成功的描述信息";
		if(code != null && successStringMap.containsKey(code)) {
			res = successStringMap.get(code);
		}
		
		return res;
	}
	
	/**
	 * 功能：操作是否成功
	 * @param code
	 * @return
	 */
	public boolean isOperationSuccess(String code) {
		
		boolean res = false;
		if(code != null && successStringMap.containsKey(code)) {
			res = true;
		}
		
		return res;
	}
	
	/**
	 * 功能：填充失败的原因
	 */
	private void setErrorCodeMap() {
		System.out.println("llllll"+Locale.getDefault().getLanguage());
		String systemLanguage = Locale.getDefault().getLanguage();
		if(systemLanguage.contains("zh")){
			errorStringMap.put("100", "数据库异常");
			errorStringMap.put("0001", "用户名和手机号为空");
			errorStringMap.put("0002", "短信验证码为空");
			errorStringMap.put("0003", "密码为空");
			errorStringMap.put("0004", "指纹图像地址为空");
			errorStringMap.put("0005", "应用ID为空");
			errorStringMap.put("0006", "短信验证码错误");
			errorStringMap.put("0007", "用户名已经被注册");
			errorStringMap.put("0008", "指纹已经被注册");
			errorStringMap.put("0009", "指纹特征值加密失败");
			errorStringMap.put("0010", "密码加密失败");
			errorStringMap.put("1001", "新增app失败");
			errorStringMap.put("1002", "appName为空");
			errorStringMap.put("1003", "app重名");
			
			errorStringMap.put("2001", "短信发送失败");
			errorStringMap.put("2003", "短信没有找到");
			
			errorStringMap.put("3001", "登录时json串解析异常");
			errorStringMap.put("3002", "用户名和指纹特征值为空");
			errorStringMap.put("3003", "密码为空");
			errorStringMap.put("3004", "应用ID为空");
			errorStringMap.put("3005", "设备编号为空");
			errorStringMap.put("3006", "设备型号为空");
			errorStringMap.put("3007", "设备类型为空");
			errorStringMap.put("3008", "用户信息未找到");
			errorStringMap.put("3009", "密码错误");
			errorStringMap.put("3010", "同步用户信息失败");
			errorStringMap.put("3011", "指纹不正确");
			errorStringMap.put("3012", "解密密码失败");
			errorStringMap.put("3013", "解密指纹失败");
			errorStringMap.put("3014", "解析以登录过app失败");
			errorStringMap.put("3015", "更新用户信息失败");
			errorStringMap.put("3016", "加密指纹失败");
			
			errorStringMap.put("4001", "用户信息更新失败");
			errorStringMap.put("4002", "用户信息解析成json串失败");
			errorStringMap.put("4003", "用户信息未找到");
			errorStringMap.put("4004", "用户密码解密失败");
			errorStringMap.put("4005", "用户指纹解密失败");
			errorStringMap.put("4006", "用户名、密码、指纹全部为空 ");
			errorStringMap.put("4007", "密码为空");
			errorStringMap.put("4008", "加密指纹失败");
			errorStringMap.put("4009", "密码错误");
			errorStringMap.put("4010", "指纹错误");
			errorStringMap.put("4011", "密码加密失败");
			
			errorStringMap.put("5001", "密保问题json串解析失败");
			errorStringMap.put("5002", "密保问题解析成json失败");
			errorStringMap.put("5003", "增加密保问题失败");
			errorStringMap.put("5004", "问题已经存在");
			
			errorStringMap.put("6002", "密保信息json解析失败");
			errorStringMap.put("6003", "密保信息解析成json失败");
			errorStringMap.put("6004", "用户ID为空");
			errorStringMap.put("6005", "密保信息没找到");
			
			errorStringMap.put("7001", "解析json失败");
			errorStringMap.put("7002", "问题答案不正确");
			errorStringMap.put("7003", "用户没有设置密保问题");
			errorStringMap.put("7004", "用户没有设置密码找回手机");
			errorStringMap.put("7005", "问题或者答案为空");
			errorStringMap.put("7006", "电话号码为空");
			errorStringMap.put("7007", "用户ID为空");
			errorStringMap.put("7008", "通过userID找不到用户信息");
			errorStringMap.put("7009", "问题1或者答案1不正确");
			errorStringMap.put("7010", "问题2或者答案2不正确");
			errorStringMap.put("7011", "问题3或者答案3不正确");
			errorStringMap.put("7012", "找回密码失败");
			
			errorStringMap.put("8001", "修改密码失败");
			errorStringMap.put("8002", "旧密码不正确");
			errorStringMap.put("8003", "json解析失败");
			errorStringMap.put("8004", "用户ID为空");
			errorStringMap.put("8005", "旧密码为空");
			errorStringMap.put("8006", "新密码为空");
			
			errorStringMap.put("10004", "不能自己加自己为好友");
			errorStringMap.put("10005", "申请者ID不存在");
			errorStringMap.put("10006", "对方已经是你好友了");
			errorStringMap.put("10007", "用户未注册IPP 发送SMS不成功");
			errorStringMap.put("10011", "连接mina服务器失败");
			errorStringMap.put("11111", "服务器或者网络异常");
			errorStringMap.put("10015", "设备sn不存在或设备类型错误");
			errorStringMap.put("10017", "绑定码错误");
			errorStringMap.put("10019", "mina 服务器断开");
			errorStringMap.put("11112", "服务器错误");
		}else if(systemLanguage.contains("en")){
			errorStringMap.put("100", "Data Error");
			errorStringMap.put("0001", "User Or Tel Num Empty");
			errorStringMap.put("0002", "Verification Code Empty");
			errorStringMap.put("0003", "Password Empty");
			errorStringMap.put("0004", "指纹图像地址为空");
			errorStringMap.put("0005", "ID Empty");
			errorStringMap.put("0006", "Verification Code Error");
			errorStringMap.put("0007", "This User Has Registed");
			errorStringMap.put("0008", "指纹已经被注册");
			errorStringMap.put("0009", "指纹特征值加密失败");
			errorStringMap.put("0010", "密码加密失败");
			errorStringMap.put("1001", "新增app失败");
			errorStringMap.put("1002", "appName为空");
			errorStringMap.put("1003", "app重名");
			
			errorStringMap.put("2001", "短信发送失败");
			errorStringMap.put("2003", "短信没有找到");
			
			errorStringMap.put("3001", "登录时json串解析异常");
			errorStringMap.put("3002", "用户名和指纹特征值为空");
			errorStringMap.put("3003", "Password Empty");
			errorStringMap.put("3004", "应用ID为空");
			errorStringMap.put("3005", "设备编号为空");
			errorStringMap.put("3006", "设备型号为空");
			errorStringMap.put("3007", "设备类型为空");
			errorStringMap.put("3008", "Can not Find User Info");
			errorStringMap.put("3009", "Password Error");
			errorStringMap.put("3010", "Sync User Info Failure");
			errorStringMap.put("3011", "指纹不正确");
			errorStringMap.put("3012", "解密密码失败");
			errorStringMap.put("3013", "解密指纹失败");
			errorStringMap.put("3014", "解析以登录过app失败");
			errorStringMap.put("3015", "更新用户信息失败");
			errorStringMap.put("3016", "加密指纹失败");
			
			errorStringMap.put("4001", "用户信息更新失败");
			errorStringMap.put("4002", "用户信息解析成json串失败");
			errorStringMap.put("4003", "用户信息未找到");
			errorStringMap.put("4004", "用户密码解密失败");
			errorStringMap.put("4005", "用户指纹解密失败");
			errorStringMap.put("4006", "用户名、密码、指纹全部为空 ");
			errorStringMap.put("4007", "密码为空");
			errorStringMap.put("4008", "加密指纹失败");
			errorStringMap.put("4009", "密码错误");
			errorStringMap.put("4010", "指纹错误");
			errorStringMap.put("4011", "密码加密失败");
			
			errorStringMap.put("5001", "密保问题json串解析失败");
			errorStringMap.put("5002", "密保问题解析成json失败");
			errorStringMap.put("5003", "增加密保问题失败");
			errorStringMap.put("5004", "问题已经存在");
			
			errorStringMap.put("6002", "密保信息json解析失败");
			errorStringMap.put("6003", "密保信息解析成json失败");
			errorStringMap.put("6004", "用户ID为空");
			errorStringMap.put("6005", "密保信息没找到");
			
			errorStringMap.put("7001", "解析json失败");
			errorStringMap.put("7002", "问题答案不正确");
			errorStringMap.put("7003", "用户没有设置密保问题");
			errorStringMap.put("7004", "用户没有设置密码找回手机");
			errorStringMap.put("7005", "问题或者答案为空");
			errorStringMap.put("7006", "电话号码为空");
			errorStringMap.put("7007", "用户ID为空");
			errorStringMap.put("7008", "通过userID找不到用户信息");
			errorStringMap.put("7009", "问题1或者答案1不正确");
			errorStringMap.put("7010", "问题2或者答案2不正确");
			errorStringMap.put("7011", "问题3或者答案3不正确");
			errorStringMap.put("7012", "找回密码失败");
			
			errorStringMap.put("8001", "Password Updates Failure");
			errorStringMap.put("8002", "Current Password Error");
			errorStringMap.put("8003", "json解析失败");
			errorStringMap.put("8004", "用户ID为空");
			errorStringMap.put("8005", "Current Password Empty");
			errorStringMap.put("8006", "New Password Empty");
			
			errorStringMap.put("10004", "Can't Add Yourself");
			errorStringMap.put("10005", "申请者ID不存在");
			errorStringMap.put("10006", "The Other Has Been Your Friend");
			errorStringMap.put("10007", "用户未注册IPP 发送SMS不成功");
			errorStringMap.put("10011", "连接mina服务器失败");
			errorStringMap.put("11111", "Server Or Net Error");
			errorStringMap.put("10015", "设备sn不存在或设备类型错误");
			errorStringMap.put("10017", "Binding Code Error");
			errorStringMap.put("10019", "mina 服务器断开");
			errorStringMap.put("11112", "Server Error");
		}
	}
	
	/**
	 * 功能：填充成功信息
	 */
	private void setSuccessCodeInfo() {
		String systemLanguage = Locale.getDefault().getLanguage();
		if(systemLanguage.contains("zh")){
			successStringMap.put("10018", "发送语音消息成功");
			successStringMap.put("10016", "绑定成功");
			errorStringMap.put("10013", "设备被绑定");
			successStringMap.put("10012", "注册成功");
			successStringMap.put("10010", "登录成功");
			successStringMap.put("10009", "发送申请好友消息成功");
			successStringMap.put("10008", "用户未注册IPP,发送邀请注册短信成功");
			successStringMap.put("10001", "发送授权消息成功");
			successStringMap.put("10002", "发送邀请同看消息成功");
			successStringMap.put("10003", "回复邀请同看消息成功");
			successStringMap.put("7000", "找回密码成功");
			successStringMap.put("6000", "保存密保信息成功"); 
			successStringMap.put("6001", "更新密保信息成功");
			successStringMap.put("5000", "新增密保问题成功");
			successStringMap.put("2002", "短信发送成功");
			successStringMap.put("4000", "更新用户信息成功");
			successStringMap.put("10014", "设备未被绑定");
		}else if(systemLanguage.contains("en")){
			successStringMap.put("10018", "发送语音消息成功");
			successStringMap.put("10016", "Bind Successfully");
			errorStringMap.put("10013", "Device Binded");
			successStringMap.put("10012", "Regist Successfully");
			successStringMap.put("10010", "Login Successfully");
			successStringMap.put("10009", "Add Requisitions Send Successfully");
			successStringMap.put("10008", "The Other Has Not Registed,Invitation SMS Sent Successfully");
			successStringMap.put("10001", "Authorization Message Sent Successfully");
			successStringMap.put("10002", "Invite Watch-Together Message Sent Successfully");
			successStringMap.put("10003", "Reply of Invitation Watch-Together Message Sent Successfully");
			successStringMap.put("7000", "Password Retake Successfully");
			successStringMap.put("6000", "保存密保信息成功"); 
			successStringMap.put("6001", "更新密保信息成功");
			successStringMap.put("5000", "新增密保问题成功");
			successStringMap.put("2002", "SMS Sent Successfully");
			successStringMap.put("4000", "Update User Info Successfully");
			successStringMap.put("10014", "Device Unbinded");
		}
	}
}
