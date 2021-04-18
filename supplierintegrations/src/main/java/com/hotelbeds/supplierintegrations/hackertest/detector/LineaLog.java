package com.hotelbeds.supplierintegrations.hackertest.detector;

class LineaLog {
	
	public enum Action {SIGNIN_SUCCESS, SIGNIN_FAILURE};
	
	private final String ip;
	private final long date;
	private final Action action;
	private final String username;
	
	public LineaLog(String ip, long date, Action action, String username) {
		super();
		this.ip = ip;
		this.date = date;
		this.action = action;
		this.username = username;
	}
	
	public boolean loginExitoso() {
		return action == Action.SIGNIN_SUCCESS;
	}

	@Override
	public String toString() {
		return "LineaLog [ip=" + ip + ", date=" + date + ", action=" + action + ", username=" + username + "]";
	}

	public String getIp() {
		return ip;
	}

	public long getDate() {
		return date;
	}

	public Action getAction() {
		return action;
	}

	public String getUsername() {
		return username;
	}

}
