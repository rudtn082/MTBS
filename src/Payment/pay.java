package Payment;

public class pay {
	private int payNum;
	private int bookNum;
	private int price;
	private String payMethod; // ����Ʈ���� or ����
	private String mID;
	private String payType; // ���ͳݰ���(����) or �������(���� ���忡�� �����ڸ� ����)

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
