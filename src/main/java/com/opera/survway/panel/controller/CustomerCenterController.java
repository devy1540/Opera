package com.opera.survway.panel.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.opera.survway.common.model.vo.PageInfo;
import com.opera.survway.common.model.vo.Pagination;
import com.opera.survway.common.model.vo.Util;
import com.opera.survway.exception.InquiryException;
import com.opera.survway.exception.SelectException;
import com.opera.survway.panel.model.service.PanelService;
import com.opera.survway.panel.model.vo.Faq;
import com.opera.survway.panel.model.vo.Inquiry;
import com.opera.survway.panel.model.vo.Notice;
import com.opera.survway.panel.model.vo.PanelMember;
import com.opera.survway.panel.model.vo.SearchNotice;

@Controller
public class CustomerCenterController {
	@Autowired
	private PanelService ps;

	/**
	 * @Author : hjheo
	 * @CreateDate : 2020. 1. 27.
	 * @ModifyDate : 2020. 1. 27.
	 * @Description : 공지사항 목록 리스트& 검색
	 */
	@RequestMapping("notice.customerCenter")
	public String selectNotice(HttpServletRequest request, Model model) {
		String queryString = request.getQueryString();

		Map<String, List<String>> queryMap = null;

		int currentPage = 1;

		String searchValue = "";

		SearchNotice searchNotice = new SearchNotice();

		if (queryString != null) {
			queryMap = Util.splitQuery(queryString);
			if (queryMap.containsKey("currentPage")) {
				currentPage = Integer.parseInt(queryMap.get("currentPage").get(0));
			}
			if (queryMap.containsKey("searchValue")) {
				searchValue = queryMap.get("searchValue").get(0);
				searchNotice.setSearchValue(searchValue);

				System.out.println(searchValue);
			}
		}
		int listCount = 0;
		try {
			listCount = ps.getNoticeListCount(searchNotice);
			System.out.println(listCount);

			PageInfo pi = Pagination.getPageInfo(currentPage, listCount);

			searchNotice.setPi(pi);

			List<Notice> noticeList = ps.selectNoticeList(searchNotice);

			System.out.println(noticeList);
			model.addAttribute("noticeList", noticeList);
			model.addAttribute("pi", pi);
			return "notice";

		} catch (SelectException e) {
			model.addAttribute("msg", e.getMessage());
			return "redirect:errorPage.me";
		}
	}

	/**
	 * @Author : hansol
	 * @CreateDate : 2020. 1. 25.
	 * @ModifyDate : 2020. 1. 25.
	 * @Description : 1:1문의 등록
	 */
	@PostMapping("inquirywrite.customerCenter")
	public String insertPanelInquiry(Model model, Inquiry i, HttpServletRequest request) {

		int category = Integer.parseInt(request.getParameter("inquiryCategoryNo"));

		i.setInquiryCategoryNo(category);

		int result;
		try {
			result = ps.insertInquiry(i);
			model.addAttribute("success", result);
		} catch (InquiryException e) {
			request.setAttribute("msg", e.getMessage());
		}

		return "redirect:panelInquiryList.customerCenter";
	}

	/**
	 * @Author : hansol
	 * @CreateDate : 2020. 1. 25.
	 * @ModifyDate : 2020. 1. 25.
	 * @Description : 1:1문의 리스트 보기
	 */
	@RequestMapping("panelInquiryList.customerCenter")
	public String showMyInquiryList(HttpSession session, Model model, HttpServletRequest request, Inquiry iq) {
		// Post로 보낸걸 queryString이라고 한다
		String queryString = request.getQueryString();
		// 그걸 쪼개기 작업하기
		Map<String, List<String>> queryMap = null;

		int currentPage = 1;
		String inquiryTitle = "";
		int inquiryCategoryNo = 0;

		iq = new Inquiry();

		if (queryString != null) {
			queryMap = Util.splitQuery(queryString);
			if (queryMap.containsKey("currentPage")) {
				currentPage = Integer.parseInt(queryMap.get("currentPage").get(0));
			}
			if (queryMap.containsKey("inquiryTitle")) {
				inquiryTitle = queryMap.get("inquiryTitle").get(0);
				iq.setInquiryTitle(inquiryTitle);
			}
			if (queryMap.containsKey("inquiryCategoryNo")) {
				inquiryCategoryNo = Integer.parseInt(queryMap.get("inquiryCategoryNo").get(0));
				iq.setInquiryCategoryNo(inquiryCategoryNo);
			}

		}

		int listCount = 0;

		PanelMember loginUser = (PanelMember) session.getAttribute("loginUser");
		int mno = loginUser.getMno();
		
		
		 
		try {
			iq.setMno(mno);
			listCount = ps.getListCountInquiry(iq);
			
			PageInfo pi = Pagination.getPageInfo(currentPage, listCount);

			iq.setPi(pi);

			List<Inquiry> list = ps.selectAllMyInquiry(iq);
			model.addAttribute("list", list);
			model.addAttribute("pi", pi);
		} catch (InquiryException e) {
			request.setAttribute("msg", e.getMessage());
		}

		return "panelInquiryList";
	}

