package net.pingfang.licenseplate.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

/**
 * @author 王超
 * @description TODO
 * @date 2022-04-22 17:10
 */
@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageRecvInfo {
	short usWidth; // 图片的宽度，单位:像素
	short usHeight; // 图片的高度，单位:像素
	char ucVehicleColor; // 车身颜色，E_ColorType
	char ucVehicleBrand; // 车标，E_VehicleFlag
	char ucVehicleSize; // 车型(1大2中3小)，,目前根据车牌颜色来区分大小车，蓝牌为小车，黄牌为大车
	char ucPlateColor; // 车牌颜色
	char[] szLprResult; // 车牌，若为'\0'，表示无效GB2312编码
	short[] usLpBox; // 车牌位置，左上角(0, 1), 右下角(2,3)
	char ucLprType; // 车牌类型,
	short usSpeed; // 单位km/h
	char ucSnapType; // 抓拍模式,
	char ucHaveVehicle; // 车牌防伪 0未知1异常2正常
	char[] acSnapTime; // 图片抓拍时间:格式YYYYMMDDHHMMSSmmm(年月日时分秒毫秒)
	char ucViolateCode; // 违法代码
	char ucLaneNo; // 车道号,从0开始编码
	int uiVehicleId; // 检测到的车辆id，若为同一辆车，则id相同
	char ucScore; // 车牌识别可行度
	char ucDirection; // 行车方向E_Direction
	char ucTotalNum; // 该车辆抓拍总张数
	char ucSnapshotIndex; // 当前抓拍第几张，从0开始编号

}
