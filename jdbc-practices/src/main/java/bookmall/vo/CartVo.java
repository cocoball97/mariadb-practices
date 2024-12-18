package bookmall.vo;

public class CartVo {
	private String title;
	private Long quantity;
	private Long price;
	private Long book_no;
	private Long user_no;

	public CartVo() {
	}
	
	public void setQuantity(long quantity) {
		this.quantity = Long.valueOf(quantity);
	}

	public String getBookTitle() {
		return title;
	}

	public Long getQuantity() {
		return quantity;
	}

	public Long getPrice() {
		return price;
	}

	public Long getBookNo() {
		return book_no;
	}

	public void setBookNo(Long book_no) {
		this.book_no = book_no;
	}

	public Long getUserNo() {
		return user_no;
	}

	public void setUserNo(Long user_no) {
		this.user_no = user_no;
	}

	public void setBookTitle(String title) {
		this.title = title;
	}
	public void setPrice(Long price) {
		this.price = price;
	}

}
