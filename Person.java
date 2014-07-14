package PeopleTrade;
/**
 * The class are the agent who participate in the stock trading.
 * @author Haozheng Tian (tianhzh@gatech.edu)
 *
 */
public class Person {
	private double cash = 0;
	private int share = 0;
	private double buyPrice = 0;
	private int p; // p abbreviates for period. For agents using momentum trading strategy, they will observe the price increasing rate during the past p days
	private double rTarget; // rTarget is the target rate for the stock price change
	private double stopLoss; // stopLoss is the stop loss target
	private double profitTaking; // profitTarking is the profit taking target
	private double[] buy_shreshold;  //For buy and hold
	private double sell_shreshold;  //For buy and hold
	double buy_threshold_cur;
	private int period;
	public void Agent(int p, double rTarget, double stopLoss, double profitTaking){
		this.p = p;
		this.rTarget = rTarget;
		this.stopLoss = stopLoss;
		this.profitTaking = profitTaking;
	}
	public void Agent1(double b_shreshold[], double s_shreshold){
	    this.buy_shreshold = b_shreshold;
	    this.sell_shreshold = s_shreshold;
	}
	public void SetBuyPrice(double price){
	buyPrice = price;
	}
	public void SetPeriod(int j){
		this.period=j;
	}
	//test
	public void Show(int n){
		System.out.println(n+"hi");
	}
	public void SetCash(double c){cash=c;}
	public void SetShare(int s){share=s;}
	public void SetBuyThreshold(double c){buy_threshold_cur = c;}
	//show values
	public double ShowCash(){return cash;}
	public int ShowShare(){return share;}
	public double ShowBuyPrice(){return buyPrice;}
	public int Showp(){return p;}
	public double ShowrTarget(){return rTarget;}
	public double ShowStopLoss(){return stopLoss;}
	public double ShowProfitTaking(){return profitTaking;}
	public double[] ShowBuyThreshold(){return buy_shreshold;}
	public double ShowBuyThresholdCur(){return buy_threshold_cur;}
	public double ShowSellThreshold(){return sell_shreshold;}
	public int ShowPeriod(){return period;}
	//
	public void ChangeCash(double d){cash+= d;}
	public void ChangeShare(int e){share+= e ;}
}
