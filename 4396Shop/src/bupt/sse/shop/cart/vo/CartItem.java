package bupt.sse.shop.cart.vo;

import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.user.vo.User;

public class CartItem {
	private Integer citemid;
	private Integer count;			//product count
	private Double subtotal;
	private Product product;	//product information
	private User user;
	
	public Integer getCitemid() {
		return citemid;
	}

	public void setCitemid(Integer citemid) {
		this.citemid = citemid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Double getSubtotal() {
		//return count*product.getShop_price();
		return subtotal;
	}

}
