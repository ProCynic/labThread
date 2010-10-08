/*
 * ScheduledOutputStream
 *
 * Output stream that waits its turn and then 
 * sends bytes according to some NWScheduler policy.
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */

import java.io.OutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

public class ScheduledOutputStream extends OutputStream{

  Stats stats;
  int flowId;
  float weight;
  NWScheduler scheduler;
  SynchronizedOutputStream sos;


  //-------------------------------------------------
  // Constructor
  //-------------------------------------------------
  public ScheduledOutputStream(OutputStream os,
                               int flowId,
                               Stats stats,
                               float weight,
                               NWScheduler scheduler)
  {
    this.sos = new SynchronizedOutputStream(os);
    this.stats = stats;
    this.flowId = flowId;
    this.weight = weight;
    this.scheduler = scheduler;
  }


  //-------------------------------------------------
  // close -- close this output stream
  //-------------------------------------------------
  public void close() 
    throws IOException
  {
    sos.close();
  }


  //-------------------------------------------------
  // flush -- flush this output stream
  //-------------------------------------------------
  public void flush() 
    throws IOException
  {
    sos.flush();
  }


  //-------------------------------------------------
  // write -- Write b.length bytes 
  // to this output stream and update stats.
  //-------------------------------------------------
  public void write(byte[] b)
    throws IOException
  {
    doWrite(b, 0, b.length);
  }
  

  //-------------------------------------------------
  // write -- Write len bytes starting at off
  // to this output stream and update stats.
  //-------------------------------------------------
  public void write(byte[] b, int off, int len)
    throws IOException
  {
    doWrite(b, off, len);
  }
  

  //-------------------------------------------------
  // write -- Write byte 
  // to this output stream and update stats.
  //-------------------------------------------------
  public void write(int bi)
    throws IOException
  {
    byte b = (byte)bi;
    byte ba[] = new byte[1];
    ba[0] = b;
    doWrite(ba, 0, 1);
  }
  
  
  //-------------------------------------------------
  // write -- Wait my turn then writes len bytes 
  // to this output stream and update stats.
  //-------------------------------------------------
  private void doWrite(byte b[], int off, int len)
    throws IOException
  {
    scheduler.waitMyTurn(flowId, weight, len);
    sos.write(b, off, len);
    stats.update(flowId, len);
  }
}