	/**
	 * @Author :hansol
	 * @CreateDate :2020. 2. 3.
	 * @ModifyDate :2020. 2. 3.
	 * @Description :자주묻는 질문 등록
	 */
	@RequestMapping("faqWrite.customerCenter")
	public String insertFaq(Model model, Faq faq, HttpServletRequest request) {
		int faqCategoryNo = Integer.parseInt(request.getParameter("faqCategoryNo"));
		String faqTitle = request.getParameter("faqTitle");
		String faqContext = request.getParameter("faqContext");

		faq.setFaqCategoryNo(faqCategoryNo);
		faq.setFaqTitle(faqTitle);
		faq.setFaqContext(faqContext);

		int result;
		try {
			result = ps.insertFaq(faq);
			model.addAttribute("success", result);
		} catch (InquiryException e) {
			
			e.printStackTrace();
		}

		return "redirect:faq.customerCenter";
	}

	/**
	 * @Author :hansol
	 * @CreateDate :2020. 2. 3.
	 * @ModifyDate :2020. 2. 3.
	 * @Description :자주묻는 질문 보기 페이징 및 search
	 */
	@RequestMapping("faq.customerCenter")
	public String showFaq(HttpSession session, Model model, HttpServletRequest request, Faq f) {

		String queryString = request.getQueryString();

		Map<String, List<String>> queryMap = null;

		int currentPage = 1;
		String faqTitle = "";
		int faqCategoryNo = 0;

		f = new Faq();

		if (queryString != null) {

			queryMap = Util.splitQuery(queryString);
			if (queryMap.containsKey("currentPage")) {
				currentPage = Integer.parseInt(queryMap.get("currentPage").get(0));
			}
			if (queryMap.containsKey("faqTitle")) {
				faqTitle = queryMap.get("faqTitle").get(0);
				f.setFaqTitle(faqTitle);
			}
			if (queryMap.containsKey("faqCategoryNo")) {
				faqCategoryNo = Integer.parseInt(queryMap.get("faqCategoryNo").get(0));
				f.setFaqCategoryNo(faqCategoryNo);
			}
		}
		int listCount = 0;

		try {
			listCount = ps.getListCountFaq(f);
			PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
			f.setPi(pi);
			List<Faq> list = ps.selectAllFaq(f);

			model.addAttribute("list", list);
			model.addAttribute("pi", pi);
		} catch (InquiryException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "faq";
	}
	
	@RequestMapping("deleteFaq.customerCenter")
	public ModelAndView deleteFaq(HttpSession session, Faq f, String faqNo, ModelAndView mv) {
		
		int fn = Integer.parseInt(faqNo);
		f.setFaqNo(fn);
		
		int num =0;
		
		try {
			num=ps.deleteFaq(f);
			mv.addObject("num",num);
			mv.setViewName("jsonView");
		} catch (InquiryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * @Author	:hansol
	 * @CreateDate	:2020. 2. 3.
	 * @ModifyDate	:2020. 2. 3.
	 * @Description	:자주묻는 질문 수정
	 */
	@RequestMapping("faqUpdate.customerCenter")
	public String updateFaq(HttpServletRequest request, Model model, Faq f) {
		int faqCategoryNo = Integer.parseInt(request.getParameter("faqCategoryNo"));
		int faqNo = Integer.parseInt(request.getParameter("faqNo"));
		String faqTitle = request.getParameter("faqTitle");
		String faqContext = request.getParameter("faqContext");
		
		f.setFaqNo(faqNo);
		f.setFaqCategoryNo(faqCategoryNo);
		f.setFaqTitle(faqTitle);
		f.setFaqContext(faqContext);
		
		int result;
		try {
			result = ps.updateFaq(f);
		} catch (InquiryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:faq.customerCenter";
	}
	
	/**
	 * @Author	:hansol
	 * @CreateDate	:2020. 2. 5.
	 * @ModifyDate	:2020. 2. 5.
	 * @Description	:자주묻는질문 답변달기(관리자)
	 */
	@RequestMapping("answerInquiry.customerCenter")
	public ModelAndView answerInquiry(int answerNo,String answer,ModelAndView mv,Inquiry i) {
		
		i.setInquiryAnswer(answer);
		i.setInquiryNo(answerNo);
		
		int num;
		try {
			num = ps.answerInquiry(i);
			mv.addObject("num",num);
			mv.setViewName("jsonView");
		} catch (InquiryException e) {
			
			e.printStackTrace();
		}
		
		
		return mv;
	}
	
	/**
	 * @Author	:hansol
	 * @CreateDate	:2020. 2. 5.
	 * @ModifyDate	:2020. 2. 5.
	 * @Description	:1:1문의 답변지우기
	 */
	@RequestMapping("deleteAnswerInquiry.customerCenter")
	public ModelAndView deleteAnswerInquiry(ModelAndView mv, int answerNo, Inquiry i) {
		i.setInquiryNo(answerNo);
		System.out.println("오ㅐ 안넘어가 ㅅㅂ");
		int num;
		
		try {
			num = ps.deleteAnswerInquiry(i);
			mv.addObject("num",num);
			mv.setViewName("jsonView");
		} catch (InquiryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
	}
}




