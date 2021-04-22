package com.bitacademy.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitacademy.mysite.service.GuestbookService;
import com.bitacademy.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService; 
	
	@RequestMapping("")
	public String guestbookIndex(Model model) {
		
		List<GuestbookVo> list = guestbookService.getAllList();
		System.out.println(list);
		model.addAttribute("list", list);
		
		return "/guestbook/index";
	}
}
