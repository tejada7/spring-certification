package com.ftm.vcp.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev | prod")
public class DevOrProdBean {
}
