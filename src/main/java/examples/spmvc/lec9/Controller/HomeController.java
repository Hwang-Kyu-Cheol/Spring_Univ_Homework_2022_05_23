package examples.spmvc.lec9.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import examples.spmvc.lec9.Domain.Member;
import examples.spmvc.lec9.Exception.AlreadyExistedEmailException;
import examples.spmvc.lec9.Exception.AlreadyExistedIdException;
import examples.spmvc.lec9.Exception.AlreadyExistedPhoneNumberException;
import examples.spmvc.lec9.Form.LoginForm;
import examples.spmvc.lec9.Service.MemberService;

@Controller
public class HomeController {
	
	private final MemberService memberService;

	@Autowired
	public HomeController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/")
	public String home(Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		if(session != null) {
			
			LoginForm loginForm = (LoginForm)session.getAttribute("authInfo");
			if(loginForm == null) {
				return "home";
			}
			
			String userName = memberService.getMemberName(loginForm.getUserId());
			
			model.addAttribute("userName", userName);
			model.addAttribute("userId", loginForm.getUserId());
		}
		
		return "home";		
	}
	
	@GetMapping("/signup")
	public String signup(Member member) {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signupSubmit(@Valid Member member, Errors errors) {
		
		//Valid Error
		if(errors.hasErrors()) {
			return "signup";
		}
		
		//중복 Error
		try {
			memberService.signup(member);
		} catch(AlreadyExistedIdException e) {
			errors.rejectValue("id", "duplicated");
		} catch(AlreadyExistedEmailException e) {
			errors.rejectValue("email", "duplicated");
		} catch(AlreadyExistedPhoneNumberException e) {
			errors.rejectValue("phoneNumber", "duplicated");
		}
		
		if(errors.hasErrors()) {
			return "signup";
		}
		
		return "signupSubmit";
	}
	
	@GetMapping("/login")
	public String signup(LoginForm loginForm, HttpServletRequest req, @CookieValue(value="REMEMBERID", required=false) Cookie rCookie) {
		HttpSession session = req.getSession();
		
		if(session == null || session.getAttribute("authInfo") == null) {
			if(rCookie != null) {
				loginForm.setUserId(rCookie.getValue());
				loginForm.setRememberid(true);
			}
		}
		
		return "login";
	}
	
	@PostMapping("/login")
	public String loginSubmit(@Valid LoginForm loginForm, Errors errors, HttpServletRequest req, HttpServletResponse res) {
		
		//Valid Error
		if(errors.hasErrors()) {
			return "login";
		}
		
		if(memberService.login(loginForm)) {
			HttpSession session = req.getSession();
			session.setAttribute("authInfo", loginForm);
			
			Cookie rememberCookie = new Cookie("REMEMBERID", loginForm.getUserId());
			rememberCookie.setPath("/");
			if(loginForm.getRememberid()) {
				rememberCookie.setMaxAge(60*60*24*30);
			}else {
				rememberCookie.setMaxAge(0);
			}
			res.addCookie(rememberCookie);
			
			return "redirect:/";
		} else {
			return "login";
		}
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
		LoginForm loginForm = (LoginForm)session.getAttribute("authInfo");
		String sessionUserId = loginForm.getUserId();
		
		if(!sessionUserId.equals(userId)) {
			return "redirect:/";
		}
	
		model.addAttribute("member", memberService.getMemberById(userId));
		
		return "mypage";
	}
	
	@GetMapping("/modify/{userId}")
	public String modify(@PathVariable("userId") String userId, HttpServletRequest req, Member member, Model model) {
		
		HttpSession session = req.getSession();
		LoginForm loginForm = (LoginForm)session.getAttribute("authInfo");
		String sessionUserId = loginForm.getUserId();
		
		if(!sessionUserId.equals(userId)) {
			return "redirect:/";
		}
		
		String userName = memberService.getMemberName(userId);
		
		Member currentMember = memberService.getMemberById(userId);
		
		member.setId(currentMember.getId());
		member.setPassword(currentMember.getPassword());
		member.setName(currentMember.getName());
		member.setEmail(currentMember.getEmail());
		member.setAddress(currentMember.getAddress());
		member.setPhoneNumber(currentMember.getPhoneNumber());
		member.setBirthday(currentMember.getBirthday());
		
		model.addAttribute("userName", userName);
		
		return "modify";
	}
	
	@PostMapping("/modify")
	public String modifySubmit(@Valid Member member, Errors errors, HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession();
		LoginForm loginForm = (LoginForm)session.getAttribute("authInfo");
		String sessionUserId = loginForm.getUserId();
		
		String userName = memberService.getMemberName(sessionUserId);
		
		if(errors.hasErrors()) {
			model.addAttribute("userName", userName);
			return "modify";
		}
		
		//중복 Error
		try {
			memberService.modify(sessionUserId, member);
		} catch(AlreadyExistedEmailException e) {
			errors.rejectValue("email", "duplicated");
		} catch(AlreadyExistedPhoneNumberException e) {
			errors.rejectValue("phoneNumber", "duplicated");
		}
		
		if(errors.hasErrors()) {
			model.addAttribute("userName", userName);
			return "modify";
		}
		
		return "redirect:/mypage/" + sessionUserId;
	}
	
	@GetMapping("/withdraw")
	public String withdraw(HttpServletRequest req) {
		HttpSession session = req.getSession();
		LoginForm loginForm = (LoginForm)session.getAttribute("authInfo");
		String sessionUserId = loginForm.getUserId();
		
		memberService.withdraw(sessionUserId);
		
		if(session != null) session.invalidate();
		return "redirect:/";
	}
}
