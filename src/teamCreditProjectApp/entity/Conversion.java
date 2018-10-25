package teamCreditProjectApp.entity;

public class Conversion {

	private String currency;
	private double conversionRate;
	
	public Conversion(String currency, double conversionRate) {
		super();
		this.currency = currency;
		this.conversionRate = conversionRate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(double conversionRate) {
		this.conversionRate = conversionRate;
	}
	
	
	
	
}
