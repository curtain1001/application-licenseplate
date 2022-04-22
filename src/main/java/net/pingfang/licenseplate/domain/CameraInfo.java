package net.pingfang.licenseplate.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author 王超
 * @description TODO
 * @date 2022-04-22 23:19
 */
@Data
@Builder(toBuilder = true)
public class CameraInfo {
    final String ip;
    final short port;
}
