
public class DemoSTFQScheduler3c {
	
	static int nFlows ;
	static int bytesToSendPerFlow ;
	static long maxBytesPerSec  ;
	
	
	public static void main(String[] args) {
		nFlows = 5;//send 5 flows through two threads each simultaneously
		bytesToSendPerFlow = 10000000;//10MB
		maxBytesPerSec = 100000;//100KB/s
		NWScheduler sched3 = new STFQNWScheduler(maxBytesPerSec);
		DemoScheduler3c ds2 = new DemoScheduler3c(sched3, nFlows, bytesToSendPerFlow);
		ds2.go();    

		System.exit(0);
	}

}
