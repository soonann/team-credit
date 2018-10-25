package teamCreditProjectApp.entity;

public class Chart {

	private String categoryName ;
	private double totalAmount;
	
	public Chart(String categoryName, double totalAmount){
		
		this.categoryName = categoryName;
		this.totalAmount = totalAmount;
		
		
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
	
	
}
