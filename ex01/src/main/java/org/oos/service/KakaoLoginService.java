package org.oos.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.oos.domain.LoginDTO;
import org.oos.domain.MemberVO;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.java.Log;

@Service
@Log
public class KakaoLoginService {

	public JsonNode getAccessToken(String autorize_code) {
		final String RequestUrl = "https://kauth.kakao.com/oauth/token";

		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", "8c3004a3744539ba3d19abea8983dff6")); // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/kakaologin")); // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", autorize_code)); // 로그인 과정중 얻은 code 값

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		JsonNode returnNode = null;

		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();

			System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
			System.out.println("Post parameters : " + postParams);
			System.out.println("Response Code : " + responseCode);

			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());

			System.out.println("response: " + response);
			System.out.println("returnNode: " + returnNode);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}

		return returnNode;

	}

	public JsonNode getKakaoUserInfo(String autorize_code) {

		final String RequestUrl = "https://kapi.kakao.com/v2/user/me";

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);

		// add header
		post.addHeader("Authorization", "Bearer " + autorize_code);
		System.out.println("autorize_code: " + autorize_code);
		System.out.println("post: " + post);
		JsonNode returnNode = null;

		try {
			final HttpResponse response = client.execute(post);
			System.out.println("response: " + response);
			final int responseCode = response.getStatusLine().getStatusCode();

			System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
			System.out.println("Response Code : " + responseCode);

			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
			System.out.println("returnNode: " + returnNode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;
	}

	public MemberVO changeData(JsonNode userInfo) {
		MemberVO vo = new MemberVO();
		System.out.println("userInfo: " + userInfo);
		// vo.setStatus('O');
		vo.setMid(userInfo.asText());
		JsonNode kakao_account = userInfo.path("kakao_account");
		System.out.println("kakao_account는?: " + kakao_account);

		if (kakao_account.path("is_email_verified").asText().equals("true")) { // 이메일 받기 허용 한 경우
			vo.setEmail(kakao_account.path("email").asText()); // email -> vo 넣기

		} else { //

		}
//
//			JsonNode properties = userInfo.path("properties"); // 추가정보 받아오기
//			if (properties.has("nickname")) {
//				vo.setNickname(properties.path("nickname").asText());
//			}
//			

		return vo;
	}

}
