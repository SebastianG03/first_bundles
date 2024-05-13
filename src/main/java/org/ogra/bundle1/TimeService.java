package org.ogra.bundle1;

public class TimeService implements ITimeService {
	
	long start;
	
	public TimeService() {
		start = System.nanoTime();
	}

	@Override
	public String getExecutionTime() {
		long end = System.nanoTime();
		return (end - start) + "";
	}

}
