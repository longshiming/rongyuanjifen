package com.ryjf.caspar.bean;



import java.io.Serializable;

public class BaseBean implements Serializable {
	private static final long serialVersionUID = 477434240087065671L;

	private int status;
	
	private String message;

	public boolean isSucc() {
		
		return status==200;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String info) {
		this.message = info;
	}

	public int getStatus() {
		return status;
	}
}
