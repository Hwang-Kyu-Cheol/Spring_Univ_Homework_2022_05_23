package examples.spmvc.lec9;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String home(Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		if(session != null) {
			
			LoginInfo loginInfo = (LoginInfo)session.getAttribute("authInfo");
			if(loginInfo == null) {
				return "home";
			}
			
			String userName = "";
			
			for(Member item: memberList) {
				if(item.getId().equals(loginInfo.getUserId())) {
					userName = item.getName();
					break;
				}
			}
			
			model.addAttribute("userName", userName);
			model.addAttribute("userId", loginInfo.getUserId());
		}
		
		return "home";
	}
	
	@GetMapping("/signup")
	public String signup(Member member) {
		return "signup";
	}
	
	@PostMapping("/signup/submit")
	public String signupSubmit(@Valid Member member, Errors errors) {
		
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
			return "signup";
		}
		
		memberList.add(member);
		return "signupSubmit";
	}
	
	@GetMapping("/login")
	public String signup(LoginInfo loginInfo, HttpServletRequest req, @CookieValue(value="REMEMBERID", required=false) Cookie rCookie) {
		HttpSession session = req.getSession();
		
		if(session == null || session.getAttribute("authInfo") == null) {
			if(rCookie != null) {
				loginInfo.setUserId(rCookie.getValue());
				loginInfo.setRememberid(true);
			}
		}
		
		return "login";
	}

	@PostMapping("/login/submit")
	public String loginSubmit(@Valid LoginInfo loginInfo, Errors errors, HttpServletRequest req, HttpServletResponse res) {
		
		boolean canLogin = false;
		
		for(Member item: memberList) {
			if(item.getId().equals(loginInfo.getUserId()) && item.getPassword().equals(loginInfo.getPwd())) {
				canLogin = true;
				break;
			}
		}
		
		if(!canLogin || errors.hasErrors()) {
			return "login";
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("authInfo", loginInfo);
		
		Cookie rememberCookie = new Cookie("REMEMBERID", loginInfo.getUserId());
		rememberCookie.setPath("/");
		if(loginInfo.getRememberid()) {
			rememberCookie.setMaxAge(60*60*24*30);
		}else {
			rememberCookie.setMaxAge(0);
		}
		res.addCookie(rememberCookie);
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session != null) session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/mypage/{userId}")
	public String mypage(@PathVariable("userId") String userId, HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession();
		LoginInfo loginInfo = (LoginInfo)session.getAttribute("authInfo");
		String sessionUserId = loginInfo.getUserId();
		
		if(!sessionUserId.equals(userId)) {
			return "redirect:/";
		}
		
		for(Member item: memberList) {
			if(item.getId().equals(userId)) {
				model.addAttribute("member", item);
				break;
			}
		}
		
		return "mypage";
	}
	
	@GetMapping("/modify/{userId}")
	public String modify(@PathVariable("userId") String userId, HttpServletRequest req, Member member, Model model) {
		
		HttpSession session = req.getSession();
		LoginInfo loginInfo = (LoginInfo)session.getAttribute("authInfo");
		String sessionUserId = loginInfo.getUserId();
		
		if(!sessionUserId.equals(userId)) {
			return "redirect:/";
		}
		
		String userName = "";
		
		for(Member item: memberList) {
			if(item.getId().equals(loginInfo.getUserId())) {
				userName = item.getName();
				break;
			}
		}
		
		model.addAttribute("userName", userName);
		
		return "modify";
	}
	
	@PostMapping("/modify/submit")
	public String modifySubmit(@Valid Member member, Errors errors, HttpServletRequest req) {
		
		if(errors.hasErrors()) {
			return "modify";
		}
		
		HttpSession session = req.getSession();
		LoginInfo loginInfo = (LoginInfo)session.getAttribute("authInfo");
		String sessionUserId = loginInfo.getUserId();
		
		for(Member item: memberList) {
			if(item.getId().equals(sessionUserId)) {
				item.setId(member.getId());
				item.setPassword(member.getPassword());
				item.setName(member.getName());
				item.setAddress(member.getAddress());
				item.setEmail(member.getEmail());
				item.setPhoneNumber(member.getPhoneNumber());
				item.setBirthday(member.getBirthday());
				break;
			}
		}
		
		return "redirect:/mypage/" + sessionUserId;
	}
	
	@GetMapping("/withdraw")
	public String withdraw(HttpServletRequest req) {
		HttpSession session = req.getSession();
		LoginInfo loginInfo = (LoginInfo)session.getAttribute("authInfo");
		String sessionUserId = loginInfo.getUserId();
		
		Member removeMember = new Member();
		for(Member item: memberList) {
			if(item.getId().equals(sessionUserId)) {
				removeMember = item;
				break;
			}
		}
		memberList.remove(removeMember);
		
		if(session != null) session.invalidate();
		return "redirect:/";
	}
}
