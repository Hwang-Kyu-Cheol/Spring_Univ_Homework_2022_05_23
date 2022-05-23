package examples.spmvc.lec9;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private ArrayList<Member> memberList;
	
	public HomeController() {
		memberList = new ArrayList<Member>();
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/insertMember")
	public String insertMember(Member member) {
		return "insertMember";
	}
	
	@PostMapping("/insertMember/submit")
	public String insertMemberSubmit(@Valid Member member, Errors errors) {
		
		//new MemberValidator().validate(member, errors);
		
		for(Member item: memberList) {
			if(item.getId().equals(member.getId())) {
				errors.rejectValue("id", "duplicated");
			}
			if(item.getEmail().equals(member.getEmail())) {
				errors.rejectValue("email", "duplicated");
			}
			if(item.getPhoneNumber().equals(member.getPhoneNumber())) {
				errors.rejectValue("phoneNumber", "duplicated");
			}
		}
		
		if(errors.hasErrors()) {
			return "insertMember";
		}
		
		memberList.add(member);
		return "insertMemberSubmit";
	}
	
	@GetMapping("/getMember")
	public String getMember() {
		return "getMember";
	}
	
	@GetMapping("/getMember/id")
	public String getMemberById(IdInput idInput) {
		return "getMemberById";
	}
	
	@PostMapping("/getMember/id/submit")
	public String getMemberByIdSubmit(@ModelAttribute IdInput idInput, Errors errors, Model model) {
		
		String id = idInput.getId();
		boolean isExist = false;
		
		for(Member item: memberList) {
			if(item.getId().equals(id)) {
				model.addAttribute("id", item.getId());
				model.addAttribute("name", item.getName());
				model.addAttribute("email", item.getEmail());
				model.addAttribute("address", item.getAddress());
				model.addAttribute("phoneNumber", item.getPhoneNumber());
				isExist = true;
			}
		}
		
		if(!isExist) {
			errors.rejectValue("id", "none");
		}
		
		if(errors.hasErrors()) {
			return "getMemberById";
		}
		
		return "getMemberByIdSubmit";
	}
	
	@GetMapping("/getMember/email")
	public String getMemberByEmail(EmailInput emailInput) {
		return "getMemberByEmail";
	}
	
	@PostMapping("/getMember/email/submit")
	public String getMemberByEmailSubmit(@ModelAttribute EmailInput emailInput, Errors errors, Model model) {
		
		String email = emailInput.getEmail();
		boolean isExist = false;
		
		for(Member item: memberList) {
			if(item.getEmail().equals(email)) {
				model.addAttribute("id", item.getId());
				model.addAttribute("name", item.getName());
				model.addAttribute("email", item.getEmail());
				model.addAttribute("address", item.getAddress());
				model.addAttribute("phoneNumber", item.getPhoneNumber());
				isExist = true;
			}
		}
		
		if(!isExist) {
			errors.rejectValue("email", "none");
		}
		
		if(errors.hasErrors()) {
			return "getMemberByEmail";
		}
		
		return "getMemberByEmailSubmit";
	}
	
	@GetMapping("/getMember/phoneNumber")
	public String getMemberByPhoneNumber(PhoneNumberInput phoneNumberInput) {
		return "getMemberByPhoneNumber";
	}
	
	@PostMapping("/getMember/phoneNumber/submit")
	public String getMemberByPhoneNumberSubmit(@ModelAttribute PhoneNumberInput phoneNumberInput, Errors errors, Model model) {
		
		String phoneNumber = phoneNumberInput.getPhoneNumber();
		boolean isExist = false;
		
		for(Member item: memberList) {
			if(item.getPhoneNumber().equals(phoneNumber)) {
				model.addAttribute("id", item.getId());
				model.addAttribute("name", item.getName());
				model.addAttribute("email", item.getEmail());
				model.addAttribute("address", item.getAddress());
				model.addAttribute("phoneNumber", item.getPhoneNumber());
				isExist = true;
			}
		}
		
		if(!isExist) {
			errors.rejectValue("phoneNumber", "none");
		}
		
		if(errors.hasErrors()) {
			return "getMemberByPhoneNumber";
		}
		
		return "getMemberByPhoneNumberSubmit";
	}
}
