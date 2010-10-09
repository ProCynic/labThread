
public class DemoSTFQScheduler3b {

	static int nFlows ;
	static int bytesToSendPerFlow ;
	static long maxBytesPerSec  ;

	//-------------------------------------------------
	// main -- run test program
	//-------------------------------------------------
	public static void main(String[] args) {
		nFlows = 5;
		bytesToSendPerFlow = 10000000;//10MB
		maxBytesPerSec = 100000;//100KB/s
		NWScheduler sched2 = new STFQNWScheduler(maxBytesPerSec);
		//weights are changed
		DemoScheduler3b ds1 = new DemoScheduler3b(sched2, nFlows, bytesToSendPerFlow);
		ds1.go();    

		System.exit(0);

	}

}
