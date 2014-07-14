package PeopleTrade;
//import java.util.*;
	/**
	 * 
	 * @author Haozheng Tian(tianhzh@gatech.edu)
	 *
	 */
public class FollowtheTrend {
	/**
	 * 
	 * @param x
	 * @return the probability of buying
	 */
	public double BuyProb(double x){
		//    0.5*((30^(x-0.2)-30^(-x+0.2))/(30^(x-0.2)+30^(-x+0.2))+1)
		//	the probability to buy
		double a = -0.2;
		double l = Math.pow(30,	x+a);
		double m = Math.pow(30, -x-a);
		
		double result = 0.5*((l - m)/(l + m) + 1);
		return result;
	}
	/**
	 * 
	 * @param x
	 * @return the probability of selling
	 */
	public double SellProb(double x){
		//	    -0.5*((30^(x+0.2)-30^(-x-0.2))/(30^(x+0.2)+30^(-x-0.2))-1)
		//	the probability to sell
		double a = 0.2;
		double l = Math.pow(30,	x+a);
		double m = Math.pow(30, -x-a);
			
		double result = -0.5*((l - m)/(l + m) - 1);
		return result;
	}
	/**
	 * 
	 * @param price
	 * @return the price that the person agree to trade with
	 */
	public double OfferPrice(double price){
		RandomNumber r = new RandomNumber();
		double rr = r.RandomDouble();
		double newprice = 0.04*price*rr+0.98*price;
		return newprice;
	}
	/**
	 * 
	 * @param pbuy
	 * @param psell
	 * @param offerprice
	 * @param vaultcash
	 * @return the amount of share that the person wish to buy
	 */
	public int BuyShare(double pbuy,double psell, double offerprice,double vaultcash){
		double temp = vaultcash/offerprice;
		int affordable = (int) temp;
		int offershare;
		if(pbuy/psell <1.2 ){
			if(affordable < 3){offershare = affordable;}
			else{offershare = affordable/3;}
		}
		else if (pbuy/psell >= 1.2 && pbuy/psell < 1.5){
			if(affordable < 2){offershare = affordable;}
			else{offershare = affordable/2;}
			}
		else {offershare = affordable;}
		return offershare;
	}
	/**
	 * 
	 * @param pbuy
	 * @param psell
	 * @param offerprice
	 * @param vaultshare
	 * @return the amount of share that the person wish to sell
	 */
	public int SellShare(double pbuy,double psell, double offerprice,int vaultshare){
		int offershare;
		if(psell/pbuy <1.2 ){
			if(vaultshare < 3){offershare = vaultshare;}
			else{offershare = vaultshare/3;}
			}
		else if (psell/pbuy >= 1.2 && psell/pbuy < 1.5){
			if(vaultshare < 2){offershare = vaultshare;}
			else{offershare = vaultshare/2;}
			}
		else {offershare = vaultshare;}
		return offershare;
	}
}
