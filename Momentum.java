	package PeopleTrade;
	import java.util.*;
// Momentum trading class
// by Kungang Li
// The Agent class need to be modified a little bit; basically, new variables are introduced (p, rTarget, stopLoss and profitTaking). I also changed the double data types to float.
//public class Agent {
//	private float cash = 0f;
//	private int share = 0;
//	float buyPrice = 0f;
//	int p; // p abbreviates for period. For agents using momentum trading strategy, they will observe the price increasing rate during the past p days
//	float rTarget; // rTarget is the target rate for the stock price change
//	float stopLoss; // stopLoss is the stop loss target
//	float profitTaking; // profitTarking is the profit taking target
//	public Agent(int p, float rTarget, float stopLoss, float profitTaking){
//	this.p = p;
//  this.rTarget = rTarget;
//	this.stopLoss = stopLoss;
//	this.profitTaking = profitTaking;
//	}
//	public void SetBuyPrice(float price){
//	buyPrice = price;
//	}
//	public void Show(int n){
//		System.out.println(n+"hi");
//	}
//	public void SetCash(float c){cash=c;}
//	public void SetShare(int s){share=s;}
//	public float ShowCash(){return cash;}
//	public int ShowShare(){return share;}
//	public void ChangeCash(float d){cash+= d;}
//	public void ChangeShare(int e){share+= e ;}
//}


public class Momentum {
	//ArraylList<float> price = new ArrayList<float>();
	//...
	//ArrayList<float> lastPrices = new ArrayList<float> ();
	//for(int i = price.size()-1; i > price.size()-17; i--)
	// {lastPrices.add(price.get(i));}
	//Buyshare(p, rTarget, lastPrices, offerprice, vaultcash)
	//...
	//lastPrices.clear();
	public int BuyShare(int p, double rTarget, ArrayList<Double> lastPrices, double offerprice, double vaultcash){
		double temp = vaultcash/offerprice;
		int affordable = (int) temp;
		int offershare = 0;
		double r = (lastPrices.get(0)-lastPrices.get(p-1))/lastPrices.get(0);
		if(r >= rTarget){
			offershare = affordable;
		}
		return offershare;	
	}
//buyPrice is the price when this agent bought the stock. stopLoss is the stop loss target. profitTaking is the profit taking target.
//SellShare(p, rTarget,lastPrices, offerprice, vaultshare, buyPrice, stopLoss, profitTarget)
		public int SellShare(int p, double rTarget, ArrayList<Double> lastPrices, double offerprice, int vaultshare, double buyPrice, double stopLoss, double profitTarget)
		{
			int offershare = 0;
			double r = -(lastPrices.get(0)-lastPrices.get(p-1))/lastPrices.get(0);
			if(r >= rTarget){
				offershare = vaultshare;
			}
			else if(lastPrices.get(0)-buyPrice <= stopLoss && buyPrice != 0){
				offershare = vaultshare;
			}
			else if(lastPrices.get(0)-buyPrice >= profitTarget && buyPrice != 0){
				offershare = vaultshare;
			}
			return offershare;
		}
	
}

