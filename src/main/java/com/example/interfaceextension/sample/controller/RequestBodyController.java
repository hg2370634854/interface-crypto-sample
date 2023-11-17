package com.example.interfaceextension.sample.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.interfaceextension.sample.domain.R;
import com.example.interfaceextension.sample.domain.User;
import com.hg.interfaceextension.annotation.Decrypt;
import com.hg.interfaceextension.annotation.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangguang
 */
@Slf4j
@RestController
public class RequestBodyController {

    @Encrypt
    @Decrypt
    @PostMapping("/auth/argument")
    public R authArgument(@RequestBody User user) {
        JSONObject json = JSONObject.from(user);
        log.info(json.toJSONString());
        log.info("有参授权接口");
        return R.ok(user);
    }

    @Encrypt
    @Decrypt
    @PostMapping("/auth/noArgument")
    public R authNoArgument() {
        log.info("无参授权接口");
        return R.ok();
    }

    @Decrypt
    @Encrypt
    @PostMapping("/argument")
    public R argument(@RequestBody User user) {
        JSONObject json = JSONObject.from(user);
        log.info(json.toJSONString());
        log.info("有参接口");
        return R.ok();
    }

    @Decrypt
    @Encrypt
    @PostMapping("/noArgument")
    public R noArgument() {
        log.info("无参接口");
        return R.ok();
    }
}
