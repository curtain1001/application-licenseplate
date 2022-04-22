package net.pingfang.licenseplate.services;

import java.util.Map;

import net.pingfang.licenseplate.domain.CameraInfo;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import net.pingfang.licenseplate.aisle.Integration;

/**
 * @author 王超
 * @description TODO
 * @date 2022-04-22 11:05
 */
@Component
public class LicensePlateService {
	final Map<Long, Integration> LPS = Maps.newConcurrentMap();

	public void add(CameraInfo info) {
		DevicePipeline pipeline = new DevicePipeline(info.getIp(),info.getPort());
	}
}
