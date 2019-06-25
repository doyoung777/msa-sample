package com.sk.sample.board;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.sk.sample.board.domain.model.Notice;
import com.sk.sample.board.domain.model.NoticeCategory;
import com.sk.sample.board.domain.repository.NoticeRepository;
import com.sk.sample.board.domain.service.NoticeService;
import com.sk.sample.mall.shared.domain.Address;

@SpringBootApplication
@EnableFeignClients
public class BoardApplication {
	
	@Autowired
	private NoticeRepository noticeRepository;

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initData(NoticeRepository noticeRepository) {	
		return (args) -> {
			insertNotices(noticeRepository);
			displayNotices(noticeRepository);
		};
	}
	
	public void insertNotices(NoticeRepository noticeRepository) {
		Notice notice1 = new Notice("제목1", "공지사항입니다.", NoticeCategory.GENERAL, new Long(6758), "공지사항");
		noticeRepository.save(notice1);
	}
	
	public void displayNotices(NoticeRepository noticeRepository) {
		System.out.println("***************************************************************");
		
		Iterable<Notice> noticeList = noticeRepository.findAll();
		for(Notice notice : noticeList) {
			System.out.println(notice.toString());	
		}
		
		System.out.println("***************************************************************");
	}
}
