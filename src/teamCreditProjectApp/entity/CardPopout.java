package teamCreditProjectApp.entity;

public class CardPopout {

	private String cardType;
	private double transactionAmount;
	private String cardNumber;
	
	public CardPopout(String cardType, String cardNumber , double transactionAmount ) {
		super();
		this.cardType = cardType;
		this.transactionAmount = transactionAmount;
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
	
}
