package com.sk.sample.board.application.sp.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.sample.board.domain.service.NoticeService;

@RestController
@RequestMapping("/v1/notice")
public class NoticeRestController {
	
	@Autowired
	private NoticeService noticeService;

}
