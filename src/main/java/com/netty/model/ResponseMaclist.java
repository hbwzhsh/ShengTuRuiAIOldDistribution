package com.netty.model;

import java.util.Collection;

public class ResponseMaclist {
	
    private Collection<String> maclist;

	private Collection<String> nicename;

	public Collection<String> getNicename() {
		return nicename;
	}

	public void setNicename(Collection<String> nicename) {
		this.nicename = nicename;
	}

	public Collection<String> getMaclist() {
		return maclist;
	}

	public void setMaclist(Collection<String> maclist) {
		this.maclist = maclist;
	}
    
    
    
}
