package com.example.interfaceextension.sample.service.impl;

import com.example.interfaceextension.sample.domain.SysAccess;
import com.example.interfaceextension.sample.mapper.SysAccessMapper;
import com.hg.interfaceextension.common.service.AccessDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author huangguang
 */
@CacheConfig(cacheNames = "sysAccess")
@Service
public class SysServiceImpl implements AccessDetailService {

    @Autowired
    private SysAccessMapper sysAccessMapper;

    @Cacheable(key = "#accessKey", unless = "#result == null")
    @Override
    public SysAccess loadSysAccessByAccessKey(String accessKey) {
        SysAccess sysAccess = sysAccessMapper.selectSysAccessByAccessKey(accessKey);
        //sysAccess.setIpWhiteList("localhost");
        return sysAccess;
    }
}
