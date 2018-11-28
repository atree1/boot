package org.oos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.oos.domain.CartVO;
import org.oos.mapper.CartMapper;
import org.oos.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@MapperScan("org.oos.mapper")
public class CartTests {

	@Setter(onMethod_=@Autowired)
	private CartService service;
	@Setter(onMethod_=@Autowired)
	private CartMapper mapper;
	
	@Test
	public void insert() {
		for(int i=0;i<10;i++) {
		CartVO vo=new CartVO();
		vo.setMid("test");
		vo.setOpno(1L);
		vo.setQty(3L);
		vo.setPno(1L);
		//log.info(""+mapper.insert(vo));
	
		}
	}
	
	@Test
	public void get() {
		log.info(""+service.get(1L));
	}
}
