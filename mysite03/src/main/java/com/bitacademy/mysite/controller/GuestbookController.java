package com.bitacademy.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		model.addAttribute("list", list);
		
		return "/guestbook/index";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insertGuestbook(GuestbookVo vo) {
		System.out.println(vo);
		guestbookService.insert(vo);
		
		return "redirect:/guestbook/";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") int no, Model model) {
		model.addAttribute(no);
		
		return "/guestbook/delete";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(GuestbookVo vo) {
		guestbookService.delete(vo);
		
		return "redirect:/guestbook/";
	}
}
