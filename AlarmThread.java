/*
 * AlarmThread
 *
 * Get next deadline from scheduler, sleep until
 * the deadline, call the scheduler.
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */
public class AlarmThread extends Thread{
  MaxNWScheduler scheduler;


  //-------------------------------------------------
  // Constructor
  //-------------------------------------------------
  public AlarmThread(MaxNWScheduler s)
  {
    scheduler = s;
  }

  //-------------------------------------------------
  // run -- thread main loop. Invoked by jvm when
  // thread.start() is called.
  //-------------------------------------------------
  public void run() {
	  while(true) {
		  long deadline = scheduler.getNextTurn() - System.currentTimeMillis();
		  if(deadline < 0)
			  deadline = 0;
		  //System.out.println("Sleeping for " + deadline);
		  try {
			  Thread.sleep(deadline);
		  } catch (InterruptedException E) {
		  }
		  scheduler.procede();
	  }
    // Main loop:
    // Get next deadline from scheduler, sleep until
    // the deadline, call the scheduler.
    //
    // NOTE: This is the *ONE* place in part 2 of the
    // project where you will call Thread.sleep
    //
	  
	  


  }
}
