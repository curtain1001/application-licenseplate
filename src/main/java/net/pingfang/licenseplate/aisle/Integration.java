package net.pingfang.licenseplate.aisle;

import net.sdk.function.systemcommon.control.message.callback.Callback_FGetReportCBEx.FGetReportCBEx;
import net.sdk.function.systemcommon.imagesnap.callback.Callback_FGetImageCBEx.FGetImageCBEx;

/**
 * @author 王超
 * @description TODO
 * @date 2022-04-22 11:19
 */
// 初始化->ip->添加相机->天
public interface Integration {
	/**
	 * 添加相机
	 *
	 * @param ip
	 */
	void addCamera(String ip);

	int getHandle();

	/**
	 * 登录相机
	 *
	 * @param port    端口
	 * @param timeout 超时市场 单位：s 默认3 范围【0-15】
	 */
	void connect(short port, short timeout);

	/**
	 * 查询相机控制与图片传输通道链接状态
	 */
	int queryConnState();

	// 注册消息回调
	void registerReportCallback(FGetReportCBEx ex);

	// 注册识别回调
	void registerRecvCallback(FGetImageCBEx ex);

//	// 注册码流回调
//	void registerRealDataCallback();

	/**
	 * 删除相机
	 *
	 */
	void delCamera();

	/**
	 * 断开相机连接
	 */
	void disConnect();

	State getStatus();

	// 释放资源
	void close();

//    List<DeviceLog> getLogs();
}
