package PeopleTrade;
// BuyAndHold trading class
// by Xin Guo

public class BuyAndHold {
public int BuyShare(double offerprice, double vaultcash, double[] buy_threshold, Person person) 
{
	double temp = vaultcash/offerprice;
	int affordable = (int) temp;
	int offershare = 0;
    
	if (offerprice > 30)
	{
         if (person.ShowBuyThresholdCur() != buy_threshold[2])
         {
        	 person.SetBuyThreshold(buy_threshold[2]);
         }
	}
	else if (offerprice > 20)
	{
        if (person.ShowBuyThresholdCur() != buy_threshold[1])
        {
       	 person.SetBuyThreshold(buy_threshold[1]);
        }
	}
	else
	{
      	 person.SetBuyThreshold(buy_threshold[0]);
	}
	if (offerprice < person.ShowBuyThresholdCur())
	{
		offershare = affordable;
	}
	return offershare;	
}

public int SellShare(double offerprice, int vaultshare, double sell_threshold,Person person)
{
	int offershare = 0;

	if (offerprice - person.ShowBuyPrice() >= sell_threshold){
		offershare = vaultshare;
	}
	return offershare;
}

}
