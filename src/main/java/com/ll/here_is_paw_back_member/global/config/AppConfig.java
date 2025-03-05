package com.ll.here_is_paw_back_member.global.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

public class AppConfig {
    @Getter
    private static String siteFrontUrl;

    @Getter
    private static String siteBackUrl;

    @Value("${custom.site.frontUrl}")
    public void setSiteFrontUrl(String siteFrontUrl) {
        AppConfig.siteFrontUrl = siteFrontUrl;
    }

    @Value("${custom.site.frontUrl}")
    public void setSiteBackUrl(String siteFrontUrl) {
        AppConfig.siteBackUrl = siteBackUrl;
    }
}
