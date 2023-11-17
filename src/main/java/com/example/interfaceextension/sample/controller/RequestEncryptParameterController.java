package com.example.interfaceextension.sample.controller;

import com.example.interfaceextension.sample.domain.R;
import com.hg.interfaceextension.annotation.Decrypt;
import com.hg.interfaceextension.annotation.Encrypt;
import com.hg.interfaceextension.annotation.RequestEncryptParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author huangguang
 */
@Slf4j
@RestController
public class RequestEncryptParameterController {


    @Encrypt
    @GetMapping("/encryptParameter")
    public R encryptParameter(@RequestEncryptParam("name") Map<String, String> name) {

        log.error("name：{}", name);
        return R.ok("test");
    }

    @PostMapping("/auth/encryptParameter")
    public R authEncryptParameter(@RequestEncryptParam("name") Map<String, String> name) {
        log.error("name：{}", name);
        return R.ok();
    }

    @PostMapping("/encryptParameterUpload")
    public R encryptParameterUpload(@RequestEncryptParam("name") Map<String, String> name,
                                    @RequestParam("file") MultipartFile file,
                                    @RequestEncryptParam("file") MultipartFile file1) {
        log.error("name：{}, file：{}, file1：{}", name, file.getOriginalFilename(), file1);
        return R.ok();
    }

    @PostMapping("/auth/encryptParameterUpload")
    public R authEncryptParameterUpload(@RequestEncryptParam("name") Map<String, String> name,
                                    @RequestParam("file") MultipartFile file,
                                    @RequestEncryptParam("file") MultipartFile file1) {
        log.error("name：{}, file：{}, file1：{}", name, file.getOriginalFilename(), file1);
        return R.ok();
    }
}
