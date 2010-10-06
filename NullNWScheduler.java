/*
 * NullNWScheduler
 *
 * Always allow sender to send right away.
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */

public class NullNWScheduler implements NWScheduler{
  
  //-------------------------------------------------
  // Constructor
  //-------------------------------------------------
  public NullNWScheduler()
  {
    return;
  }

  //-------------------------------------------------
  // waitMyTurn -- return immediately for NullNWScheduler
  //-------------------------------------------------
  public void waitMyTurn(int flowId, float weight, int lenToSend)
  {
    return;
  }

}
