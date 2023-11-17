package com.example.interfaceextension.sample.domain;

import com.hg.interfaceextension.common.entity.AccessDetail;
import lombok.Setter;

/**
 * @author huangguang
 */
@Setter
public class SysAccess implements AccessDetail {

    private final static long serialVersionUID = 1L;
    private Long accessId;
    private String accessKey;
    private String secretKey;
    private String ipWhiteList;

    @Override
    public String getAccessKey() {
        return accessKey;
    }

    @Override
    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public String getAesKey() {
        return "1234567890123456";
    }

    public String getWhiteIPList() {
        return ipWhiteList;
    }
}
