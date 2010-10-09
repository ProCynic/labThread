/*
 * Source
 *
 * Send a bunch of bytes to specified port using
 * a bunch of threads.
 *
 * You must follow the coding standards distributed
 * on the class web page.
 *
 * (C) 2007 Mike Dahlin
 *
 */
import java.net.Socket;
import java.io.OutputStream;
import java.io.IOException;
import java.io.EOFException;

public class Source3c{

	SourceWorker workers[];

	//-------------------------------------------------
	// Constructor
	//-------------------------------------------------
	public Source3c(int nFlows, 
			int bytesToSendPerFlow,
			NWScheduler sched,
			float weight[],
			Stats stats,
			int testPort)
	{
		assert(nFlows < 1000);
		assert(bytesToSendPerFlow > 1000);
		assert(weight.length == nFlows);
		Socket s = null;
		OutputStream os = null;

		workers = new SourceWorker[2*nFlows];

		// create 2 worker threads per flow.
		for(int ii = 0; ii < nFlows; ii++){
			try{
				s = new Socket("localhost", testPort);
				os = s.getOutputStream();
				ScheduledOutputStream sos = new ScheduledOutputStream(os,
						ii,
						stats,
						weight[ii],
						sched);
				workers[ii] = new SourceWorker(bytesToSendPerFlow, sos);
				ScheduledOutputStream sos2 = new ScheduledOutputStream(os,
						ii,
						stats,
						weight[ii],
						sched);
				workers[(2*nFlows-1)-ii] = new SourceWorker(bytesToSendPerFlow, sos2);
			}
			catch(IOException ioe){
				System.out.println(ioe.toString());
				ioe.printStackTrace();
				try{
					if(os != null){
						os.close();
					}
				}
				catch(IOException e){
					// ignore
				}
				try{
					if(s != null){
						s.close();
					}
				}
				catch(IOException e){
					// ignore
				}
				System.exit(-1);
			}
		}
	}


	//-------------------------------------------------
	// start() -- tell all the worker threads to start
	//-------------------------------------------------
	public void start()
	{
		int ii;
		for(ii = 0; ii < workers.length; ii++){
						
			workers[ii].start();
		}
	}

	//-------------------------------------------------
	// waitFor() -- join with all threads
	//-------------------------------------------------
	public void waitFor()
	{
		int ii;
		for(ii = 0; ii < workers.length; ii++){
			try{
				workers[ii].join();
			}
			catch(InterruptedException ie){
				// ignore
			}
		}
	}

}
