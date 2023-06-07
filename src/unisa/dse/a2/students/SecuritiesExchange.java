package unisa.dse.a2.students;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

import unisa.dse.a2.interfaces.ListGeneric;

public class SecuritiesExchange {

	/**
	 * Exchange name
	 */
	private String name;
	
	public String getName() {
		return name;
	}
	
	/**
	 * List of brokers on this exchange
	 */
	public DSEListGeneric<StockBroker> brokers;
	
	/**
	 * List of announcements of each trade processed
	 */
	public DSEListGeneric<String> announcements;
	
	/**
	 * HashMap storing the companies, stored based on their company code as the key
	 */
	public HashMap<String, ListedCompany> companies;

	/**
	 * Initialises the exchange ready to handle brokers, announcements, and companies
	 * @param name
	 */
	public SecuritiesExchange(String name)
	{
		this.name = name;
		brokers = new DSEListGeneric<StockBroker>();
		announcements= new DSEListGeneric<String>();
		companies = new HashMap<String,ListedCompany>();
	}
	
	/**
	 * Adds the given company to the list of listed companies on the exchange
	 * @param company
	 * @return true if the company was added, false if it was not
	 */
	public boolean addCompany(ListedCompany company)
	{
		int prevSize = companies.size();
		companies.put(company.getName(), company);
		return companies.size() != prevSize;
	}

	/**
	 * Adds the given broke to the list of brokers on the exchange
	 * @param company
	 */
	public boolean addBroker(StockBroker broker)
	{
		int prevSize = this.brokers.size();
		this.brokers.add(broker);
		return this.brokers.size() != prevSize;
	}
	
	/**
	 * Process the next trade provided by each broker, processing brokers starting from index 0 through to the end
	 * 
	 * If the exchange has three brokers, each with trades in their queue, then three trades will processed, one from each broker.
	 * 
	 * If a broker has no pending trades, that broker is skipped
	 * 
	 * Each processed trade should also make a formal announcement of the trade to the announcements list in the form a string:
	 * "Trade: QUANTITY COMPANY_CODE @ PRICE_BEFORE_TRADE via BROKERNAME", 
	 * e.g. "Trade: 100 DALL @ 99 via Honest Harry Broking" for a sale of 100 DALL shares if they were valued at $99
	 * Price shown should be the price of the trade BEFORE it's processed. Each trade should add its announcement at 
	 * the end of the announcements list
	 * 
	 * @return The number of successful trades completed across all brokers
	 * @throws UntradedCompanyException when traded company is not listed on this exchange
	 */
	public int processTradeRound() throws UntradedCompanyException
	{
		boolean pendingTrades = true;
		if (brokers == null) {
			return 0;
		}

		int count = 0;
		while(pendingTrades) {
			pendingTrades = false;
			for (int j = 0; j < brokers.size(); j++) {
				StockBroker fBroker = brokers.get(j);
				if (fBroker == null) {
					throw new NullPointerException("Null StockBroker");
				}
				if (fBroker.getPendingTradeCount() > 0) {
					pendingTrades = true;
					Trade trade = fBroker.getNextTrade();
				
					if(trade != null) {
						String fCode = trade.getCompanyCode();
						int fQuantity = trade.getShareQuantity();
						if(!companies.containsKey(fCode)) {
							throw new UntradedCompanyException(fCode);
							
						} 
						ListedCompany fCompany = companies.get(fCode);
						announcements.add("Trade: " + fQuantity + " " + fCode + " @ " + fCompany.getCurrentPrice() + " via " + fBroker.getName());
						fCompany.processTrade(trade.getShareQuantity());
						count++;
					}
				} 

			}
		}
		
		
		
		return count;
	}


	public int runCommandLineExchange(Scanner sc)
	{
		return 0;
	}
}
