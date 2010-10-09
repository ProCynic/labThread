/*
 * MaxNWScheduler
 *
 * Implement the NWScheduler Interface to provide
 * a scheduler that ensures that system sends
 * at most max bytes per second.
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */

import java.util.concurrent.locks.Condition;

public class MaxNWScheduler implements NWScheduler{

	AlarmThread at;
	SimpleLock mutex;
	Condition c1;

	long nextTurn;
	long m;
	//
	// Fill in code
	//


	//-------------------------------------------------
	// Constructor
	//-------------------------------------------------
	public MaxNWScheduler(long bytesPerSec)
	{
		this.mutex = new SimpleLock();
		this.c1 = mutex.newCondition();
		this.at = new AlarmThread(this);
		this.at.start();
		this.m = bytesPerSec;
		this.nextTurn = System.currentTimeMillis();
	}

	//-------------------------------------------------
	// waitMyTurn -- return only after caller may safely
	// send. If prev send s0 at time t0 transmitted b0
	// bytes, the next send may not send before
	// t1 >= t0 + b0/bytesPerSec
	//
	// NOTE: See the assignent for important restriction.
	// In particular, this call must use Condition.await()
	// (or Condition.awaitUninterruptibly())
	// with *no arguments* and it may not call Thread.sleep().
	// Instead, you must rely on an alarmThread to 
	// signal when it is OK to proceed.
	//-------------------------------------------------
	public void waitMyTurn(int flowId, float weight, int lenToSend) {
		mutex.lock();
		while(System.currentTimeMillis() < nextTurn) {  // While it's not time for the next write yet:
			c1.awaitUninterruptibly();  //wait.  will eventually end up in alarmthread
		}
		nextTurn = System.currentTimeMillis() + (long)((1000*lenToSend)/m );  // The time of the next write
		mutex.unlock();
		return;
	}



	//
	// Hint: you will need to add at least one
	// new public method that AlarmThread will
	// call.
	//

	// signal some waiting write to proceed
	public void proceed() {
		mutex.lock();
		c1.signal();
		mutex.unlock();
	}

	//return the time of the next write.
	public long getNextTurn(){
		mutex.lock();
		mutex.unlock();
		return nextTurn;
	}
}
