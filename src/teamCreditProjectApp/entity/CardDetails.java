package teamCreditProjectApp.entity;

public class CardDetails {
	
	private int id;
	private String singpassID;
	private String cardNumber;
	private int activationStatus;
	private String startDate;
	private String endDate;
	private int rewardPoints;
	private int expiringRewardPoints;
	private String expiringRewardPointsDate;
	private String cardType;
	private double creditLimit;
	private double usageLimit;
	private String bankName;
	
	
	public CardDetails(String cardNumber, int activationStatus, String startDate, String endDate) {
		super();
		this.cardNumber = cardNumber;
		this.activationStatus = activationStatus;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public CardDetails(int id, String singpassID, String cardNumber, int activationStatus, String startDate,
			String endDate, int rewardPoints, int expiringRewardPoints, String expiringRewardPointsDate,
			String cardType, double creditLimit, double usageLimit, String bankName) {
		super();
		this.id = id;
		this.singpassID = singpassID;
		this.cardNumber = cardNumber;
		this.activationStatus = activationStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rewardPoints = rewardPoints;
		this.expiringRewardPoints = expiringRewardPoints;
		this.expiringRewardPointsDate = expiringRewardPointsDate;
		this.cardType = cardType;
		this.creditLimit = creditLimit;
		this.usageLimit = usageLimit;
		this.bankName = bankName;
	}
	
	public CardDetails(){
		
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSingpassID() {
		return singpassID;
	}
	public void setSingpassID(String singpassID) {
		this.singpassID = singpassID;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getActivationStatus() {
		return activationStatus;
	}
	public void setActivationStatus(int activationStatus) {
		this.activationStatus = activationStatus;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public int getExpiringRewardPoints() {
		return expiringRewardPoints;
	}
	public void setExpiringRewardPoints(int expiringRewardPoints) {
		this.expiringRewardPoints = expiringRewardPoints;
	}
	public String getExpiringRewardPointsDate() {
		return expiringRewardPointsDate;
	}
	public void setMonth(String expiringRewardPointsDate) {
		this.expiringRewardPointsDate = expiringRewardPointsDate;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	public double getUsageLimit() {
		return usageLimit;
	}
	public void setUsageLimit(double usageLimit) {
		this.usageLimit = usageLimit;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
	
	
	
	
	
}
