package bupt.sse.shop.category.service;

import java.util.List;

import javax.transaction.Transactional;

import bupt.sse.shop.category.dao.CategoryDao;
import bupt.sse.shop.category.vo.Category;
@Transactional
public class CategoryService {
	//注入categoryDao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	//业务层保存一级分类的方法
	public void save(Category category) {
		categoryDao.save(category);
	}
	//业务层根据cid查询一级分类
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}
	//业务层删除一级分类的方法
	public void delete(Category category) {
		categoryDao.delete(category);
		
	}
	//业务层修改一级分类的方法
	public void update(Category category) {
		categoryDao.update(category);
		
	}
	
}
