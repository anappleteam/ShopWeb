package bupt.sse.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bupt.sse.shop.category.vo.*;
import bupt.sse.shop.product.service.ProductService;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.category.service.CategoryService;

public class IndexAction extends ActionSupport {
	private CategoryService categoryService;
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	@Override
	public String execute() throws Exception {
		List<Category> cList = categoryService.findAll();
		//将一级分类放入session
		ActionContext.getContext().getSession().put("cList",cList);
		List<Product> hList= productService.findHot();
		//查询热门商品
		ActionContext.getContext().getValueStack().set("hList", hList);
		//查询最新商品
		List<Product> nList=productService.fingNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
}
