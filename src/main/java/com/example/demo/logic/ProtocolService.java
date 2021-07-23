package com.example.demo.logic;

import java.util.List;

import com.example.demo.rest.boundaries.ProtocolBoundary;

public interface ProtocolService {

	public ProtocolBoundary addProtocols(ProtocolBoundary newProtocols, String userId);

	public void updateProtocols(String userId, ProtocolBoundary updateProtocols);

//	public void deleteProtocols(String userId, ProtocolBoundary deleteProtocols);

	public ProtocolBoundary getProtocolById(String userId, String protocolId);
	
	public List<ProtocolBoundary> getAllProtocols(String userId, int page, int size); // TODO add to protocol controller


}
