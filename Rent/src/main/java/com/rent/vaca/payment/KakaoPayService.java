package com.rent.vaca.payment;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
 
import com.rent.vaca.acco.AccoHasFacilVO;
import com.rent.vaca.reserv.ReservVO;

@Service
public class KakaoPayService {
	
	private static final Logger log = LoggerFactory.getLogger(KakaoPayService.class);

    private final String kakaoPayReadyUrl = "https://kapi.kakao.com";

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;
    
    public String kakaoPayReady(ReservVO vo, AccoHasFacilVO vo1) {
        
    	RestTemplate restTemplate = new RestTemplate();

        // HTTP Header 세팅
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "3d7b04ea706625424c698a4d05fbdc24");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

     // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", vo.getReservCode());
        params.add("partner_user_id", vo.getEmail());
        params.add("item_name", vo1.getDescription());
        params.add("quantity", "1");
        params.add("total_amount", String.valueOf(vo1.getPrice()));
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://localhost:8080/Rent/payment/payment_ok");
        params.add("cancel_url", "http://localhost:8080/Rent/payment/payment_fail");
        params.add("fail_url", "http://localhost:8080/Rent/payment/payment_");
        
        
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
		try {
			
		    kakaoPayReadyVO = restTemplate.postForObject(new URI(kakaoPayReadyUrl + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
		
		    log.info("" + kakaoPayReadyVO);
		            
        	return kakaoPayReadyVO.getNext_redirect_pc_url();
		 
        }catch(RestClientException e){
        	e.printStackTrace();
        }catch (URISyntaxException e) {
		    e.printStackTrace();
		}
		return "/payment/payment_fail";
    }
    
    public KakaoPayApprovalVO kakaoPayInfo(
    		String pg_token, ReservVO vo, AccoHasFacilVO vo1) {
    	 
        log.info("카카오페이 승인 응답: {}", kakaoPayApprovalVO);
        
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "3d7b04ea706625424c698a4d05fbdc24");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
 
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", vo.getReservCode());
        params.add("partner_user_id", vo.getEmail());
        params.add("pg_token", pg_token);
        params.add("total_amount", String.valueOf(vo1.getPrice()));
        
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        try {
        	
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(kakaoPayReadyUrl + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            
            log.info("" + kakaoPayApprovalVO);
          
            return kakaoPayApprovalVO;
        
        } catch(RestClientException e){
            e.printStackTrace();
        } catch(URISyntaxException e){
            e.printStackTrace();
        }
        
        return null;
    }
    
}
