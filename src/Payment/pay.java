package Payment;

public class pay {
	private int payNum;
	private int bookNum;
	private int price;
	private String payMethod; // 포인트결제 or 현금
	private String mID;
	private String payType; // 인터넷결제(고객이) or 현장결제(고객이 현장에서 관리자를 통해)

	public int getpayNum() {
		return payNum;
	}
	public void setpayNum(int payNum) {
		this.payNum = payNum;
	}
	public int getbookNum() {
		return bookNum;
	}
	public void setbookNum(int bookNum) {
		this.bookNum = bookNum;
	}
	public int getprice() {
		return price;
	}
	public void setprice(int price) {
		this.price = price;
	}
	public String getpayMethod() {
		return payMethod;
	}
	public void setpayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	public String getpayType() {
		return payType;
	}
	public void setpayType(String payType) {
		this.payType = payType;
	}
}
