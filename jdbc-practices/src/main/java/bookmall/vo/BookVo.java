package bookmall.vo;

public class BookVo {
	private Long no;
	private String title;
	private Long price;
	private Long categoryno;

	public BookVo(String title, long price) {
		this.title = title;
		this.price = Long.valueOf(price);
	}
	
	public BookVo() {
	}

	public void setCategoryNo(Long categoryno) {
		this.categoryno = categoryno;
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


	public Long getCategoryno() {
		return categoryno;
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


	public void setCategoryno(Long categoryno) {
		this.categoryno = categoryno;
	}


}
