package bupt.sse.shop.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.category.service.CategoryService;
import bupt.sse.shop.category.vo.Category;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
	private Category category=new Category();
	@Override
	public Category getModel() {
		return category;
	}
	CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public String findAll() {
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	public String save(){
		categoryService.save(category);
		//页面跳转
		return "saveSuccess";
	}
	//后台删除一级分类的方法
	public String delete(){
		//接收cid
		category=categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "deleteSuccess";
	}
	//后台编辑一级分类的方法
	public String edit() {
		category=categoryService.findByCid(category.getCid());
		//页面跳转
		return "editSuccess";
	}
	//后台修改一级分类的方法
		public String update() {
			categoryService.update(category);
			return "updateSuccess";
		}
}
