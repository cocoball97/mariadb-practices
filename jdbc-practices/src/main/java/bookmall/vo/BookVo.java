package bookmall.vo;

public class BookVo {
	private Long no;
	private String title;
	private Long price;
	private Long category_no;

	public BookVo(String title, long price) {
		this.title = title;
		this.price = Long.valueOf(price);
	}
	
	public BookVo() {
	}

	public void setCategoryNo(Long category_no) {
		this.category_no = category_no;
	}


	public Long getNo() {
		return no;
	}


	public String getTitle() {
		return title;
	}


	public Long getPrice() {
		return price;
	}


	public Long getCategoryNo() {
		return category_no;
	}


	public void setNo(Long no) {
		this.no = no;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setPrice(Long price) {
		this.price = price;
	}



}
