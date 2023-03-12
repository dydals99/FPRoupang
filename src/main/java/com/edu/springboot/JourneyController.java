package com.edu.springboot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springboot.jdbc.CategoryService;
import com.edu.springboot.jdbc.IReviewService;
import com.edu.springboot.jdbc.JourneyDTO;
import com.edu.springboot.jdbc.JourneyInfoDTO;
import com.edu.springboot.jdbc.JourneyService;
import com.edu.springboot.jdbc.ParameterTicketDTO;
import com.edu.springboot.jdbc.TicketDTO;
import com.edu.springboot.jdbc.TicketInfoDTO;
import com.edu.springboot.jdbc.TotalJourneyDTO;
import com.edu.springboot.jdbc.TotalTicketDTO;

@Controller
public class JourneyController {
	
	@Autowired
	CategoryService cate_dao;
	
	@Autowired
	JourneyService journey_dao;
	
	@RequestMapping("/journey_Main")
	public String joureny_Main() {
		
		return "/journey/journeyMain";
	}
 	
	@RequestMapping("/journey_insert")
	public String journey_insert1(Model model, HttpServletRequest req) {
		int sub_idx=0;
		if(!(req.getParameter("sub_idx")==null)) {
			sub_idx = Integer.parseInt(req.getParameter("sub_idx"));
		}
		model.addAttribute("cate",cate_dao.select_cate(sub_idx));
		return "/journey/journey_insert_bakup";
	}
	@RequestMapping("/journey_edit")
	public String journey_edit(Model model, HttpServletRequest req) {
		int ti_idx = Integer.parseInt(req.getParameter("product_idx"));
		JourneyInfoDTO journeyDetail = journey_dao.select_journey_info(ti_idx);
		SimpleDateFormat setDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date utilDate1 = (java.util.Date)setDate.parse(journeyDetail.getJi_duetime1());
			java.util.Date utilDate2 = (java.util.Date)setDate.parse(journeyDetail.getJi_duetime2());
			
			model.addAttribute("duetime1",setDate.format(utilDate1));
			model.addAttribute("duetime2",setDate.format(utilDate2));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("journeyDetail",journeyDetail);
		return "/journey/journey_info_edit";
	}
	
	@RequestMapping("/journeyinfo_editAction")
	public String journey_editAction(MultipartHttpServletRequest req, MultipartFile[] sub_ji_image)throws Exception{
		int product_idx = Integer.parseInt(req.getParameter("product_idx"));
		JourneyInfoDTO dto = new JourneyInfoDTO();
		JourneyInfoDTO delete_dto = journey_dao.journeyinfo_image(product_idx);
		deleteFile(delete_dto.getJi_image1());
		deleteFile(delete_dto.getJi_image2()); 
		deleteFile(delete_dto.getJi_image3());
		deleteFile(delete_dto.getJi_image4());
		int index=1;
		for(MultipartFile f: sub_ji_image) {
			if(!(f.getOriginalFilename().equals(""))) {
				String imgName = saveFile(f);
				switch(index) {
					case 1:dto.setJi_image1(imgName); break;
					case 2:dto.setJi_image2(imgName); break;
					case 3:dto.setJi_image3(imgName); break;
					case 4:dto.setJi_image4(imgName); break;
				}
				index++;
			}else {
				break;
			}
		}
		dto.setBot_idx(product_idx);
		dto.setJi_duetime1(req.getParameter("ji_duetime1"));
		dto.setJi_duetime2(req.getParameter("ji_duetime2"));
		dto.setJi_price(Integer.parseInt(req.getParameter("ji_price")));
		dto.setJi_title(req.getParameter("ji_title"));
		dto.setJi_adult(Integer.parseInt(req.getParameter("ji_adult")));
		dto.setJi_kid(Integer.parseInt(req.getParameter("ji_kid")));
		dto.setJi_roomnum(Integer.parseInt(req.getParameter("ji_roomnum")));
		dto.setJi_intro(req.getParameter("ji_intro"));
		
		journey_dao.update_journey_info(dto);
		
		return "/home";
	}
	
