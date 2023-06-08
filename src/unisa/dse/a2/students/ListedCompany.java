package unisa.dse.a2.students;

public class ListedCompany {

	/**
	 * The full name of the company
	 */
	private String name;
	
	public String getName() {
		return name;
	}

	/**
	 * The listing code of the company
	 */
	private String code;
	
	public String getCode() {
		return code;
	}

	/**
	 * Current price of the company after last trade
	 */
	private int currentPrice;
	
	public int getCurrentPrice() {
		return currentPrice;
	}
	
	/**
	 * 
	 * @param code: company code
	 * @param name: company name
	 * @param currentPrice: current price of the company after traded
	 */
	public ListedCompany(String code, String name, int currentPrice)
	{
		this.name = name;
		this.code = code;
		this.currentPrice = currentPrice;
	}
	/**
	 * Set the currentPrice but cannot go below 1 in price.
	 * @param price
	 */
	public void setCurrentPrice(int price) {
		if (currentPrice + price > 1)
			currentPrice += price;
		else 
			currentPrice = 1;
			
		
	}
	/**
	 * Processing a trade should increase the current price of the company by 
	 *    quantity / 100
	 * A company's price CANNOT go below 1
	 * 
	 * @param quantity
	 * @return the price after adjustment
	 */
	public void processTrade(int quantity)
	{
		int price;
		price = quantity/ 100;
		setCurrentPrice(price);
	}
}
