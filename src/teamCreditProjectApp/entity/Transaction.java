package teamCreditProjectApp.entity;

public class Transaction {
	
	private int id;
	private String cardNumber;
	private String transactionDate;
	private double transactionAmount;
	private String transactionCurrency;
	private double originalAmount;
	private double conversionRate;
	private String description;
	private String category;
	
	public Transaction(){
		
	}
	public Transaction(int id, String cardNumber, String transactionDate, double transactionAmount,
			String transactionCurrency, double originalAmount, double conversionRate,String description, String category) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.transactionCurrency = transactionCurrency;
		this.originalAmount = originalAmount;
		this.conversionRate = conversionRate;
		this.description = description;
		this.category = category;
	}
	
	
	
	
	public Transaction(int id, String cardNumber, String transactionDate, double transactionAmount,
			String description, String category) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.description = description;
		this.category = category;
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
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionCurrency() {
		return transactionCurrency;
	}
	public void setTransactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}
	public double getOriginalAmount() {
		return originalAmount;
	}
	public void setOriginalAmount(double originalAmount) {
		this.originalAmount = originalAmount;
	}
	public double getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(double conversionRate) {
		this.conversionRate = conversionRate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategory() {
		return category;
	}




	public void setCategory(String category) {
		this.category = category;
	}

	
	
	
}
