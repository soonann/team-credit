package teamCreditProjectApp.entity;

public class Login {

	private String singpassID;
	private String password;
	private String userName;
	
	public Login(){
		
	}
	
	
	public Login(String singpassID, String password, String userName) {
		super();
		this.singpassID = singpassID;
		this.password = password;
		this.userName = userName;
	}
	
	
	public String getSingpassID() {
		return singpassID;
	}
	public void setSingpassID(String singpassID) {
		this.singpassID = singpassID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
	
}
