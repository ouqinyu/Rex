package com.sgmw.sales.app.sample.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.sgmw.sales.app.sample.security.FeignConfig;

/*
 * usercenter:指明微服务需要调用的微服务的名称
 * FeignConfig：请求过滤器，将jwt token写入请求头
 */
@FeignClient(value = "usercenter", configuration = FeignConfig.class)
public interface TestService {
	
	@GetMapping("/api/info")
    String getInfo();
	
	@GetMapping("/api/info/{para}")
    String getInfoBy(@RequestHeader("Authorization") String authorizationToken, @PathVariable("para") String para);
}

