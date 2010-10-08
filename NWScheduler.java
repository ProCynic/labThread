/*
 * NWScheduler
 *
 * Interface that allows waiting until a flow's turn
 * to send.
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */

public interface NWScheduler{
  
  public void waitMyTurn(int flowId, float weight, int lenToSend);

}
