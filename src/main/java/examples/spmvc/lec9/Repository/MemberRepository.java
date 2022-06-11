package examples.spmvc.lec9.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import examples.spmvc.lec9.Domain.Member;

@Repository
public class MemberRepository {

	private static ArrayList<Member> memberList = new ArrayList<Member>();
	
	public MemberRepository() {
		Member member1 = new Member();
		member1.setId("id1");
		member1.setPassword("password1");
		member1.setName("황규철");
		member1.setEmail("iambigman97@naver.com");
		member1.setAddress("양천구 목동");
		member1.setPhoneNumber("01030722971");
		member1.setBirthday(LocalDate.of(1997, 1, 8));
		memberList.add(member1);
	}
	
	public Member create(Member member) {
		memberList.add(member);
		return member;
	}
	
	public Member findById(String id) {
		
		for(Member item: memberList) {
			if(item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}

	public Member findByEmail(String email) {
		
		for(Member item: memberList) {
			if(item.getEmail().equals(email)) {
				return item;
			}
		}
		return null;
	}
	
	public Member findByPhoneNumber(String phoneNumber) {
		
		for(Member item: memberList) {
			if(item.getPhoneNumber().equals(phoneNumber)) {
				return item;
			}
		}
		return null;
	}
	
	public Member update(String id, Member member) {
		for(Member item: memberList) {
			if(item.getId().equals(id)) {
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
		
		return member;
	}
	
	public void delete(String id) {
		for(Member item: memberList) {
			if(item.getId().equals(id)) {
				memberList.remove(item);
				break;
			}
		}
	}
}
