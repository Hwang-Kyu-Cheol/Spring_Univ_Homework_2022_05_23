package examples.spmvc.lec9.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examples.spmvc.lec9.Domain.Member;
import examples.spmvc.lec9.Exception.AlreadyExistedEmailException;
import examples.spmvc.lec9.Exception.AlreadyExistedIdException;
import examples.spmvc.lec9.Exception.AlreadyExistedPhoneNumberException;
import examples.spmvc.lec9.Form.LoginForm;
import examples.spmvc.lec9.Repository.MemberRepository;

@Service
public class MemberService {
	
	private final MemberRepository memberRepository;

	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public void signup(Member member) throws AlreadyExistedIdException, AlreadyExistedEmailException, AlreadyExistedPhoneNumberException {
		
		if(memberRepository.findById(member.getId()) != null) {
			throw new AlreadyExistedIdException();
		}
		
		if(memberRepository.findByEmail(member.getEmail()) != null) {
			throw new AlreadyExistedEmailException();
		}
		
		if(memberRepository.findByPhoneNumber(member.getPhoneNumber()) != null) {
			throw new AlreadyExistedPhoneNumberException();
		}
		
		memberRepository.create(member);
	}
	
	public boolean login(LoginForm loginForm) {
		Member member = memberRepository.findById(loginForm.getUserId());
		
		if(member != null && member.getPassword().equals(loginForm.getPwd())) {
			return true;
		}
		return false;
	}
	
	public String getMemberName(String id) {
		return memberRepository.findById(id).getName();
	}
	
	public Member getMemberById(String id) {
		return memberRepository.findById(id);
	}
	
	public void modify(String id, Member member) throws AlreadyExistedEmailException, AlreadyExistedPhoneNumberException {
		
		Member currentMember = memberRepository.findById(id);
		
		if(memberRepository.findByEmail(member.getEmail()) != null && !currentMember.getEmail().equals(member.getEmail())) {
			throw new AlreadyExistedEmailException();
		}
		
		if(memberRepository.findByPhoneNumber(member.getPhoneNumber()) != null && !currentMember.getPhoneNumber().equals(member.getPhoneNumber())) {
			throw new AlreadyExistedPhoneNumberException();
		}
		
		memberRepository.update(id, member);
	}
	
	public void withdraw(String id) {
		memberRepository.delete(id);
	}
}
