/*
 * SynchronizedOutputStream
 *
 * Output stream that allows multiple threads to
 * use it.
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */
import java.io.OutputStream;
import java.io.IOException;

public class SynchronizedOutputStream{

  OutputStream os;
  SimpleLock mutex;
  
    

  //-------------------------------------------------
  // Constructor
  //-------------------------------------------------
  public SynchronizedOutputStream(OutputStream os)
  {
    this.os = os;
    this.mutex=new SimpleLock();

  }

  //-------------------------------------------------
  // close -- close this output stream
  //-------------------------------------------------
  public void close() 
    throws IOException
  {
    mutex.lock();
    
    os.close();
    
    mutex.unlock();            
  }


  //-------------------------------------------------
  // flush -- flush this output stream
  //-------------------------------------------------
  public void flush() 
    throws IOException
  {
    mutex.lock();
    
    os.flush();
    
    mutex.unlock();

  }


  //-------------------------------------------------
  // write -- Write b.length bytes to this output stream
  //-------------------------------------------------
  public void write(byte[] b)
    throws IOException
  {
    mutex.lock();
    
    os.write(b);
    
    mutex.unlock();
    
  }
  

  //-------------------------------------------------
  // write -- Write len bytes starting at off
  // to this output stream
  //-------------------------------------------------
  public void write(byte[] b, int off, int len)
    throws IOException
  {
    mutex.lock();
    
    os.write(b, off, len);
    
    mutex.unlock();
  }
  

  //-------------------------------------------------
  // write -- Write byte to this output stream
  //-------------------------------------------------
  public void write(int bi)
    throws IOException
  {
    mutex.lock();
    
    os.write(bi);
    
    mutex.unlock();
  }
  
  

}
