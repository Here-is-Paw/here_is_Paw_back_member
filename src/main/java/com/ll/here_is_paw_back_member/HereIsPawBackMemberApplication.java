package com.ll.here_is_paw_back_member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class HereIsPawBackMemberApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HereIsPawBackMemberApplication.class, args);

//		// 스프링 컨텍스트에서 값 가져오기
//		String frontUrl = context.getEnvironment().getProperty("custom.site.frontUrl");
//		String backUrl = context.getEnvironment().getProperty("custom.site.backUrl");
//
//		log.info("siteFrontUrl: {}", frontUrl);
//		log.info("siteBackUrl: {}", backUrl);
	}
}
