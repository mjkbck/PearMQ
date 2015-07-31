package com.nis.pmq.client.loadbalancer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import com.nis.pmq.client.SocketClient;

public class RoundRobinStrategy implements LoadBalancerStrategy {
	
	private Deque<SocketClient> dq = new ArrayDeque<SocketClient>();

	@Override
	public synchronized SocketClient chooseServer(ServiceStatsData socketStatsData) {
		SocketClient socket = dq.removeFirst();
		dq.addLast(socket);
		
		return socket;
	}

	@Override
	public synchronized void registerSocket(SocketClient socket) {
		if (!dq.contains(socket)) {
			dq.add(socket);
		}		
	}



}
