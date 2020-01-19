package com.opera.survway.panel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.opera.survway.exception.LoginException;
import com.opera.survway.panel.model.service.PanelService;
import com.opera.survway.panel.model.vo.PanelMember;

@SessionAttributes("loginUser")
@Controller
public class PanelController {
	
	@Autowired
	private PanelService ps;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@PostMapping("panelSignup.me")
	public String insertPanelMember(Model model, PanelMember pm) {
		
		pm.setPanelBirthday(pm.getYear() + pm.getMonth() + pm.getDay());
		pm.setUserPhone(pm.getPhone1() + pm.getPhone2() + pm.getPhone3());
		pm.setUserAddress(pm.getPost() + "/" + pm.getAddress() + "/" + pm.getDetailAddress());
		
		String encPassword =passwordEncoder.encode(pm.getUserPwd());
	    pm.setUserPwd(encPassword);
		System.out.println(pm);
		
		try {
			ps.insertMember(pm);
			
			return "redirect:panelSignup3.panel";
		} catch (LoginException e) {
			model.addAttribute("msg", e.getMessage());
			
			return "panelSignup.panel";
		}
	}
	
	
}












































