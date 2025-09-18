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
import com.rent.vaca.user.UserVO;

import lombok.RequiredArgsConstructor;

public interface UserService {
	//ȸ������ �߻�޼���
	int join(UserVO vo);
	
	//�α��� �߻�޼���
	UserVO login(UserVO vo);
	
	//�̸��� �ߺ�üũ �߻�޼���
	int emailCheck(String email);
	
	UserVO findEmail(UserVO vo);
	
	UserVO pwauto(UserVO vo);
	
	UserVO findPw(UserVO vo);
	
	int kakaojoin(String accesstoken);
	
	UserVO kakaologin(String accesstoken);
	

		public static String getAccessToken (String authorize_code) {
			String access_Token = "";
			String refresh_Token = "";
			String reqURL = "https://kauth.kakao.com/oauth/token";

			try {
				URL url = new URL(reqURL);
	            
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
	            
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				StringBuilder sb = new StringBuilder();
				sb.append("grant_type=authorization_code");
	            
				sb.append("&client_id=d689d303f45b6b16979a1e2bcb396fe8"); //본인이 발급받은 key
				sb.append("&redirect_uri=http://localhost:8080/vaca/login/kakaocallback"); // 본인이 설정한 주소
	            
				sb.append("&code=" + authorize_code);
				bw.write(sb.toString());
				bw.flush();
	            
				// 결과 코드가 200이라면 성공
				int responseCode = conn.getResponseCode();
				System.out.println("responseCode : " + responseCode);
	            
				// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				String result = "";
	            
				while ((line = br.readLine()) != null) {
					result += line;
				}
				System.out.println("response body : " + result);
	            
				// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);
	            
				access_Token = element.getAsJsonObject().get("access_token").getAsString();
				refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
	            
				System.out.println("access_token : " + access_Token);
				System.out.println("refresh_token : " + refresh_Token);
	            
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return access_Token;
		
	}
		
		public static HashMap<String, Object> getUserInfo(String access_Token) {

			// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
			HashMap<String, Object> userInfo = new HashMap<String, Object>();
			String reqURL = "https://kapi.kakao.com/v2/user/me";
			try {
				URL url = new URL(reqURL);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");

				// 요청에 필요한 Header에 포함될 내용
				conn.setRequestProperty("Authorization", "Bearer " + access_Token);

				int responseCode = conn.getResponseCode();
				System.out.println("responseCode : " + responseCode);

				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String line = "";
				String result = "";

				while ((line = br.readLine()) != null) {
					result += line;
				}
				System.out.println("response body : " + result);

				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);
				
				String kakaoid = element.getAsJsonObject().get("id").getAsString();
				JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
				JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

				String nickname = properties.getAsJsonObject().get("nickname").getAsString();

				System.out.println("여기서부터닉네임이랑카카오아이디가나옵니다.");
				System.out.println(nickname);
				System.out.println(kakaoid);
				userInfo.put("nickname", nickname);
				userInfo.put("kakaoid", kakaoid);


			} catch (IOException e) {
				e.printStackTrace();
			}
			return userInfo;
		}
		
		//====================================================================================================================
	
		    final  String NAVER_CLIENT_ID ="kefXRLW29KDelSqZkpjS";
		    final  String NAVER_CLIENT_SECRET ="cbaN8XFOPm";
		    final  String NAVER_BASE_URL ="https://nid.naver.com/oauth2.0/authorize";
		    final  String NAVER_TOKEN_BASE_URL ="https://oauth2.googleapis.com/token";
		    final  String NAVER_CALLBACK_URL ="http://localhost:8083/vaca/login/navercallback";

		    /**
		     * 네아로 url Get
		     * @return
		     */
		    
		    public static String getRequestLoginUrl() {
		        final String state = new BigInteger(130, new SecureRandom()).toString();

		        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
		            requestParam.add("response_type", "code");
		            requestParam.add("state", state);
		            requestParam.add("client_id", NAVER_CLIENT_ID);
		            requestParam.add("redirect_uri", NAVER_CALLBACK_URL);

		        return UriComponentsBuilder.fromUriString(NAVER_BASE_URL)
		                                                        .queryParams(requestParam)
		                                                        .build().encode()
		                                                        .toString();
		    }
		    
		    /**
		     * 네아로 콜백 - AccessToken 
		     * @param code
		     * @param state
		     * @return
		     */
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

}


