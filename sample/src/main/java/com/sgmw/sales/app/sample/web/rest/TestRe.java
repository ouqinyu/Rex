package com.sgmw.sales.app.sample.web.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sgmw.sales.app.sample.security.SecurityUtils;
import com.sgmw.sales.app.sample.service.TestService;

/**
 * 微服务间调用是示例
 */
@RestController
@RequestMapping("/api")
public class TestRe {
	
	@Autowired
    TestService testService;
	
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String sayHi(){
    	Optional<String> opt = SecurityUtils.getCurrentUserJWT();
    	String token  = opt.get();
    	System.out.println(token);
    	String info1 = testService.getInfo();
    	String info2 = testService.getInfoBy(token,"hello usercenter,I'm sample!");
    	System.out.println(info1+":"+info2);
        return info1+":"+info2;
    }
}
