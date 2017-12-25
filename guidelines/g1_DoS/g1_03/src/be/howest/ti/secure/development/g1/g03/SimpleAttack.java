package be.howest.ti.secure.development.g1.g03;

public class SimpleAttack {
	public static void main(String[] args) {
        new SimpleAttack().run();
    }

    public void run() {
        System.out.println(this.getClass().toString());

        System.out.println("Unsafe version with input " + Integer.MAX_VALUE);
        this.checkGrowByUnsafe(Integer.MAX_VALUE);

        System.out.println("Safe version with input " + Integer.MAX_VALUE);
        this.checkGrowBySafe(Integer.MAX_VALUE);
   
        System.out.println("LogicalThinking version with input " + (Integer.MAX_VALUE));
        this.checkGrowdByLogicalThinking(Integer.MAX_VALUE);
    }

   private void checkGrowByUnsafe(int extra) {
        int current = 10, max = 1024;

        System.out.println("current (" + current + ") + extra (" + extra + ") = " + (current + extra));

        if (extra > 0 && current + extra < max) {
            System.out.println("Go ahead");
        } else {
            System.out.println("Too much");
        }
    }
   
    private void checkGrowBySafe(int extra) {
       int current = 10, max = 1024;

       System.out.println("max (" + max + ") - extra (" + extra + ") = " + (max - extra));

       if (extra < 0 || current > max - extra) {
           System.out.println("Too much");
       } else {
           System.out.println("Go ahead");
       }
    }
   /**
    * Added by Sven Meuleman on 24/12/2017.
    * 
    */
    private void checkGrowdByLogicalThinking(int extra)
    {
    	int current =10, max = 1024;
    	if(extra <0)
    	{
    		System.out.println("You cannot add a negative, that would be substracting!");
    	}
    	else 
    	{
    		if(extra==Integer.MAX_VALUE && current >0)
    		{
    			System.out.println("You cannot add the max value of Integer, that would overflow!");
    		}
    		else
    		{
    			/**Overflowing the MAX_VALUE would return a negative value
    			 * We already checked that extra is bigger than 0.
    			 * And now check that current is also bigger than 0 but the sum of current and extra would be negative, we can state that a negative value would indicate an overflow
    			 */
    			if(current+extra<=0 && current>0) 
    			{
    				System.out.println("You cannot add "+extra+" to "+current+" as the sum("+(current+extra)+") is bigger than the max value of Integer, that would overflow!");
    			}
    			else
    			{
    				if(current+extra>max)
    				{
    					System.out.println("You cannot add "+extra+" to "+current+" as the sum ("+(current+extra)+") is bigger than the max value("+max+"), that would overflow!");	
    				}
    				else
    				{
    					 System.out.println("current (" + current + ") + extra (" + extra + ") = " + (current + extra));
    				}
    			}
    		}
    	}
    }
    
}
