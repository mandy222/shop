package cn.edu.lingnan.dto;

public class GoodsDTO {
	private String gid;
	private String gname;
	private int price;
	private int count;
	private int gstate;
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getGstate() {
		return gstate;
	}
	public void setGstate(int gstate) {
		this.gstate = gstate;
	}
}
