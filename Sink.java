/*
 * Sink
 *
 * Open a port and receive as much data on it 
 * as someone is willing to send.
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */
import java.net.ServerSocket;
import java.io.IOException;

public class Sink{

  ServerSocket s;
  SinkWorker t;
  boolean verbose;
  static int NOT_DONE = 0;
  static int DONE = 1;

  //-------------------------------------------------
  // Constructor
  //-------------------------------------------------
  public Sink(int port, boolean verbose)
    throws IOException
  {
    s = new ServerSocket(port);
    t = new SinkWorker(s, verbose);
    t.start();
  }
  

  //-------------------------------------------------
  // close -- indicate that work is done by
  // setting mailbox flag. Then interrupt
  // thread so that it stops accepting
  // connections and checks its mailbox.
  //-------------------------------------------------
  public void close()
  {
    t.interrupt();
  }
}
