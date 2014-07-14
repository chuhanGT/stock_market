package PeopleTrade;

public class OfferPrice {
	public double offerPrice(double currentPrice){
		
			
			//rr=rr*100;
			//int rr2=(int)Math.floor(rr);
			//double rrr=(double)rr2/100;
			double newprice=0;
			do{
				RandomNumber r = new RandomNumber();
				double rr = r.NextGau();
				double d1 = r.RandomDouble();
double d2 = 0.01 + 0.09*d1;
				double temp = d2*currentPrice*rr+currentPrice;
			    newprice = Math.floor(temp*10)/10;
			}
			while(newprice<=0);
			
			return newprice;
		
		
	}
}
