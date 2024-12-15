package bookmall.vo;

public class CartVo {
	private Long no;
	private String title;
	private Long quantity;
	private Long price;
	private Long bookno;
	private Long userno;

	public CartVo() {
	}
	
	public void setQuantity(long quantity) {
		this.quantity = Long.valueOf(quantity);
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

	public Long getPrice() {
		return price;
	}

	public Long getBookNo() {
		return bookno;
	}

	public void setBookNo(Long bookno) {
		this.bookno = bookno;
	}

	public Long getUserNo() {
		return userno;
	}

	public void setUserNo(Long userno) {
		this.userno = userno;
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


	
	
}
