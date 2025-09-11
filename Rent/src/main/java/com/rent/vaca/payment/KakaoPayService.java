package com.rent.vaca.payment;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rent.vaca.acco.AccoHasFacilVO;
import com.rent.vaca.reserv.ReservVO;

@Service
public class KakaoPayService {
	
	@Value("${payment.kakao.secretKey}")
    private String secretKey;

    @Value("${payment.kakao.cid}")
    private String cid;

    private final String kakaoPayReadyUrl = "https://kapi.kakao.com/v1/payment/ready";

    public String readyToPay(ReservVO vo, AccoHasFacilVO vo1) {
        RestTemplate restTemplate = new RestTemplate();

        // HTTP Header 세팅
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "KakaoAK " + secretKey);

        // 결제 준비 요청 파라미터 세팅 (예시)
        Map<String, String> params = new HashMap<>();
        params.put("cid", cid);  // 가맹점 코드
        params.put("partner_order_id", vo.getReservCode());  // 주문번호, 예약 고유번호 활용 가능
        params.put("partner_user_id", String.valueOf(vo.getUserId()));  // 사용자 아이디
        params.put("item_name", "숙소 예약 결제");
        params.put("quantity", "1");
        params.put("total_amount", String.valueOf(vo1.getPrice())); // 결제금액 (예약VO에 총금액 필드 있다고 가정)
        params.put("tax_free_amount", "0");
        params.put("approval_url", "http://yourdomain.com/payment/kakao_success");  // 결제 성공 시 redirect URL
        params.put("cancel_url", "http://yourdomain.com/payment/kakao_cancel");   // 결제 취소 시 redirect URL
        params.put("fail_url", "http://yourdomain.com/payment/kakao_fail");       // 결제 실패 시 redirect URL

        // HttpEntity에 Header와 Params 담기
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, headers);

        // POST 요청 보내기
        ResponseEntity<Map> response = restTemplate.postForEntity(URI.create(kakaoPayReadyUrl), entity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map body = response.getBody();
            // 결제 준비 성공 시 응답에서 결제 페이지 URL 추출
            String redirectUrl = (String) body.get("next_redirect_pc_url");
            return redirectUrl;
        } else {
            throw new RuntimeException("카카오페이 결제 준비 실패: " + response.getStatusCode());
        }
    }
}
