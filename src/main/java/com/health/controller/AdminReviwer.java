package com.health.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.health.model.Category;
import com.health.model.Tutorial;
import com.health.model.User;
import com.health.model.language;
import com.health.model.topic;
import com.health.repository.CategoryDao;
import com.health.repository.TutorialDao;
import com.health.repository.UserRepository;
import com.health.repository.languagedao;
import com.health.service.tutorialService;

@Controller
public class AdminReviwer 
{

	@Autowired
	private  TutorialDao tutorialdao;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private com.health.repository.topicRepositary topicRepositary;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private languagedao languageDao;
	
	@Autowired
	private tutorialService tutorialService;
	
	
	@RequestMapping("/listTutorialAdminReviwer")
	public String listTutorialAdmin(Model model) 
	{	
		
		List<Tutorial> tutorial=(List<Tutorial>) tutorialdao.finByVideoStatus();
	

		model.addAttribute("AdminListTutorias",tutorial);
	
		return "listTutorialAdminReviwer";

		
	}
	
	@RequestMapping("/adminreview/review/{id}")
	public String componenettutorialReview(@PathVariable Integer id, Model model,HttpServletRequest req) 
	{	
		
		
					Tutorial tutorial=tutorialdao.findOne(id);

						if(tutorial.getOutlineStatus()==0)
						{		
							model.addAttribute("statusOutline","Pending");

						}	
						else if(tutorial.getOutlineStatus()==1)
						{ 
					
							model.addAttribute("statusOutline","Wating for Domain Review");
							model.addAttribute("statusOutlineTrue",true);
						} 
						
						if(tutorial.getScriptStatus()==0) 
						{
							
							model.addAttribute("statusScript", "Pending");
							

						}else if (tutorial.getScriptStatus()==1) 								
						{

							model.addAttribute("statusScript","Wating for Domain Review");
							model.addAttribute("statusScriptTrue", true);
							
						}
						
						if(tutorial.getSlideStatus()==0) 
						{
							
							model.addAttribute("statusSlide", "PelistTutorialAdminReviwernding");

						}else if (tutorial.getSlideStatus()==1) 
						
						{

							model.addAttribute("statusSlide","Wating for Domain Review");
							model.addAttribute("statsuSlideTrue", true);
							
						}
						
						if(tutorial.getVideoStatus()==0) 
						{
							
							model.addAttribute("statusVideo", "Pending");

						}else if (tutorial.getVideoStatus()==1) 
						
						{

							model.addAttribute("statusVideo","Wating for Domain Review");
							model.addAttribute("statusVideoTrue", true);
							
						}
					
						if(tutorial.getKeywordStatusSet()==0)
						{
							
							model.addAttribute("statusKeyword", "Pending");

						}else if (tutorial.getKeywordStatusSet()==1) 
						
						{
							model.addAttribute("statusKeyword","Wating for Domain Review");	
							model.addAttribute("statusKeywordTrue", true);
						}
						
						model.addAttribute("tutorialComponenet",tutorial);	
		
						
			return "addContentAdminReview";

	}
		@RequestMapping("/viewVideoAdmin")
	  public @ResponseBody List<String> viewVideoContentAdmin(@RequestParam(value = "categorname") String categorname,
			  @RequestParam(value = "topicid") String topicid,
			  @RequestParam(value = "lanId") String lanId,Model model,Authentication authentication)
			  
	 {
	    
		  List<String> videoframe = new ArrayList<String>();
		  
		  User user=userRepository.findByUsername(authentication.getName());
		  
		  topic topic = topicRepositary.findBytopicname(topicid);		  
		  
		  Category category=categoryDao.findBycategoryname(categorname);
	
		  language language=languageDao.findBylanguageName(lanId);
	
		  int status = 1;
		  
		
		Tutorial tutorial=tutorialdao.finByKeywordContentDomain(topic,category, language);
			
		  videoframe.add(tutorial.getVideo());
		   
		return videoframe;
		  
	 }
	/*
	 * here is code Video accept
	 */
		
		  @RequestMapping("/acceptAdminVideo")
		  public @ResponseBody List<String> acceptVideoReviewr(@RequestParam(value = "categorname") String categorname,
				  @RequestParam(value = "topicid") String topicid,
				  @RequestParam(value = "lanId") String lanId,Model model,Authentication authentication)
		  
		{
			
			  List<String> videoStatusUpdate = new ArrayList<String>();
			  
			  User user=userRepository.findByUsername(authentication.getName());
			  
			  topic topic = topicRepositary.findBytopicname(topicid);		  
			  
			  Category category=categoryDao.findBycategoryname(categorname);
		
			  language language=languageDao.findBylanguageName(lanId);
		  
			  int AdminStatus=2;
			  
			  tutorialService.updateVideoStatusByAdmin(AdminStatus, topic, category,language);
			  
			  videoStatusUpdate.add("Video Status Update Succefully");
			  
			  return videoStatusUpdate;

		}
		  	/* here code need to improvements */
		  
		  @RequestMapping("/needToImprovemenetByAdmin")
		  public @ResponseBody List<String> needToImprovemenetByAdmin(@RequestParam(value = "categorname") String categorname,
				  @RequestParam(value = "topicid") String topicid,
				  @RequestParam(value = "lanId") String lanId,Model model,Authentication authentication)	  
		{
			
			  List<String> videoStatusUpdate = new ArrayList<String>();
			  
			  User user=userRepository.findByUsername(authentication.getName());
			  
			  topic topic = topicRepositary.findBytopicname(topicid);		  
			  
			  Category category=categoryDao.findBycategoryname(categorname);
		
			  language language=languageDao.findBylanguageName(lanId);
		  
			  //Admin set to need to improvement
			  
			  int AdminStatus=5;
			  
			  tutorialService.updateVideoStatusByAdmin(AdminStatus, topic, category,language);
			  
			  videoStatusUpdate.add("Video Staus Update UpadateSuccefully");
			  
			  return videoStatusUpdate;

		}
		  
		  
		  
		
		
		

	
	
}
