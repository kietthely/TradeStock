package unisa.dse.a2.students;

public class Trade implements Comparable<Trade> {

	/*
	 * Don't modify the tradeID or created values, as they're used to simplify the tests
	 * You may be required to use the "created" value in some parts of your code.
	 */
	private long tradeId = -1;
	private long created;

	/**
	 * @return Track the moment in time this Trade was created
	 */
	public long getCreated()
	{
		return created;
	}
	
	public String listedCompanyCode;

	/**
	 * @return The company's code
	 */
	public String getCompanyCode() {
		return listedCompanyCode;
	}
	
	private int shareQuantity;

	/**
	 * @return The quantity of shares to trade
	 */
	public int getShareQuantity() {
		return shareQuantity;
	}

	private StockBroker broker;

	/**
	 * @return The broker associated with this trade
	 */
	public StockBroker getStockBroker() {
		return broker;
	}


	/***
	 * Do not modify this constructor, it is used for testing purposes only
	 */
	public Trade(StockBroker broker, int id)
	{
		created = System.nanoTime(); //do not change this
		tradeId = id; //do not change this
		try { Thread.sleep(100); } catch (Exception x) {
			
		}
	}
	
	/***
	 * Create a new trade with the associated broker, company, and share quantity
	 * DO NOT change the current created and tradeId code
	 * 
	 * @param broker
	 * @param listedCompanyCode
	 * @param shareQuantity
	 */
	public Trade(StockBroker broker, String listedCompanyCode, int shareQuantity)
	{
		created = System.nanoTime(); //do not change this
		tradeId = System.nanoTime(); //do not change this
		try { Thread.sleep(100); 
		this.broker = broker;
		this.listedCompanyCode = listedCompanyCode;
		this.shareQuantity = shareQuantity;
		
		} catch (Exception x) {
			
		}
	}
	
	/**
	 * Compares one trade to another trade
	 * 
	 * If we have two trades, A and B, and we examine the company in each trade:
	 *  - if A and B are BOTH on their broker's watchlists, they are equal (return 0)
	 *  - if A is on their brokers list, but B is not on B's brokers list (return 1)
	 *  - if B is on their brokers list, but A is not on A's brokers list (return -1)
	 *  - Otherwise, if neither trade is on their broker's list, then compare 
	 *  		the "created" field, returning -1 if "this" is smaller, 0 if equal, 
	 *  		or 1 if greater
	 *  
	 * @return The ordering priority of the trade
	 */
	public int compareTo(Trade t)
	{
		int out = 0;
		String otherCompany = t.getCompanyCode();
		StockBroker otherBroker = t.getStockBroker();
		DSEListGeneric<String> otherWatchList = otherBroker.getWatchlist();
		DSEListGeneric<String> currentWatchList = this.broker.getWatchlist();
		// both on their watchlists
		if (currentWatchList.contains(otherCompany) && otherWatchList.contains(getCompanyCode())) {
			out = 0;
		//currentWatchList is, but otherWatchList is not
		}else if (currentWatchList.contains(otherWatchList) && !otherWatchList.contains(otherCompany)) {
			out = 1;
		// otherWatchList is, but currentWatchList is not
		} else if (otherWatchList.contains(getCompanyCode()) && !currentWatchList.contains(getCompanyCode())) {
			out = -1;
		} else {
			if (this.getCreated() < t.getCreated()) {
				out = -1;
			} else if (this.getCreated() == t.getCreated()){
				out = 0;
			} else {
				out = 1;
			}
		}
		return out;
		
	}
	

	/***
	 * Do not modify this toString, it is used for testing purposes
	 */
	@Override
	public String toString() {
		return ""+tradeId;
	}

	/***
	 * Do not modify this equals, it is used for testing purposes
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (tradeId != other.tradeId)
			return false;
		return true;
	}
	
}
