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
	
}
