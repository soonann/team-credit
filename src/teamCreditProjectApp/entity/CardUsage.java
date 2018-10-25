package teamCreditProjectApp.entity;

public class CardUsage {
	private int id;
	private String cardNumber;
	private double usageLimit;
	private String dateEffective;
	
	
	
	public CardUsage(int id, String cardNumber, double usageLimit, String dateEffective) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.usageLimit = usageLimit;
		this.dateEffective = dateEffective;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public double getUsageLimit() {
		return usageLimit;
	}
	public void setUsageLimit(double usageLimit) {
		this.usageLimit = usageLimit;
	}
	public String getDateEffective() {
		return dateEffective;
	}
	public void setDateEffective(String dateEffective) {
		this.dateEffective = dateEffective;
	}
	
	
	
}
