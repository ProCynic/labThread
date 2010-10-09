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

public class DemoSTFQScheduler3a{

  static int nFlows ;
  static int bytesToSendPerFlow ;
  static long maxBytesPerSec  ;

  //-------------------------------------------------
  // main -- run test program
  //-------------------------------------------------
  public static void main(String args[])
  {

    nFlows =10;
    bytesToSendPerFlow = 10000000;//10MB
    maxBytesPerSec = 100000;//100KB/s
    NWScheduler sched1 = new STFQNWScheduler(maxBytesPerSec);
    DemoScheduler3a ds = new DemoScheduler3a(sched1, nFlows, bytesToSendPerFlow);//need to  sleep each thread 10s
    ds.go();
    
    System.exit(0);
  }

}
