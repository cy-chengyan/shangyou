package shangyou.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@ComponentScan({"shangyou.api", "shangyou.core"})
@MapperScan({"shangyou.core.data.mapper"})
@SpringBootApplication // 开启组件扫描和自动配置
public class Main {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Main.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
