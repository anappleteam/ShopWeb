package bupt.sse.shop.categorysecond.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.category.service.CategoryService;
import bupt.sse.shop.category.vo.Category;
import bupt.sse.shop.categorysecond.service.CategorySecondService;
import bupt.sse.shop.categorysecond.vo.CategorySecond;
import bupt.sse.shop.utils.PageBean;

//二级分类管理的action
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
	// 模型驱动使用的对象
	private CategorySecond categorySecond = new CategorySecond();

	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}

	// 注入二级Service
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// 接收page
	private Integer page;

	public void setPage(int page) {
		this.page = page;
	}
	//注入一级分类的service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 查询所有的二级分类
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		// 将pageBean数据存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	public String addPage(){
		//查询所有一级分类
		 List<Category> cList=categoryService.findAll();
		 //把数据显示到页面的下拉列表中
		 ActionContext.getContext().getValueStack().set("cList", cList);
		 //页面跳转
		 return "addPage";
	}
	//删除二级分类
	public String delete(){		
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}	
	public String save(){
		categorySecondService.save(categorySecond);
		return "save";
	}
	public String edit(){
		//根据CSID查询二级分类对象
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		//查询一级分类
		List<Category> cList= categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}

}
