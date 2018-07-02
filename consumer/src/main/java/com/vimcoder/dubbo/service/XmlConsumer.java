package com.vimcoder.dubbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbo")
public class XmlConsumer {

	@Autowired
	private IMergeGroup mergeGroup;

	@RequestMapping("/mergeGroup")
	public String mergeGroup() {
		return "从服务端获取信息：" + mergeGroup.list();
	}
}
