package examples.spmvc.lec9.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import examples.spmvc.lec9.Domain.Item;
import examples.spmvc.lec9.Domain.Member;
import examples.spmvc.lec9.Domain.Order;

@Repository
public class OrderRepository {

	private static int index = 0;
	private static ArrayList<Order> orderList = new ArrayList<Order>();
	
	public OrderRepository() {
		
	}
	
	public Order create(Member member, Item item) {
		Order order = new Order();
		order.setId(++index);
		order.setMember(member);
		order.setItem(item);
		order.setOrderDate(LocalDate.now());
		
		orderList.add(order);
		
		return order;
	}
	
	public ArrayList<Order> findByMember(Member member) {
		ArrayList<Order> orders = new ArrayList<Order>();
		
		for(Order item: orderList) {
			if(item.getMember().getId().equals(member.getId())) {
				orders.add(item);
			}
		}
		
		return orders;
	}
}
