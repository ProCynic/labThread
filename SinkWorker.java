/*
 * SinkWorker
 *
 * Receive connection and fork of a child to read data
 * from it.
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.IOException;
import java.io.InterruptedIOException;

public class SinkWorker extends Thread{

  ServerSocket s;
  boolean verbose;
  
  //-------------------------------------------------
  // Constructor
  //-------------------------------------------------
  public SinkWorker(ServerSocket s, boolean verbose)
  {
    this.s = s;
    this.verbose = verbose;
  }

  //-------------------------------------------------
  // run -- thread main loop. Invoked by jvm when
  // thread.start() is called.
  //-------------------------------------------------
  public void run()
  {
    Socket sock = null;
    try{
      while(!this.isInterrupted()){
        sock = s.accept();
        if(verbose){
          System.out.println("# SinkWorker::run accept connection returns");
        }
        InputStream is = sock.getInputStream();
        SinkWorkerWorker sww = new SinkWorkerWorker(is, verbose);
        sww.start();
      }
    }
    catch(InterruptedIOException iioe){
      if(verbose){
        System.out.println("SinkWorker::run interrupted for normal shutdown");
      }
    }
    catch(IOException ioe){
        System.out.println("SinkWorker::run IOException" + ioe.toString());
    }
    finally{
      try{
        if(sock != null){
          sock.close();
        }
      }
      catch(IOException e){
        // ignore
      }
    }
  }

}
