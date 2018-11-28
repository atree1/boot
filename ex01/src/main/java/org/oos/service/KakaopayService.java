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

	private static List<OrderDetailVO> finalOrderList;
	
	private static final String HOST = "https://kapi.kakao.com";
	private KakaoPayInfoDTO kakaoPayDTO;
	private KakaoPayReadyDTO kakaoPayReadyDTO;
	
	@Setter(onMethod_ = @Autowired)
	private OrderDetailService service;

	public String kakaoPayReady(Long totalPrice, List<OrderDetailVO> orderList) {
		
		finalOrderList = orderList;
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK f3071c0fe499cf06231fd365c1cc4d30");
		headers.add("Accpet", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", "1111");
		params.add("partner_user_id", finalOrderList.get(0).getMid());
		params.add("item_name", finalOrderList.get(0).getProduct().getPname());
		params.add("quantity", finalOrderList.get(0).getQty()+"");
		params.add("total_amount", totalPrice+"");
		params.add("tax_free_amount", "0");
		params.add("approval_url", "http://localhost:8080/kakaopay/success");
		params.add("cancel_url", "http://localhost:8080/kakaopay/cancel");
		params.add("fail_url", "http://localhost:8080/kakaopay/fail");

		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

		try {
			kakaoPayReadyDTO = restTemplate.postForObject(new URI("https://kapi.kakao.com/v1/payment/ready"), body,
					KakaoPayReadyDTO.class);
			
			return kakaoPayReadyDTO.getNext_redirect_pc_url();
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return "/pay";
	}

	public Map<String, Object> kakaoPayInfo(String pg_token) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK f3071c0fe499cf06231fd365c1cc4d30");
		headers.add("Accpet", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", kakaoPayReadyDTO.getTid());
		params.add("partner_order_id", "1111");
		params.add("partner_user_id",finalOrderList.get(0).getMid());

		params.add("pg_token", pg_token);
	
		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);
		Map<String, Object> map = new HashMap<>();
		try {

			kakaoPayDTO = restTemplate.postForObject(new URI("https://kapi.kakao.com/v1/payment/approve"), body,
					KakaoPayInfoDTO.class);
			
			map.put("kakao", kakaoPayDTO);
			map.put("orderList", finalOrderList);
			
			
			return map;
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
