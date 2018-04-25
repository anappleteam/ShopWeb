package bupt.sse.shop.cart.vo;

import bupt.sse.shop.product.vo.Product;

public class CartItem {
	private Product product;	//product information
	private int count;			//product count
	private double subtotal;	
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public double getSubtotal() {
		return count*product.getShop_price();
	}

}
