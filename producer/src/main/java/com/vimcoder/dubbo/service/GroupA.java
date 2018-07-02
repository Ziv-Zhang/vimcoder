package com.vimcoder.dubbo.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.vimcoder.dubbo.service.IMergeGroup;

@Service(group = "a")
public class GroupA implements IMergeGroup {

	@Override
	public List<String> list() {
		List<String> s = new ArrayList<>();
		s.add("service A");
		return s;
	}

}
