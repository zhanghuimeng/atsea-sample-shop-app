package com.docker.atsea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.docker.atsea.configuration.JpaConfiguration;
import com.docker.atsea.controller.LoginController;
import com.docker.atsea.security.JwtFilter;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.docker.atsea"})
@EntityScan("com.docker.atsea.model")
@EnableJpaRepositories("com.docker.atsea.repository")
public class AtSeaApp {

    // FilterRegistrationBean大概是Spring自带的
    // 主要用来对servlet filter进行自定义，比如设置order

    /**
     * Spring 提供了FilterRegistrationBean类，此类提供setOrder方法，
     * 可以为filter设置排序值，让spring在注册webfilter之前排序后再依次注册。
     * @return
     */
	@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/purchase/*"); // 大概是加入了purchase的url

        return registrationBean;
    }

    /**
     * SpringApplication 则是用于从main方法启动Spring应用的类。默认，它会执行以下步骤：

     创建一个合适的ApplicationContext实例 （取决于classpath）。
     注册一个CommandLinePropertySource，以便将命令行参数作为Spring properties。
     刷新application context，加载所有单例beans。
     激活所有CommandLineRunner beans。

     默认，直接使用SpringApplication 的静态方法run()即可。但也可以创建实例，并自行配置需要的设置。
     * @param args
     */
	public static void main(String[] args) {
		SpringApplication.run(AtSeaApp.class, args);
	}
}
