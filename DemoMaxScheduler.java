/*
 * DemoMaxScheduler
 *
 * Demonstrate the MAX scheduler
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */

public class DemoMaxScheduler{

  static int nFlows = 10;
  static int bytesToSendPerFlow = 1000000;
  static long maxBytesPerSec = 80000;

  //-------------------------------------------------
  // main -- run test program
  //-------------------------------------------------
  public static void main(String args[])
  {
    NWScheduler sched = new MaxNWScheduler(maxBytesPerSec);
    DemoScheduler ds = new DemoScheduler(sched, nFlows, bytesToSendPerFlow);
    ds.go();
    System.exit(0);
  }

}
