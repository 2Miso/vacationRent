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

        // HTTP Header ����
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "KakaoAK " + secretKey);

        // ���� �غ� ��û �Ķ���� ���� (����)
        Map<String, String> params = new HashMap<>();
        params.put("cid", cid);  // ������ �ڵ�
        params.put("partner_order_id", vo.getReservCode());  // �ֹ���ȣ, ���� ������ȣ Ȱ�� ����
        params.put("partner_user_id", String.valueOf(vo.getUserId()));  // ����� ���̵�
        params.put("item_name", "���� ���� ����");
        params.put("quantity", "1");
        params.put("total_amount", String.valueOf(vo1.getPrice())); // �����ݾ� (����VO�� �ѱݾ� �ʵ� �ִٰ� ����)
        params.put("tax_free_amount", "0");
        params.put("approval_url", "http://yourdomain.com/payment/kakao_success");  // ���� ���� �� redirect URL
        params.put("cancel_url", "http://yourdomain.com/payment/kakao_cancel");   // ���� ��� �� redirect URL
        params.put("fail_url", "http://yourdomain.com/payment/kakao_fail");       // ���� ���� �� redirect URL

        // HttpEntity�� Header�� Params ���
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, headers);

        // POST ��û ������
        ResponseEntity<Map> response = restTemplate.postForEntity(URI.create(kakaoPayReadyUrl), entity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map body = response.getBody();
            // ���� �غ� ���� �� ���信�� ���� ������ URL ����
            String redirectUrl = (String) body.get("next_redirect_pc_url");
            return redirectUrl;
        } else {
            throw new RuntimeException("īī������ ���� �غ� ����: " + response.getStatusCode());
        }
    }
}
