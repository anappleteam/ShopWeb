package bupt.sse.shop.categorysecond.vo;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.New;

import bupt.sse.shop.category.vo.Category;
import bupt.sse.shop.product.vo.Product;
import javassist.compiler.ast.NewExpr;

//二级分类实体
public class CategorySecond {
	private Integer csid;
	private String csname;
	//所属的一级分类的对象
	private Category category;
	//配置商品的集合
	private Set<Product> products = new HashSet<Product>();
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
}
