package PeopleTrade;
//Technical trading class
//by Chu Han
import java.util.*;

public class TechTrade {
	
	
	
	
	
	private boolean SellorNot(ArrayList<Double> price,int period){

	    int time=price.size();
		boolean willSell;
		
		double sum=0.0;
		if(time<period){
			period=time;
		}
		
		for(int i=time-1;i>=time - period;i--){
			sum=sum+price.get(i);
		}
		double avg=sum/period;
		if (price.get(time-1)>avg){
			willSell=true;
		}
		else{
			willSell=false;
		}
		return willSell;
	}

	public int BuyShare (ArrayList<Double> price, double offerprice, double vaultcash,int period){
		TechTrade run=new TechTrade();
		
		boolean sell= run.SellorNot(price,period);
		double temp=vaultcash/offerprice;
		int affordable= (int)temp;
		int offershare = 0;
		if (sell == false){
			offershare=affordable;
		}
		return offershare;
	}
	
	public int SellShare(ArrayList<Double> price, int vaultshare, int period){
		TechTrade run=new TechTrade();
		boolean sell=run.SellorNot(price,period);
		int offershare=0;
		if (sell){
			offershare=vaultshare;
		}
		return offershare;
	}
	
	
	}
