package bookmall.vo;

public class OrderVo {
	private Long no;
	private String number;
	private String name;
	private String status;
	private Long payment;
	private String shipping;
	private Long userno;

	public OrderVo() {
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public void setPayment(long payment) {
		this.payment = Long.valueOf(payment);
	}
	
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public Long getNo() {
		return no;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPayment(Long payment) {
		this.payment = payment;
	}

	public void setUserNo(Long userno) {
		this.userno = userno;
	}

	public String getStatus() {
		return status;
	}

	public Long getPayment() {
		return payment;
	}

	public String getShipping() {
		return shipping;
	}

	public Long getUserno() {
		return userno;
	}
	
}
