package com.vimcoder.dubbo.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.vimcoder.dubbo.service.IMergeGroup;

@Service(group = "b")
public class GroupB implements IMergeGroup {

	@Override
	public List<String> list() {
		List<String> s = new ArrayList<>();
		s.add("service B");
		return s;
	}

}
