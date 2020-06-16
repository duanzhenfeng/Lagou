package com.lg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.lg.dzf.mapper")
public class LGApplication {
    public static void main(String[] args) {
        SpringApplication.run(LGApplication.class);
    }
}
