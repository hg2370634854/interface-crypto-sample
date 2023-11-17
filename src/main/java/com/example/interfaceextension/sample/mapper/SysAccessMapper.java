package com.example.interfaceextension.sample.mapper;

import com.example.interfaceextension.sample.domain.SysAccess;

/**
 * 相关接口授权信息Mapper接口
 */
public interface SysAccessMapper {
    /**
     * 通过access key查询
     */
    SysAccess selectSysAccessByAccessKey(String accessKey);
}
