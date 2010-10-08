/*
 * DemoNullScheduler
 *
 * Demonstrate the NULL scheduler
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */

public class DemoNullScheduler{

  static int nThreads = 10;
  static int bytesToSendPerThread = 1000000000;


  //-------------------------------------------------
  // main -- run test program
  //-------------------------------------------------
  public static void main(String args[])
  {
    NWScheduler sched = new NullNWScheduler();
    DemoScheduler ds = new DemoScheduler(sched, nThreads, bytesToSendPerThread);
    ds.go();
    System.exit(0);
  }

}
