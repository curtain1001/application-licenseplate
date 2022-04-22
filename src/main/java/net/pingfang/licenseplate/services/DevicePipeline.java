package net.pingfang.licenseplate.services;

import com.sun.jna.Pointer;

import net.pingfang.licenseplate.aisle.Integration;
import net.pingfang.licenseplate.aisle.State;
import net.pingfang.licenseplate.config.DeviceInit;
import net.pingfang.licenseplate.domain.ResultCode;
import net.sdk.function.systemcommon.control.message.callback.Callback_FGetReportCBEx.FGetReportCBEx;
import net.sdk.function.systemcommon.imagesnap.callback.Callback_FGetImageCBEx.FGetImageCBEx;

/**
 * @author 王超
 * @description TODO
 * @date 2022-04-22 11:58
 */
public class DevicePipeline implements Integration {

	public int handle = -1;
	public final String ip;
	public final short port;

	public DevicePipeline(String ip, short port) {
		this.ip = ip;
		this.port = port;
	}

	private void setHandle(int handle) {
		this.handle = handle;
	}

	@Override
	public void addCamera(String ip) {
		if (handle == -1) {
			int aHandle = DeviceInit.net.Net_AddCamera(ip);
			if (aHandle != -1) {
				setHandle(aHandle);
			}
		} else {
			throw new RuntimeException("该通道已存在相机");
		}

	}

	@Override
	public int getHandle() {
		return handle;
	}

	@Override
	public void connect(short port, short timeout) {
		int cState = DeviceInit.net.Net_ConnCamera(handle, port, timeout);
		if (cState != 0) {
			throw new RuntimeException("相机连接失败：" + ResultCode.getMsg(cState));
		}
	}

	@Override
	public int queryConnState() {
		return DeviceInit.net.Net_QueryConnState(handle);
	}

	@Override
	public void registerReportCallback(FGetReportCBEx ex) {
		// 回调

//		FGetReportCBEx ex = new FGetReportCBEx() {
//			/**
//			 *
//			 * @param handle    相机句柄
//			 * @param ucType
//			 * @param ptMessage
//			 * @param pUserData
//			 * @return
//			 */
//			@Override
//			public int invoke(int handle, byte ucType, Pointer ptMessage, Pointer pUserData) {
//				if (ucType == 7) {
//					Data_T_IOStateRsp.T_IOStateRsp.ByReference tIOStateRsp = new Data_T_IOStateRsp.T_IOStateRsp.ByReference();
//					int nSize = tIOStateRsp.size();
//					byte[] Readbuf = new byte[nSize];
//					ptMessage.read(0, Readbuf, 0, nSize);
//					tIOStateRsp.ucIndex = Readbuf[0];
//				}
//				return 0;
//			}
//
//		};
		int rrme = DeviceInit.net.Net_RegReportMessEx(handle, ex, Pointer.NULL);
	}

	@Override
	public void registerRecvCallback(FGetImageCBEx ex) {
		int rev = DeviceInit.net.Net_RegImageRecvEx(handle, ex, Pointer.NULL);
		if (rev != 0) {
			throw new RuntimeException("车牌识别结果获取回调函数注册完毕：" + ResultCode.getMsg(rev));
		}
//		FGetImageCBEx callback = new FGetImageCBEx() {
//			/**
//			 *
//			 * @param tHandle     相机句柄
//			 * @param uiImageId   当前触发识别的相机句柄图
//			 * @param ptImageInfo 识别结果
//			 * @param picInfo     图片缓冲区
//			 * @param pointer     回调的用户信息
//			 * @return
//			 */
//			@Override
//			public int invoke(int tHandle, int uiImageId, ByReference ptImageInfo, T_PicInfo.ByReference picInfo,
//					Pointer pointer) {
//				return 0;
//			}
//		};
	}

//	@Override
//	public void registerRealDataCallback(NET_REALDATACALLBACKEx ex) {
//		DeviceInit.net.Net_SetRealDataCallBackEx();
//	}

	@Override
	public void delCamera() {
		int del = DeviceInit.net.Net_DelCamera(handle);
		if (del != 0) {
			throw new RuntimeException("删除相机失败：" + ResultCode.getMsg(del));
		}
	}

	@Override
	public void disConnect() {
		int dcc = DeviceInit.net.Net_DisConnCamera(handle);
		if (dcc != 0) {
			throw new RuntimeException("注销相机登录：" + ResultCode.getMsg(dcc));
		}
	}

	@Override
	public State getStatus() {
		return null;
	}

	@Override
	public void close() {

	}
}
