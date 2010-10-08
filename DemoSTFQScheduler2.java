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

public class DemoSTFQScheduler2{

  static int nFlows ;
  static int bytesToSendPerFlow ;
  static long maxBytesPerSec  ;

  //-------------------------------------------------
  // main -- run test program
  //-------------------------------------------------
  public static void main(String args[])
  {
    /*
    //Test1
    nFlows =10;
    bytesToSendPerFlow = 10000000;//10MB
    maxBytesPerSec = 100000;//100KB/s
    NWScheduler sched1 = new STFQNWScheduler(maxBytesPerSec);
    DemoScheduler2 ds = new DemoScheduler2(sched1, nFlows, bytesToSendPerFlow);//need to  sleep each thread 10s
    ds.go();
    */
    
//    nFlows = 5;
//    bytesToSendPerFlow = 10000000;//10MB
//    maxBytesPerSec = 100000;//100KB/s
//    NWScheduler sched2 = new STFQNWScheduler(maxBytesPerSec);
//    //weights are changed
//    DemoScheduler3 ds1 = new DemoScheduler3(sched2, nFlows, bytesToSendPerFlow);
//    ds1.go();    
    
    
    nFlows = 5;//send 5 flows through two threads simultaneously
    bytesToSendPerFlow = 500000;//10000000;//10MB
    maxBytesPerSec = 80000;//100000;//100KB/s
    NWScheduler sched3 = new STFQNWScheduler(maxBytesPerSec);
    DemoScheduler4 ds2 = new DemoScheduler4(sched3, nFlows, bytesToSendPerFlow);
    ds2.go();    
    
    System.exit(0);
  }

}
