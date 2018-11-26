package org.oos.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.KakaoPayInfoDTO;
import org.oos.domain.KakaoPayReadyDTO;
import org.oos.domain.OrderDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class KakaopayService {
	private static final String HOST = "https://kapi.kakao.com";
	private KakaoPayInfoDTO kakaoPayInfoDTO;
	private KakaoPayReadyDTO kakaoPayReadyDTO;
	
	@Setter(onMethod_ = @Autowired)
	private OrderDetailService orderService;
	@Setter(onMethod_ = @Autowired)
	private MemberService memberService;
	@Setter(onMethod_ = @Autowired)
	private StoreService storeService;
	
	private List<OrderDetailVO> orderList;
	public String kakaoPayReady(List<OrderDetailVO> order) {
		this.orderList=order;
		String mid=orderList.get(0).getMid();
		Long sno=orderList.get(0).getSno();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK 80751743222df1787be1cbbc946d6ea5");
		headers.add("Accpet", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
		
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id",""+sno);
		params.add("partner_user_id",mid);
		params.add("item_name", orderList.get(0).getProduct().getPname());
		params.add("quantity",""+orderList.get(0).getQty());
		params.add("total_amount", ""+orderList.get(0).getProduct().getPrice()*orderList.get(0).getQty());
		params.add("tax_free_amount", "0");
		params.add("approval_url", "http://localhost:8080/kakaopay/success");
		params.add("cancel_url", "http://localhost:8080/kakaopay/cancel");
		params.add("fail_url", "http://localhost:8080/kakaopay/fail");

		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

		try {
			kakaoPayReadyDTO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body,
					KakaoPayReadyDTO.class);
			return kakaoPayReadyDTO.getNext_redirect_pc_url();
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/pay";
	}

	public Map<String,Object> kakaoPayInfo(String pg_token) {
		String mid=orderList.get(0).getMid();
		Long sno=orderList.get(0).getSno();
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK 80751743222df1787be1cbbc946d6ea5");
		headers.add("Accpet", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", kakaoPayReadyDTO.getTid());
		params.add("partner_order_id",""+sno );
		params.add("partner_user_id", mid);

		params.add("total_amount",""+orderList.get(0).getProduct().getPrice()*orderList.get(0).getQty());
		params.add("pg_token", pg_token);
		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);
		Map<String, Object> map = new HashMap<>();
		try {
			kakaoPayInfoDTO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body,
					KakaoPayInfoDTO.class);
			log.info("" + kakaoPayInfoDTO);
			
			map.put("kakao", kakaoPayInfoDTO);
			map.put("orderList", orderList);
			return map;
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
