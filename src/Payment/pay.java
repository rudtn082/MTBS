package Payment;

public class pay {
   private int payNo;
   private int bookNo;
   private int price;
   private String payMethod; // ����Ʈ���� or ����
   private String payType; // ���ͳݰ���(����) or �������(���� ���忡�� �����ڸ� ����)

   public int getpayNo() {
      return payNo;
   }
   public void setpayNo(int payNo) {
      this.payNo = payNo;
   }
   public int getbookNo() {
      return bookNo;
   }
   public void setbookNo(int bookNo) {
      this.bookNo = bookNo;
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
   public String getpayType() {
      return payType;
   }
   public void setpayType(String payType) {
      this.payType = payType;
   }
}