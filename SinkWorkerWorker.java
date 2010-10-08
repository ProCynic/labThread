/*
 * SinkWorkerWorker
 *
 * Recieve all data from a stream.
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */
import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;

public class SinkWorkerWorker extends Thread{

  boolean verbose;
  InputStream is;

  //-------------------------------------------------
  // Constructor
  //-------------------------------------------------
  public SinkWorkerWorker(InputStream is, boolean vebose)
  {
    this.verbose = verbose;
    this.is = is;
  }
  

  //-------------------------------------------------
  // run -- thread main loop. Invoked by jvm when
  // thread.start() is called.
  //-------------------------------------------------
  public void run()
  {
    int got = 0;
    byte b[] = new byte[8192];

    try{
      while(true){
        got += is.read(b);
      }
    }
    catch(IOException ioe){
      if(verbose){
        System.out.println("SinkWorkerWorker close stream after reading ("
                           + ioe.toString() + ") "
                           + got + " bytes");
      }
    }
    finally{
      try{
        is.close();
      }
      catch(IOException sd){
        // ignore
      }
    }
  }

}
