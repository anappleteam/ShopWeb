package bupt.sse.shop.hibernate.test;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bupt.sse.shop.category.dao.CategoryDao;
import bupt.sse.shop.category.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestCache1 {
	@Autowired
	private CategoryService categoryService;
	@Test
	public void testCache1()
	{
		categoryService.findAll();
	}
	
}
