/*
 * SourceWorker
 *
 * Send a bunch of bytes to specified port.
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */
import java.io.IOException;

public class SourceWorker extends Thread{
  
  int bytesToSend;
  ScheduledOutputStream sos;
  byte b[];
  static int BUFSIZE = 8192;
  static int workerId = 0;


  //-------------------------------------------------
  // Constructor
  //-------------------------------------------------
  public SourceWorker(int bytesToSend, 
                      ScheduledOutputStream sos)
  {
    this.bytesToSend = bytesToSend;
    this.sos = sos;
    int ii;
    //
    // For fun, make the different workers send using
    // different sized buffers
    //
    workerId++;
    if(workerId > 10){
      workerId = 1;
    }
    int myBufSize = BUFSIZE/workerId;
    b = new byte[myBufSize];
    for(ii = 0; ii < myBufSize; ii++){
      b[ii] = 0;
    }
  }

  //-------------------------------------------------
  // run -- thread main loop. Invoked by jvm when
  // thread.start() is called.
  //-------------------------------------------------
  public void run()
  {
    int sent = 0;
    try{
      while(sent < bytesToSend){
        sos.write(b); // May send a bit more than bytesToSend
        sent += b.length;
      }
    }
    catch(IOException ioe){
      System.out.println("# SourceWorker IOException: " + ioe.toString());
    }
    finally{
      try{
        sos.close();
      }
      catch(IOException ioe){
        // ignore
      }
    }
  }
}
