package examples.spmvc.lec9.Form;

import javax.validation.constraints.NotEmpty;

public class LoginForm {
	
	@NotEmpty
	private String userId;
	
	@NotEmpty
	private String pwd;
	
	private Boolean rememberid;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Boolean getRememberid() {
		return rememberid;
	}

	public void setRememberid(Boolean rememberid) {
		this.rememberid = rememberid;
	}
}
