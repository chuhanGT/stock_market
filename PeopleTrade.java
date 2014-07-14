	package PeopleTrade;


import java.util.*;
import java.io.*;
/**
 * The main controls the generation of people and the trading process
 * @author Haozheng Tian (tianhzh@gatech.edu)
 *
 */
public class PeopleTrade {
	
	public void RUN(int m) {
		// TODO Auto-generated method stub
		//invoke PeopleTrade class
		long startTime = System.currentTimeMillis();// get start time
		
		int n = 1000;// number of people
		ArrayList<Person> people = new ArrayList<Person>();
		final int[] p={1,3,5,7,9,11,13,15};
		final double[] P={1.0,1.5,2.0,2.5,3,3.5,4,4.5,5,5.5,6,6.5,7,7.5,8,8.5,9,9.5,10};
		final double[] rTarget={0.05,0.1,0.15,0.2,0.25,0.3,0.35,0.4,0.45,0.5};
		final double[] stopLoss={1.0,1.5,2.0,2.5,3,3.5,4,4.5,5,5.5,6,6.5,7,7.5,8,8.5,9,9.5,10};
		final int[] CONST_P={5,10,20,30,60};
		// every person start with 5000share of stock and $50000 cash
		for (int iter = 0; iter < 1000; iter++)
		{
			OfferPrice rand_ = new OfferPrice();
			double r_ = rand_.offerPrice(10);
			System.out.print(iter+":");
			System.out.println(r_);
		}
		for (int i = 0; i<n; i++)
		{
				Person temp=new Person();
				temp.SetShare(5000);
				temp.SetCash(50000);
				 if(i>=250 && i<500){
	                   double[] b_threshold = {0.0, 0.0, 0.0}; 
	                   for (int ii = 0; ii < 3; ++ii) 
	                   { 
	                       RandomNumber rb = new RandomNumber(); 
	                       double rrb = rb.RandomDouble();                      
	                       int rrrb = (int)(rrb*31); 
	                       b_threshold[ii] = rrrb*0.1 + ii*10.0 + 7.0; 
	                   } 
	                   RandomNumber rb = new RandomNumber(); 
	                   double rrb = rb.RandomDouble(); 
	                //rrr is a random integer between 0 to 18 
	                   int rrrb = (int)(rrb*19); 
	                   double s_threshold = rrrb*0.5 + 1.0;     
	                   temp.Agent1(b_threshold, s_threshold); 
	                } 
				 else if(i>=500 && i<750){
					RandomNumber rn = new RandomNumber();
					double r=rn.RandomDouble();
					int pIndex=(int)Math.floor(r*8);
					r=rn.RandomDouble();
					int rIndex=(int)Math.floor(r*10);
					r=rn.RandomDouble();
					int sIndex=(int)Math.floor(r*19);
					r=rn.RandomDouble();
					int PIndex=(int)Math.floor(r*19);
					
					temp.Agent(p[pIndex], rTarget[rIndex], stopLoss[sIndex], P[PIndex]);
				}
				 else if(i>=750){
					 RandomNumber rn = new RandomNumber();
						double r=rn.RandomDouble();
						int num= (int)Math.floor(r*5);
					    temp.SetPeriod(CONST_P[num]);
				 }
				
				people.add(temp);

		}
		//initiate price of stock
		ArrayList<Double> price = new ArrayList<Double>();
		ArrayList<Integer> shareVolume = new ArrayList<Integer>();   // volume of share
		ArrayList<Double> cashVolume = new ArrayList<Double>();	// vulume of share*price
		OfferPrice getPrice = new OfferPrice();
		for(int i=0;i<100;i++){
			price.add(getPrice.offerPrice(10));
			shareVolume.add(0);
			cashVolume.add(0.0);
		}
		try{
			//print initial state of people
		PrintWriter writer = new PrintWriter(m+"PeopleShare.txt", "UTF-8");
		PrintWriter writer2 = new PrintWriter(m+"PeopleCash.txt", "UTF-8");
		PrintWriter writer3 = new PrintWriter(m+"PeopleValue.txt", "UTF-8");
		for(int i=0;i<people.size();i++){
			writer.print(people.get(i).ShowShare() + "\t");
			writer2.print(people.get(i).ShowCash() + "\t");
		double zz= people.get(i).ShowShare()*price.get(price.size() - 1) + people.get(i).ShowCash();
			//System.out.println(i);
			writer3.print(zz + "\t");
		  }
		  
		writer.println();
		writer2.println();
		writer3.println();
		// go through # j times of trades
		//System.out.println("here");
		
		for(int j = 0;j<5000; j++){
			//System.out.println("main"+ j);
			//initiate four strategies
			
			//System.out.println(j);
			FollowtheTrend test=new FollowtheTrend();
			BuyAndHold test2=new BuyAndHold();
			Momentum test3=new Momentum();
			TechTrade test4=new TechTrade();
			
			
			ArrayList<ArrayList<Double>> buyMoney = new ArrayList<>(); 
			//ArrayList<ArrayList<Integer>> buyShare = new ArrayList<>();
			ArrayList<ArrayList<Double>> sellMoney = new ArrayList<>(); 
			//ArrayList<ArrayList<Integer>> sellShare = new ArrayList<>();
			
			//go through all the people to take an action
			for(int i=0;i<n;i++){
				//System.out.println("people"+ i + " main " + j);
				RandomNumber rand = new RandomNumber();
				double R = rand.RandomDouble();
				
				if(i<250){
					double earning=(price.get(price.size()-1)-price.get(price.size()-5))/price.get(price.size()-5);
					double Pbuy = test.BuyProb(earning);//probability to buy
					double Psell = test.SellProb(earning);//probability to sell
					if(R<=Pbuy && R>=0){
						double offerprice=getPrice.offerPrice(price.get(price.size()-1));
						
						if(people.get(i).ShowCash() > offerprice){
							int offershare = test.BuyShare(Pbuy, Psell, offerprice, people.get(i).ShowCash());
							buyMoney.add(new ArrayList<Double>());
							buyMoney.get(buyMoney.size()-1).add((double)i);
							buyMoney.get(buyMoney.size()-1).add(offerprice);
							buyMoney.get(buyMoney.size()-1).add((double)offershare);
						}
					}
					//decision of sell
					else if((R > Pbuy) && (R <= Pbuy + Psell)){
						if(people.get(i).ShowShare() > 0){
							double offerprice=getPrice.offerPrice(price.get(price.size()-1));
							int offershare = test.SellShare(Pbuy, Psell, offerprice, people.get(i).ShowShare());
							sellMoney.add(new ArrayList<Double>());
							sellMoney.get(sellMoney.size()-1).add((double)i);
							sellMoney.get(sellMoney.size()-1).add(offerprice);
							sellMoney.get(sellMoney.size()-1).add((double)offershare);
							
						}
					}
					//decision of hold
					else if (R > Pbuy + Psell && R <= 1){}
					}
				/**
				 * BuyAndHold
				 */
					else if((i>=250) && (i<500)){
						//System.out.println("people"+ i + " main " + j);
						double offerprice=getPrice.offerPrice(price.get(price.size()-1));
						int share=test2.BuyShare(offerprice, people.get(i).ShowCash(),people.get(i).ShowBuyThreshold(), people.get(i));
						
						if(share == 0){
							share=test2.SellShare(offerprice, people.get(i).ShowShare(),people.get(i).ShowSellThreshold(),people.get(i));
							if(share!=0){
								sellMoney.add(new ArrayList<Double>());
								sellMoney.get(sellMoney.size()-1).add((double)i);
								sellMoney.get(sellMoney.size()-1).add(offerprice);
								sellMoney.get(sellMoney.size()-1).add((double)share);
							}
							
						}
						else if(share !=0){
							buyMoney.add(new ArrayList<Double>());
							buyMoney.get(buyMoney.size()-1).add((double)i);
							buyMoney.get(buyMoney.size()-1).add(offerprice);
							buyMoney.get(buyMoney.size()-1).add((double)share);
						}
						
					}
				/**
				 * Momentum
				 */
					else if((i>=500) && (i<750)){
						
						ArrayList<Double> lastPrices=new ArrayList<>();
						for(int z=price.size()-1;z> price.size() - 17;z--){
							lastPrices.add(price.get(z));
							//System.out.println(z);
						}
						double offerprice=getPrice.offerPrice(price.get(price.size()-1));
						int share=test3.BuyShare(people.get(i).Showp(), people.get(i).ShowrTarget(), lastPrices, offerprice, people.get(i).ShowCash());
						if(share==0){
							share=test3.SellShare(people.get(i).Showp(),people.get(i).ShowrTarget(), lastPrices, offerprice, people.get(i).ShowShare(), people.get(i).ShowBuyPrice(), people.get(i).ShowStopLoss(), people.get(i).ShowProfitTaking());
							if(share!=0){
								sellMoney.add(new ArrayList<Double>());
								sellMoney.get(sellMoney.size()-1).add((double)i);
								sellMoney.get(sellMoney.size()-1).add(offerprice);
								sellMoney.get(sellMoney.size()-1).add((double)share);
							}
						}
						else{
							buyMoney.add(new ArrayList<Double>());
							buyMoney.get(buyMoney.size()-1).add((double)i);
							buyMoney.get(buyMoney.size()-1).add(offerprice);
							buyMoney.get(buyMoney.size()-1).add((double)share);
						}
						//System.out.println("people"+ i + " main " + j);
					}
				/**
				 * TechTrade
				 */
					else if((i>=750) && (i<1000)){
						double offerprice=getPrice.offerPrice(price.get(price.size()-1));
						int share=test4.BuyShare(price, offerprice, people.get(i).ShowCash(),people.get(i).ShowPeriod());
						if(share==0){
							share=test4.SellShare(price,people.get(i).ShowShare(),people.get(i).ShowPeriod());
							if(share!=0){
								sellMoney.add(new ArrayList<Double>());
								sellMoney.get(sellMoney.size()-1).add((double)i);
								sellMoney.get(sellMoney.size()-1).add(offerprice);
								sellMoney.get(sellMoney.size()-1).add((double)share);
							}
						}
						else{
							buyMoney.add(new ArrayList<Double>());
							buyMoney.get(buyMoney.size()-1).add((double)i);
							buyMoney.get(buyMoney.size()-1).add(offerprice);
							buyMoney.get(buyMoney.size()-1).add((double)share);
						}
					}
					// decision of buy
					
			}
			//System.out.println(sellMoney);
			//System.out.println("%% "+ sellShare);
			//System.out.println("here");
			if(buyMoney.size()>0 && sellMoney.size()>0){
				
				Broker broker=new Broker();
				double[] newPrice = broker.MakeTrade(buyMoney,sellMoney,people,price.get(price.size()-1));
				price.add(newPrice[0]);
				shareVolume.add((int)newPrice[1]);
				cashVolume.add(newPrice[2]);
			}
			else{
				price.add(price.get(price.size() - 1));
				shareVolume.add(0);
				cashVolume.add(0.0);
			}
			//print people's profile;
			for(int i=0;i<people.size();i++){
				writer.print(people.get(i).ShowShare() + "\t");
				writer2.print(people.get(i).ShowCash() + "\t");
				//if (people.get(i).ShowCash()<0){System.out.println("Negative");}
				double zz= people.get(i).ShowShare()*price.get(price.size() - 1) + people.get(i).ShowCash();
				//System.out.println(i);
				writer3.print(zz + "\t");
			  }
			  
			writer.println();
			writer2.println();
			writer3.println();
		
			
		}
		
		writer.close();
		writer2.close();
		writer3.close();
		}catch (Exception e){//Catch exception if any
			  System.err.println("Error in here: " + e.getMessage());
			  }
		
		//print trading statistics
		try{
			  // Create file 
			PrintWriter writer4 = new PrintWriter(m+"trading_price_vol_cashVol.txt", "UTF-8");
			for(int i=0;i<price.size();i++){
				writer4.print(price.get(i) + "\t"+shareVolume.get(i) + "\t" + cashVolume.get(i));
				writer4.println();
			}
			
			 
			  //Close the output stream
			  writer4.close();
			  }
		catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Took: "+ (double)totalTime/(double)1000 + "seconds.");
		System.out.println("End");
		
	 
	}
}

