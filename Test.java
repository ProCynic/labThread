/*
 * DemoSTFQScheduler
 *
 * Demonstrate the MAX scheduler
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */

public class Test{

  static int nFlows = 2;
  static int bytesToSendPerFlow = 10000;
  static long maxBytesPerSec = 1000;

  //-------------------------------------------------
  // main -- run test program
  //-------------------------------------------------
  public static void main(String args[])
  {
    NWScheduler sched = new STFQNWScheduler(maxBytesPerSec);
    DemoScheduler ds = new DemoScheduler(sched, nFlows, bytesToSendPerFlow);
    ds.go();
    System.exit(0);
  }

}
