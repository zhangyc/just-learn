package com.hackorange.pixify;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hackorange.pixify.mapper")
public class PixifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PixifyApplication.class, args);
        System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());

    }

}
