/*
 * DemoScheduler
 *
 * Framework for demonstrate schedulers by sending
 * lots of bytes through them.
 *
 * NOTE: This demo is not an exhaustive test -- it 
 * does not attempt to test all requirements in the 
 * specification. Passing these tests is *not*
 * a guarantee of a good grade on this assignment.
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */
import java.util.Random;
import java.io.IOException;

public class DemoScheduler3a{


  static int minPort = 4000;
  static int nPorts = 1000;
  Source3a source;
  Sink sink;
  Stats stats;

  public DemoScheduler3a(NWScheduler sched, int nThreads, int bytesToSendPerThread)
  {
    int ii;
    stats = new Stats();
    int testPort = selectPort();
    try{
      sink = new Sink(testPort, false);
    }
    catch(IOException e){
      System.out.println("TestNullScheduler cannot create sink on port " 
                         + testPort 
                         + " " + e.toString());
      e.printStackTrace();
      System.exit(-1);
    }
    float weights[] = new float[nThreads];
    for(ii = 0; ii < nThreads; ii++){
      weights[ii] = (float)1.0;
    }
    source = new Source3a(nThreads, bytesToSendPerThread, sched, 
                        weights, stats, testPort);
  }

  public void go(){
    source.start();
    source.waitFor();
    if(sink != null){
      sink.close();
    }
    stats.print();
  }

  private static int selectPort()
  {
    Random r = new Random(System.currentTimeMillis());
    int rnd = r.nextInt();
    if(rnd < 0){
      rnd = rnd * -1;
    }
    int port = rnd % nPorts + minPort;
    assert(port >= minPort);
    assert(port < minPort + nPorts);
    return port;
  }

}
