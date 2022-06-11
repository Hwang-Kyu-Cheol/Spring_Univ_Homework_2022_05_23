package examples.spmvc.lec9.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examples.spmvc.lec9.Domain.Item;
import examples.spmvc.lec9.Domain.Member;
import examples.spmvc.lec9.Domain.Order;
import examples.spmvc.lec9.Repository.ItemRepository;
import examples.spmvc.lec9.Repository.MemberRepository;
import examples.spmvc.lec9.Repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	@Autowired
	public OrderService(OrderRepository orderRepository, MemberRepository memberRepository, ItemRepository itemRepository) {
		this.orderRepository = orderRepository;
		this.memberRepository = memberRepository;
		this.itemRepository = itemRepository;
	}
	
	public void order(String memberId, int itemId) {
		
		Member member = memberRepository.findById(memberId);
		Item item = itemRepository.findById(itemId);
		
		orderRepository.create(member, item);
	}
	
	public ArrayList<Order> getOrdersByMemberId(String memberId){
		
		Member member = memberRepository.findById(memberId);
		
		return orderRepository.findByMember(member);
	}
}
