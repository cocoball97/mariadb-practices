package bookmall.vo;

public class OrderBookVo {
	private Long no;
	private String title;
	private Long quantity;
	private Long price;
	private Long bookno;
	private Long orderno;

	public OrderBookVo() {
	}
	
	public void setQuantity(long quantity) {
		this.quantity = Long.valueOf(quantity);;
	}
	
	public void setPrice(long price) {
		this.price = Long.valueOf(price);
	}

	public Long getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public Long getQuantity() {
		return quantity;
	}

	public Long getBookNo() {
		return bookno;
	}

	public void setBookNo(Long bookno) {
		this.bookno = bookno;
	}

	public Long getOrderNo() {
		return orderno;
	}

	public void setOrderNo(Long orderno) {
		this.orderno = orderno;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getPrice() {
		return price;
	}

	
}