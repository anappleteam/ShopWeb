package bupt.sse.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import bupt.sse.shop.categorysecond.dao.CategorySecondDao;
import bupt.sse.shop.categorysecond.vo.CategorySecond;
import bupt.sse.shop.utils.PageBean;

@Transactional
public class CategorySecondService {
	private CategorySecond categorySecond = new CategorySecond();
	// 注入二级分类的dao
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	// 业务层 分页查询二级分类
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		// 设置当前页数
		pageBean.setPage(page);
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置页面显示数据的集合:
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	public CategorySecond findByCsid(Integer csid){
		return categorySecondDao.findByCsid(csid);
	}
	public void delete(CategorySecond categorySecond){
		categorySecondDao.delete(categorySecond);
		
	}
	public void save(CategorySecond categorySecond){
		categorySecondDao.save(categorySecond);
	}
	public void update(CategorySecond categorySecond){
		categorySecondDao.update(categorySecond);
	}
	//业务层修改二级分类
	public List<CategorySecond> findAll(){
		return categorySecondDao.findAll();
		
	}
}
