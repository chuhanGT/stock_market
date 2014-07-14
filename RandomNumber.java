package PeopleTrade;
import java.security.SecureRandom;
/**
 * Generate random number.
 * @author Haozheng Tian (tianhzh@gatech.edu)
 *
 */
public class RandomNumber {
	public double RandomDouble(){
		SecureRandom random = new SecureRandom();
		double d1 = random.nextDouble();
		return d1;
	}
	public double NextGau(){
		SecureRandom random = new SecureRandom();
		double d1 = random.nextGaussian();
		return d1;
	}
}
