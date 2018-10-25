package teamCreditProjectApp.entity;

public class Payment {

	private int id;
	private String cardNumber;
	private String startDate;
	private String endDate;
	private double minPayment;
	private double actualPayment;
	
	
	public Payment(int id, String cardNumber, String startDate, String endDate, double minPayment,
			double actualPayment) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.minPayment = minPayment;
		this.actualPayment = actualPayment;
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
	public double getMinPayment() {
		return minPayment;
	}
	public void setMinPayment(double minPayment) {
		this.minPayment = minPayment;
	}
	public double getActualPayment() {
		return actualPayment;
	}
	public void setActualPayment(double actualPayment) {
		this.actualPayment = actualPayment;
	}
	
	
	
	
	
	
}
