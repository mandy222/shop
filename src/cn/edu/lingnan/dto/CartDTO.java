package cn.edu.lingnan.dto;

public class CartDTO {
	private int cid;
	private String uid;
	private int totalPrice;
	private int cstate;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getCstate() {
		return cstate;
	}
	public void setCstate(int cstate) {
		this.cstate = cstate;
	}
	
}
