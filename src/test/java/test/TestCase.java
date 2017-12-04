package test;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hzw.bean.User;
import com.hzw.dao.UserDAO;

public class TestCase {
	UserDAO dao = null;
	@Before
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		this.dao = ac.getBean("userDAO",UserDAO.class);
		
	}
	@Test
	public void test0() {
		User user = new User();
		user.setUid(UUID.randomUUID().toString());
		user.setUsername("黄校");
		user.setPassword("123456");
		user.setEmail("123@qq.com");
		dao.save(user);
	}
	
	@Test
	public void test1() {
		List<User> user = dao.getUser();
		for (User user2 : user) {
			System.out.println(user2);
		}
	}
	@Test
	public void test2() {
		User user = dao.getOne("root");
		System.out.println(user);
	}
}
