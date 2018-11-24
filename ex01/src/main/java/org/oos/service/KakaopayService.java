package org.oos.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.oos.domain.KakaoPayDTO;
import org.oos.domain.KakaoPayReadyDTO;
import org.oos.domain.MemberVO;
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

@Service
public class KakaopayService {
	private static final String HOST = "https://kapi.kakao.com";
	private KakaoPayDTO kakaoPayDTO;
	private KakaoPayReadyDTO kakaoPayReadyDTO;
	@Setter(onMethod_ = @Autowired)
	private OrderDetailService service;

	public String kakaoPayReady(OrderDetailVO odvo,MemberVO mvo) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK 80751743222df1787be1cbbc946d6ea5");
		headers.add("Accpet", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", ""+odvo.getOno());
		params.add("partner_user_id", mvo.getMid());
		params.add("item_name", odvo.getProduct().getPname());
		params.add("quantity", ""+odvo.getQty());
		params.add("total_amount", ""+odvo.getQty()*odvo.getProduct().getPrice());
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

	public KakaoPayDTO kakaoPayInfo(String pg_token) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK 80751743222df1787be1cbbc946d6ea5");
		headers.add("Accpet", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", kakaoPayReadyDTO.getTid());
		params.add("partner_order_id", "1001");
		params.add("partner_user_id", "atree");
		params.add("pg_token", pg_token);
		params.add("total_amount", "1000");

		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);
		try {
			kakaoPayDTO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayDTO.class);

			return kakaoPayDTO;
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
