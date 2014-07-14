package PeopleTrade;
import java.util.*;
/**
 * The class make the trade between people
 * @author Haozheng Tian (tianhzh@gatech.edu)
 *
 */
public class Broker {
	public void test(ArrayList<Person> j){
		for(int i =0;i< j.size(); i++){
			
			j.get(i).ChangeCash(-1000);
			double temp=j.get(i).ShowCash();
			System.out.println(temp);
		}
	}
	public double[] MakeTrade(ArrayList<ArrayList<Double>> buyMoney,  ArrayList<ArrayList<Double>> sellMoney,  ArrayList<Person> person, double price){
		// finalreturn, the first store price, the second share volume, the third the cash volume
		double[] finalreturn=new double[3];
		//set the initiating price
		finalreturn[0] = price;
		
		Collections.sort(buyMoney, new Comparator<ArrayList<Double>>() {
		    public int compare(ArrayList<Double> a, ArrayList<Double> b) {
		    	
		        return b.get(1).compareTo(a.get(1));
		    }});
		Collections.sort(sellMoney, new Comparator<ArrayList<Double>>() {
		    public int compare(ArrayList<Double> a, ArrayList<Double> b) {
		        return a.get(1).compareTo(b.get(1));
		    }});
		//actually make the transactions
		while(buyMoney.get(0).get(1) >= sellMoney.get(0).get(1)){
			//System.out.println("broker"+ buyMoney.get(0).get(1) + " $$ "+ sellMoney.get(0).get(1));// for test
			
			//buyMoney, sellMoney: 1st ID of people, 2nd offerprice, 3rd offershare
			int buyer = (int)((double)buyMoney.get(0).get(0));
			int seller = (int)((double)sellMoney.get(0).get(0));
			
			if((int)((double)buyMoney.get(0).get(2)) > (int)((double)sellMoney.get(0).get(2))){
				//System.out.println("b");
				// modify seller's account
				
				person.get(seller).ChangeShare((int) -sellMoney.get(0).get(2));
				person.get(seller).ChangeCash(buyMoney.get(0).get(1)*sellMoney.get(0).get(2));
				//modify buyer's account
				
				person.get(buyer).ChangeShare((int) ((double)sellMoney.get(0).get(2)));
				person.get(buyer).ChangeCash(-buyMoney.get(0).get(1)*sellMoney.get(0).get(2));
				//set buying price
				if(buyer<750 && buyer>=250){
					person.get(buyer).SetBuyPrice(buyMoney.get(0).get(1));
				}
				//if(person.get(buyer).ShowCash()<0){System.out.println(buyer);break;}
				//modify buyer's list
				double remaining= (buyMoney.get(0).get(2)-sellMoney.get(0).get(2));
				buyMoney.get(0).set(2,remaining);
				//update transaction price
				finalreturn[0]=buyMoney.get(0).get(1);
				finalreturn[1]+=sellMoney.get(0).get(2);
				finalreturn[2]+=sellMoney.get(0).get(2)*buyMoney.get(0).get(1);
				//remove people with completed transaction
				sellMoney.remove(0);
					
				
			}
			else if ((int)((double)buyMoney.get(0).get(2)) < (int)((double)sellMoney.get(0).get(2))){
				//System.out.println("bb");
				// modify seller's account
				person.get(seller).ChangeShare((int)-buyMoney.get(0).get(2));
				person.get(seller).ChangeCash(buyMoney.get(0).get(1)*buyMoney.get(0).get(2));
				//modify buyer's account
				person.get(buyer).ChangeShare((int)((double)buyMoney.get(0).get(2)));
				person.get(buyer).ChangeCash(-buyMoney.get(0).get(1)*buyMoney.get(0).get(2));
				//set buying price
				if(buyer<750 && buyer>=250){
					person.get(buyer).SetBuyPrice(buyMoney.get(0).get(1));
				}
				//modify buyer's list
				double remaining=sellMoney.get(0).get(2) - buyMoney.get(0).get(2);
				sellMoney.get(0).set(2,remaining);
				//update transaction price
				finalreturn[0]=buyMoney.get(0).get(1);
				finalreturn[1]+=buyMoney.get(0).get(2);
				finalreturn[2]+=buyMoney.get(0).get(2)*buyMoney.get(0).get(1);
				
				//remove people with completed transaction
				
				buyMoney.remove(0);
				
			}
			else {
				//System.out.println("bbb");
				// modify seller's account
				person.get(seller).ChangeShare((int)-buyMoney.get(0).get(2));
				person.get(seller).ChangeCash(buyMoney.get(0).get(1)*buyMoney.get(0).get(2));
				//modify buyer's account
				person.get(buyer).ChangeShare((int)((double)buyMoney.get(0).get(2)));
				person.get(buyer).ChangeCash(-buyMoney.get(0).get(1)*buyMoney.get(0).get(2));
				//set buying price
				if(buyer<750 && buyer>=250){
					person.get(buyer).SetBuyPrice(buyMoney.get(0).get(1));
				}
				//update transaction price
				finalreturn[0]=buyMoney.get(0).get(1);
				finalreturn[1]+=sellMoney.get(0).get(2);
				finalreturn[2]+=sellMoney.get(0).get(2)*buyMoney.get(0).get(1);
				
				//remove people with completed transaction
				
				buyMoney.remove(0);
				sellMoney.remove(0);	
				
			}
			if(buyMoney.size()==0){break;}
			if(sellMoney.size()==0){break;}
			
		}
		return finalreturn;
	}
}
