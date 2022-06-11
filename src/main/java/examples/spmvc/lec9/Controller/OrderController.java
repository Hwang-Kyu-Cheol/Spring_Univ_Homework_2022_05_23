package examples.spmvc.lec9.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import examples.spmvc.lec9.Domain.Member;
import examples.spmvc.lec9.Form.LoginForm;
import examples.spmvc.lec9.Service.OrderService;

@Controller
public class OrderController {

	private final OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping("/order")
	public String order(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("authInfo") == null) {
			return "redirect:/";
		}
		
		LoginForm loginForm = (LoginForm)session.getAttribute("authInfo");
		
		String memberId = loginForm.getUserId();
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		
		orderService.order(memberId, itemId);
		
		return "redirect:/order/" + memberId;
	}
	
	@GetMapping("/order/{userId}")
	public String memberOrder(@PathVariable("userId") String userId, HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("authInfo") == null) {
			return "redirect:/";
		}
		
		LoginForm loginForm = (LoginForm)session.getAttribute("authInfo");
		
		String memberId = loginForm.getUserId();
		
		model.addAttribute("orderList", orderService.getOrdersByMemberId(memberId));
		
		return "memberOrder";
	}
}
