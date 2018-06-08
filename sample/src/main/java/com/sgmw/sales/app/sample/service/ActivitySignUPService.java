package com.sgmw.sales.app.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgmw.sales.app.sample.domain.ActivitySignUp;
import com.sgmw.sales.app.sample.domain.ActivitySignUpExample;
import com.sgmw.sales.app.sample.repository.mybatis.ActivitySignUpMapper;

@Service
public class ActivitySignUPService {
	
	@Autowired
	private ActivitySignUpMapper asum;
	
	public List<ActivitySignUp>  getSignUp() {
		ActivitySignUpExample example = new ActivitySignUpExample();
		List<ActivitySignUp> ret = asum.selectByExample(example);
		return ret;
	}

	public int save(ActivitySignUp activitySignUp) {
		return asum.insert(activitySignUp);
	}
	
	public int update(ActivitySignUp activitySignUp) {
		return asum.updateByPrimaryKey(activitySignUp);
	}

	public ActivitySignUp findOne(Long id) {
		return asum.selectByPrimaryKey(id);
	}

	public void delete(Long id) {
		asum.deleteByPrimaryKey(id);
	}
}
