package org.oos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.oos.domain.Criteria;
import org.oos.domain.ProductImgVO;
import org.oos.domain.ProductOptionVO;
import org.oos.domain.ProductVO;
import org.oos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log

public class ProductServiceTests {

	@Setter(onMethod_=@Autowired)
	private ProductService service;
	@Test
	public void test1() {
		
		log.info(""+service.read(1L));
	}
	
	@Test
	public void test2() {
		Criteria cri=new Criteria();
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("criteria", cri);
	    map.put("sno",1L);
		log.info(""+service.getList(map));
	}
	
	@Test
	public void testInsert() {
		
		ProductVO vo=new ProductVO();
		ProductImgVO imgVo= new ProductImgVO();
		imgVo.setIname("t1");
		imgVo.setIpath("1111");
		imgVo.setPno(1L);
		imgVo.setUuid("asdas");
		ProductOptionVO optVo= new ProductOptionVO();
		optVo.setOpno(3L);
		optVo.setPno(1L);
		optVo.setQty(1L);
		optVo.setSize("X");
		vo.setPname("t1");
		vo.setPrice(1111L);
		vo.setSno(1L);
		List<ProductImgVO> iList=new ArrayList<>();
		iList.add(imgVo);
		List<ProductOptionVO> oList=new ArrayList<>();
		oList.add(optVo);
		
		vo.setImgList(iList);
		vo.setOptList(oList);
		log.info(""+service.register(vo));
	}
}
