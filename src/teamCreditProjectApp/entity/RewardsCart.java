package teamCreditProjectApp.entity;

public class RewardsCart {
	
	private int id;
	private String cardNumber;
	private int itemID;
	private int quantity;
	private String dateRedeemed;
	private String itemName;
	private String itemPoints;
	private String itemPic;
	private String itemType;
	private String itemDescription;
	private String BankName;
	
	
	
	
	public RewardsCart(int id, String cardNumber, int itemID, int quantity, String dateRedeemed, String itemName, String itemPoints, String itemPic, String itemType, String itemDescription, String BankName) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.itemID = itemID;
		this.quantity = quantity;
		this.dateRedeemed = dateRedeemed;
		this.itemName = itemName;
		this.itemPoints = itemPoints;
		this.itemPic = itemPic;
		this.itemType = itemType;
		this.itemDescription = itemDescription;
		this.BankName = BankName;
	}
	
	
	public RewardsCart(String itemName, int quantity, String itemPoints) {
		this.itemName = itemName;
		this.quantity = quantity;
		this.itemPoints = itemPoints;
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
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDateRedeemed() {
		return dateRedeemed;
	}
	public void setDateRedeemed(String dateRedeemed) {
		this.dateRedeemed = dateRedeemed;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemPoints() {
		return itemPoints;
	}


	public void setItemPoints(String itemPoints) {
		this.itemPoints = itemPoints;
	}


	public String getItemPic() {
		return itemPic;
	}


	public void setItemPic(String itemPic) {
		this.itemPic = itemPic;
	}


	public String getItemType() {
		return itemType;
	}


	public void setItemType(String itemType) {
		this.itemType = itemType;
	}


	public String getItemDescription() {
		return itemDescription;
	}


	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}


	public String getBankName() {
		return BankName;
	}


	public void setBankName(String bankName) {
		BankName = bankName;
	}
	
	
	
	
}
