package bookmall.vo;

public class OrderBookVo {
	private String title;
	private Long quantity;
	private Long price;
	private Long book_no;
	private Long order_no;

	public OrderBookVo() {
	}
	
	
	public void setPrice(long price) {
		this.price = Long.valueOf(price);
	}

	public String getBookTitle() {
		return title;
	}

	public Long getQuantity() {
		return quantity;
	}

	public Long getBookNo() {
		return book_no;
	}

	public void setBookNo(Long book_no) {
		this.book_no = book_no;
	}

	public Long getOrderNo() {
		return order_no;
	}

	public void setOrderNo(Long order_no) {
		this.order_no = order_no;
	}

	public void setBookTitle(String title) {
		this.title = title;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getPrice() {
		return price;
	}

	
}