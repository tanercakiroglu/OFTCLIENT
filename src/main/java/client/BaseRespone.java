package client;

public class BaseRespone {

	String tag;
	
	boolean status;
	
	Object response;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Object getResponse() {
		return response;
	}

	public void setRespone(Object response) {
		this.response = response;
	}
	
	
}
