package examples.spmvc.lec9.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import examples.spmvc.lec9.Domain.Item;
import examples.spmvc.lec9.Form.LoginForm;
import examples.spmvc.lec9.Service.ItemService;

@Controller
public class ItemController {

	private final ItemService itemService;
	
	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@GetMapping("/item")
	public String item(Model model) {
		
		ArrayList<Item> itemList = itemService.getAllItems();
		
		model.addAttribute("itemList", itemList);
		
		return "item";		
	}
	
	@GetMapping("/item/{itemId}")
	public String itemDetail(@PathVariable("itemId") int itemId, HttpServletRequest req, Model model) {
		
		Item item = itemService.getItemById(itemId);
		model.addAttribute("item", item);
		
		HttpSession session = req.getSession();
		if(!(session == null || session.getAttribute("authInfo") == null)) {
			LoginForm loginForm = (LoginForm)session.getAttribute("authInfo");
			model.addAttribute("userId", loginForm.getUserId());
		}
		
		return "itemDetail";
	}
	
	@GetMapping("/item/search")
	public String searchItem(HttpServletRequest req, Model model) {
		
		String itemName = req.getParameter("name");
		
		if(itemName == null) {
			return "searchItem";
		} else if(itemName.trim().isEmpty()) {
			return "redirect:/item/search";
		} else {
			ArrayList<Item> itemList = itemService.getItemsByName(itemName);
			
			model.addAttribute("itemList", itemList);
			
			return "searchItem";
		}
	}
}
