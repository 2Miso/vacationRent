package com.rent.vaca.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rent.vaca.notice.NoticeVO;
import com.rent.vaca.user.UserVO;

import lombok.RequiredArgsConstructor;

public interface UserService {

	int join(UserVO vo);
	
	UserVO login(UserVO vo);
	
	int emailCheck(String email);
	
	int phoneCheck(String phone);
	
	UserVO findEmail(UserVO vo);
	
	UserVO pwauto(UserVO vo);
	
	UserVO findPw(UserVO vo);
	
	int kakaojoin(String accesstoken);
	
	UserVO kakaologin(String accesstoken);
	
	int naverjoin(String accesstoken);
	
	UserVO naverlogin(String accesstoken);
	
	UserVO useraccountupdate(UserVO vo);
	
	List<Object> getPostsByUserId(int userId);
	
	List<Object> getReservByUserId(int userId);
	
	UserVO useraccount(UserVO vo);
	
	UserVO useraccountchange(UserVO vo);
	
	UserVO useraccountsocial(UserVO vo);

//============================================================================================================================
//카카오 소셜로그인 
	public static String getAccessToken (String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=d689d303f45b6b16979a1e2bcb396fe8");
			sb.append("&redirect_uri=http://jjezen.cafe24.com/Rent/login/kakaocallback"); 
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();
	            
			int responseCode = conn.getResponseCode();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
	            
			while ((line = br.readLine()) != null) {
				result += line;
			}
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);	            
				access_Token = element.getAsJsonObject().get("access_token").getAsString();
				refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();	    
				br.close();
				bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return access_Token;
	}
		
	public static HashMap<String, Object> getUserInfo(String access_Token) {		
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);
			int responseCode = conn.getResponseCode();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);				
			String kakaoid = element.getAsJsonObject().get("id").getAsString();
			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			userInfo.put("nickname", nickname);
			userInfo.put("kakaoid", kakaoid);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInfo;
		}
		
//====================================================================================================================
//네이버 소셜로그인	
	String NAVER_CLIENT_ID ="kefXRLW29KDelSqZkpjS";
	String NAVER_CLIENT_SECRET ="txfxP6iiA4";
	String NAVER_BASE_URL ="https://nid.naver.com/oauth2.0/authorize";
	String NAVER_TOKEN_BASE_URL ="https://nid.naver.com/oauth2.0/token";
	String NAVER_CALLBACK_URL ="http://jjezen.cafe24.com/Rent/login/navercallback";
		    
	public static String getRequestLoginUrl() {
		final String state = new BigInteger(130, new SecureRandom()).toString();
		MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
			requestParam.add("response_type", "code");
				requestParam.add("state", state);
				requestParam.add("client_id", NAVER_CLIENT_ID);
				requestParam.add("redirect_uri", NAVER_CALLBACK_URL);
		return UriComponentsBuilder.fromUriString(NAVER_BASE_URL).queryParams(requestParam).build().encode().toString();
	}
		    

	public static ResponseEntity<?> requestAccessToken(String code, String state) {
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
			requestBody.add("code", code);
			requestBody.add("state", state);
			requestBody.add("client_id", NAVER_CLIENT_ID);
			requestBody.add("client_secret", NAVER_CLIENT_SECRET);
		    requestBody.add("grant_type", "authorization_code");
		    return new RestTemplate().postForEntity(NAVER_TOKEN_BASE_URL, requestBody, Map.class);
		    
	}
//==========================================================================================
	//이건 제가 만든 코드가 아닙니다.
	public List<NoticeVO> selectQuestionList();
}