	@ResponseBody
	@RequestMapping("/editJourney")
	public ModelAndView edit_journey(HttpServletRequest req) {
		int value = Integer.parseInt(req.getParameter("value"));
		ModelAndView mv = new ModelAndView();
		
		JourneyDTO dto = journey_dao.journey_list(value);
		
		mv.addObject("dto",dto);
		mv.setViewName("/journey/journey_edit");
		return mv;
	}
	@RequestMapping("/editJourneyAction")
	public String edit_journey_action(MultipartFile[] sub_image, MultipartFile title_image, Model model, MultipartHttpServletRequest req) throws Exception { 

		int value = Integer.parseInt(req.getParameter("value"));

		JourneyDTO dto = new JourneyDTO();
		JourneyDTO delete_dto = journey_dao.journey_image(value);
		System.out.println(title_image.getOriginalFilename());
		if(!(title_image.getOriginalFilename().equals(""))) {
			dto.setJ_title_image(saveFile(title_image));
			deleteFile(delete_dto.getJ_title_image());
		}
		deleteFile(delete_dto.getJ_image1());
		deleteFile(delete_dto.getJ_image2());
		deleteFile(delete_dto.getJ_image3());
		deleteFile(delete_dto.getJ_image4());
		int index=1;
		for(MultipartFile f: sub_image) {
			if(!(f.getOriginalFilename().equals(""))) {
				String imgName = saveFile(f);
				switch(index) {
					case 1:dto.setJ_image1(imgName); break;
					case 2:dto.setJ_image2(imgName); break;
					case 3:dto.setJ_image3(imgName); break;
					case 4:dto.setJ_image4(imgName); break;
				}
				index++;
			}else {
				break;
			}
		}
		dto.setBot_idx(value);
		dto.setLocation(req.getParameter("location"));
		dto.setJ_intro(req.getParameter("j_intro"));
		dto.setNotice(req.getParameter("notice"));
		dto.setTraffic_info(req.getParameter("traffic_info"));
		dto.setLoging_policy(req.getParameter("loging_policy"));
		dto.setCheck_io(req.getParameter("check_io"));
		dto.setJ_booking(req.getParameter("j_booking"));
		dto.setJ_notice(req.getParameter("j_notice"));
		dto.setAdd_fare(req.getParameter("add_fare"));
		dto.setAdd_bed(req.getParameter("add_bed"));
		dto.setBreakfast_noti(req.getParameter("breakfast_noti"));
		dto.setJ_cancelfee(req.getParameter("j_cancelfee"));
		dto.setJ_cancelnoti(req.getParameter("j_cancelnoti"));
		
		String[] chkCommon = req.getParameterValues("common_items");
		String CommonVal = "";
		for(int i = 0; i < chkCommon.length ; i++) {
			CommonVal += chkCommon[i];
			if(i!=chkCommon.length-1) {
				CommonVal += ",";
			}
		}
		dto.setCommon_items(CommonVal);
		
		String[] chkService = req.getParameterValues("j_conservice");
		String ServiceVal = "";
		for(int i = 0; i < chkService.length ; i++) {
		 	ServiceVal += chkService[i];
			if(i!=chkService.length-1) {
					 ServiceVal += ",";
			}
		}
		dto.setJ_conservice(ServiceVal);
		
		String[] chkfac=req.getParameterValues("j_confacility");
		String facVal = "";
		for(int i = 0 ; i < chkfac.length ; i++) {
				facVal += chkfac[i];
				if(i!=chkfac.length-1) {
					facVal += ",";
				}
		}
		dto.setJ_confacility(facVal);
		
		
		journey_dao.update_journey(dto);
			 
		return "/home";
	}
	public String saveFile(MultipartFile file) {
		UUID uid = UUID.randomUUID();
		String saveName = uid + "_" + file.getOriginalFilename();
		
		String path ="";
		try {
			path = ResourceUtils.getFile("classpath:static/uploads/")
					.toPath().toString(); 
		}catch (Exception e) {}
		
		File fileinfo = new File(path,saveName);
		
		try {
			file.transferTo(fileinfo);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return saveName;
	}
	public String deleteFile(String FileName) {
		
		try {
			String path = ResourceUtils.getFile("classpath:static/uploads/")
					.toPath().toString();
			
			File delete_file = new File(path + File.separator + FileName);
			
			if(delete_file.exists()) {
				delete_file.delete();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		return "success";
	}
	@ResponseBody
	@RequestMapping("/category_list.j")
	public ArrayList<ParameterTicketDTO> cate_list(HttpServletRequest req){
//		System.out.println("실행");
		int sub_idx=0;
		if(!(req.getParameter("sub_idx")==null)) {
			sub_idx=Integer.parseInt(req.getParameter("sub_idx"));
		}
		String level = req.getParameter("level");
//		System.out.println(level);
		if(level.equals("1")) {
			ArrayList<ParameterTicketDTO> sub_cate = cate_dao.select_cate(sub_idx);
			return sub_cate;
		}else{
			String company_name=req.getParameter("company_name");
			ArrayList<ParameterTicketDTO> sub_cate = cate_dao.select_cate_bot_j(sub_idx,company_name);
			return sub_cate;
		}
	}
	@ResponseBody
	@RequestMapping("/journeyInfo")
	public JourneyDTO select_journey(HttpServletRequest req){
		int bot_idx = Integer.parseInt(req.getParameter("bot_idx"));
		System.out.println(bot_idx);
		JourneyDTO journey = journey_dao.journey_list(bot_idx);
		return journey;
	}
	@ResponseBody
	@RequestMapping("/journeyDetailInfo")
	public ArrayList<JourneyInfoDTO> select_journey_list(HttpServletRequest req){
		int bot_idx = Integer.parseInt(req.getParameter("bot_idx"));
		ArrayList<JourneyInfoDTO> journeyDetail = journey_dao.journey_info_list(bot_idx);
		return journeyDetail;
	}
	@RequestMapping("/journeyInsertAction")
	public ModelAndView journey_insert2(MultipartFile[] sub_image, MultipartFile title_image, MultipartFile[] sub_ji_image,
			Model model, MultipartHttpServletRequest req) throws Exception {
		ModelAndView mv = new ModelAndView();
		int value= Integer.parseInt(req.getParameter("value"));		
		JourneyDTO j_dto = new JourneyDTO();
		if(value==0) {
			journey_dao.insert_bot_title(req.getParameter("bot_title"),
					Integer.parseInt(req.getParameter("mid_category")),
					req.getParameter("company_name"));
			
			j_dto.setJ_title_image(saveFile(title_image));
			int index=1;
			for(MultipartFile f: sub_image) {
				String imgName = saveFile(f);
				switch(index) {
					case 1:j_dto.setJ_image1(imgName); break;
					case 2:j_dto.setJ_image2(imgName); break;
					case 3:j_dto.setJ_image3(imgName); break;
					case 4:j_dto.setJ_image4(imgName); break;
				}
				index++;
			}
			
			String[] chkService = req.getParameterValues("j_conservice");
			String ServiceVal = "";
			for(int i = 0 ; i < chkService.length ; i++) {
				ServiceVal += chkService[i];
				if(i!=chkService.length-1) {
					ServiceVal += ",";
				}
			}
			j_dto.setJ_conservice(ServiceVal);
			
			String[] chkfac= req.getParameterValues("j_confacility");
			String facVal = "";
			for(int i = 0 ; i < chkfac.length ; i++) {
				facVal += chkfac[i];
				if(i!=chkfac.length-1) {
					facVal += ",";
				}
			}
			j_dto.setJ_confacility(facVal);
			
			String[] chkcom= req.getParameterValues("common_items");
			String comVal = "";
			for(int i = 0 ; i < chkcom.length ; i++) {
				comVal += chkcom[i];
				if(i!=chkcom.length-1) {
					comVal += ",";
				}
			}
			j_dto.setCommon_items(comVal);
			
			j_dto.setLocation(req.getParameter("location"));
			j_dto.setJ_intro(req.getParameter("j_intro"));
			j_dto.setNotice(req.getParameter("notice"));
			j_dto.setTraffic_info(req.getParameter("traffic_info"));
			j_dto.setLoging_policy(req.getParameter("loging_policy"));
			j_dto.setCheck_io(req.getParameter("check_io"));
			j_dto.setJ_booking(req.getParameter("j_booking"));
			j_dto.setAdd_fare(req.getParameter("add_fare"));
			j_dto.setAdd_bed(req.getParameter("add_bed"));
			j_dto.setBreakfast_noti(req.getParameter("breakfast_noti"));
			j_dto.setJ_notice(req.getParameter("j_notice"));
			j_dto.setJ_cancelfee(req.getParameter("j_cancelfee"));
			j_dto.setJ_cancelnoti(req.getParameter("j_cancelnoti"));

			value=journey_dao.select_new_idx();
		
		}
		try {
		JourneyInfoDTO ji_dto = new JourneyInfoDTO();
		ji_dto.setBot_idx(value);
		ji_dto.setJi_duetime1(req.getParameter("ji_duetime1"));
		ji_dto.setJi_duetime2(req.getParameter("ji_duetime2"));
		ji_dto.setJi_price(Integer.parseInt(req.getParameter("ji_price")));
		ji_dto.setJi_title(req.getParameter("ji_title"));
		ji_dto.setJi_adult(Integer.parseInt(req.getParameter("ji_adult")));
		ji_dto.setJi_kid(Integer.parseInt(req.getParameter("ji_kid")));
		ji_dto.setJi_roomnum(Integer.parseInt(req.getParameter("ji_roomnum")));
		ji_dto.setJi_intro(req.getParameter("ji_intro"));
		int index=1;
		for(MultipartFile f: sub_ji_image) {
			String imgName = saveFile(f);
			switch(index) {
				case 1:ji_dto.setJi_image1(imgName); break;
				case 2:ji_dto.setJi_image2(imgName); break;
				case 3:ji_dto.setJi_image3(imgName); break;
				case 4:ji_dto.setJi_image4(imgName); break;
			}
			index++;
		}
		journey_dao.insert_journey_info(ji_dto);
		}
		catch (Exception e) {}
		
		if(!(req.getParameter("j_intro").equals(""))) {
			journey_dao.insert_journey(j_dto);
		}
		mv.setViewName("/home");
		return mv;
	}
	@RequestMapping("/all_delete_journey")
	public String all_delete(HttpServletRequest req) {
		String company_name = req.getParameter("company_name");
		String value = req.getParameter("value");
		String[] list = value.split(",");
		List<String> val = new ArrayList<String>();
		
		for(int i = 0 ; i < list.length ; i++) {
			val.add(list[i]);
		}
		
		ArrayList<JourneyDTO> total_image = journey_dao.journey_Total_image(val);
		ArrayList<JourneyInfoDTO> total_ji_image = journey_dao.journeyinfo_Total_image(val);
		ArrayList<String> image = new ArrayList<String>();
		ArrayList<String> image_ji = new ArrayList<String>();
		
		for(int i = 0 ; i < total_image.size(); i++) {
			JourneyDTO dto = total_image.get(i);
			image.add(dto.getJ_title_image());
			if(dto.getJ_image1()!=null) {
				image.add(dto.getJ_image1());
				if(dto.getJ_image2()!=null) {
					image.add(dto.getJ_image2());
					if(dto.getJ_image3()!=null) {
						image.add(dto.getJ_image3());
						if(dto.getJ_image4()!=null) {
							image.add(dto.getJ_image4());
						}
					}
				}
			}
		}
		for(int i = 0 ; i < image.size(); i++) {
			deleteFile(image.get(i));
		}
		for(int i = 0 ; i < total_image.size(); i++) {
			JourneyInfoDTO dto = total_ji_image.get(i);
			if(dto.getJi_image1()!=null) {
				image_ji.add(dto.getJi_image1());
				if(dto.getJi_image2()!=null) {
					image_ji.add(dto.getJi_image2());
					if(dto.getJi_image3()!=null) {
						image_ji.add(dto.getJi_image3());
						if(dto.getJi_image4()!=null) {
							image_ji.add(dto.getJi_image4());
						}
					}
				}
			}
		}
		for(int i = 0 ; i < image_ji.size(); i++) {
			deleteFile(image_ji.get(i));
		}
		
		journey_dao.delete_journey(val);
		journey_dao.alldelete_journey_info(val);
		journey_dao.delete_bot_category_j(val, company_name);
		
		return "/home";
	}
	
	@RequestMapping("/detail_delete_journey")
	public String delete(HttpServletRequest req) {
		String company_name = req.getParameter("company_name");
		String value = req.getParameter("value");
		
		String[] list = value.split(",");
		List<String> val = new ArrayList<String>();
		
		for(int i = 0 ; i < list.length ; i++) {
			val.add(list[i]);
		}
		
		journey_dao.delete_journey_info(val, company_name);
		return "/home";
	}
	@RequestMapping("/journey_List")
	public ModelAndView show_Journey_List(HttpServletRequest req, HttpSession session, TotalJourneyDTO totaljourneyDTO) {
		ModelAndView mv = new ModelAndView();
		int sub_idx = Integer.parseInt(req.getParameter("category"));
		String location = req.getParameter("location"); 
		String title = req.getParameter("title");
		String ji_duetime1 = "";
		String ji_duetime2 = "";
		int ji_adult = 2;
		int ji_kid = 0;
		
		if(location != null) {
			
			String like_loc = journey_dao.like_journey_List(location);
		
			mv.addObject("like_loc",like_loc);
		}
		else if(title != null) {
			
			String search_list = cate_dao.search_journey_List(sub_idx, title);
			
			mv.addObject("search_list",search_list);
		}
	
		
		if(session.getAttribute("ji_duetime1")!= null) {
			totaljourneyDTO.setJi_duetime1((String)session.getAttribute("ji_duetime1"));
			totaljourneyDTO.setJi_duetime2((String)session.getAttribute("ji_duetime2"));
		}else {
			totaljourneyDTO.setJi_duetime1(ji_duetime1);
			totaljourneyDTO.setJi_duetime2(ji_duetime2);
		}
		
		if(session.getAttribute("ji_kid") != null) {
			totaljourneyDTO.setJi_kid(Integer.parseInt((String)(session.getAttribute("ji_kid")))); 
		}else {
			totaljourneyDTO.setJi_kid(ji_kid);
		}
		if(session.getAttribute("ji_adult") != null) {
			totaljourneyDTO.setJi_kid(Integer.parseInt((String)(session.getAttribute("ji_adult")))); 
		}else {
			totaljourneyDTO.setJi_kid(ji_adult);
		}
		totaljourneyDTO.setSub_idx(sub_idx);
		
//		System.out.println("어린이 :"+ totaljourneyDTO.getJi_kid());
//		System.out.println("성인 :"+ totaljourneyDTO.getJi_adult());
//		System.out.println("시작일 :"+ totaljourneyDTO.getJi_duetime1());
//		System.out.println("종료일 :"+ totaljourneyDTO.getJi_duetime2());
		
		String category_title = cate_dao.select_one_cate(sub_idx); 
		ArrayList<TotalJourneyDTO> journey_list = journey_dao.show_journey_list(totaljourneyDTO);
		
		mv.addObject("sub_idx",sub_idx);
		mv.addObject("category_title",category_title);
		mv.addObject("journey_list", journey_list);
		mv.setViewName("/journey/journeyList");
		
		session.removeAttribute("ji_adult");
		session.removeAttribute("ji_kid");
		session.removeAttribute("ji_duetime1");
		session.removeAttribute("ji_duetime2");
		
		return mv;
	}
	//인원 설정
	@RequestMapping("/personnel")
	@ResponseBody
	public String personnel(HttpSession session, String ji_adult, String ji_kid, HttpServletRequest req) {
		
		session.setAttribute("ji_adult", ji_adult);
		session.setAttribute("ji_kid", ji_kid);
		System.out.println(session.getAttribute("ji_adult"));
		System.out.println(session.getAttribute("ji_kid"));
		
		return "/journey/journeyList";
	}
	//예약일 설정
	@RequestMapping(value = "/dateSave", method = RequestMethod.POST)
	@ResponseBody
	public String dateSave(HttpSession session,@RequestBody Map<String, String> map, HttpServletRequest req) {
		
	    String ji_duetime1 = map.get("ji_duetime1");
	    String ji_duetime2 = map.get("ji_duetime2"); 
		
		session.setAttribute("ji_duetime1", ji_duetime1);
		session.setAttribute("ji_duetime2", ji_duetime2);
		
		System.out.println("시작날짜"+session.getAttribute("ji_duetime1"));
		System.out.println("종료날짜"+session.getAttribute("ji_duetime2"));
		
		return "/journey/journeyList";
	}
}
