package com.rent.vaca.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents
;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.json.JsonObjectParser;
import com.google.common.net.MediaType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rent.vaca.notice.NoticeVO;
import com.rent.vaca.user.Response;
import com.rent.vaca.user.UserService;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//약관동의페이지
	@RequestMapping(value="/user/join/agree", method = RequestMethod.GET)
	public String agree() {
		return "user/join/agree";
	}
	
	//회원가입 페이지
	@RequestMapping(value="/user/join/form", method = RequestMethod.GET)
	public String join() {
		return "user/join/form";
	}
	
	//회원가입 기능구현
	@RequestMapping(value="user/join/form", method = RequestMethod.POST) 
	public String join(UserVO vo) {
		System.out.println("버튼눌림");
		userService.join(vo);
		return "redirect:/user/join/finish";
	}
	
	//이메일중복체크 기능구현
	@RequestMapping(value="user/join/form/emailCheck", method = RequestMethod.POST) 
	@ResponseBody
	public Response emailCheck(@RequestParam("email") String email) {
		System.out.println("코드가 작동됨");
		int cnt = userService.emailCheck(email);
		System.out.println(cnt);
		Response res = new Response();
		res.setCode(cnt);
		res.setResult("success");
		return res;
	}
	
	//회원가입완료 페이지 
	@RequestMapping(value="/user/join/finish", method = RequestMethod.GET) 
	public String finish() {
		return "user/join/finish";
	}
	
	//이메일찾기 페이지
	@RequestMapping(value="/user/find/email", method = RequestMethod.GET) 
	public String findEmail() {
		return "user/find/email";
	}
	
	//이메일찾기페이지 기능 
	@RequestMapping(value="/user/find/email", method = RequestMethod.POST) 
	public String findEmail(UserVO vo,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		try {
			String email = userService.findEmail(vo).getEmail();
			System.out.println(userService.findEmail(vo).getEmail());
			model.addAttribute("email", email);
			response.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = response.getWriter();
		    writer.println("<script>");
		    writer.println("alert('가입한 이메일은 "+email+"입니다.');");
		    writer.println("window.location.href = '/vaca/login/email';");
		    writer.println("</script>");
		    writer.flush(); // 버퍼 비우기

		}catch(NullPointerException e){
			model.addAttribute("email", "입력한 정보가 일치하지 않거나 없는 정보입니다");
			response.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = response.getWriter();
		    writer.println("<script>");
		    writer.println("alert('입력한 정보가 일치하지 않거나 없는 정보입니다');");
		    writer.println("window.location.href = '/vaca/user/find/email';");
		    writer.println("</script>");
		    writer.flush(); // 버퍼 비우기

		}finally{

		}
		return null;

	}
	
	//비밀번호찾기 페이지
	@RequestMapping(value="/user/find/pw", method = RequestMethod.GET) 
	public String findPw() {
		return "user/find/pw";
	}
	
	//비밀번호찾기 메일보냈음페이지 
	@RequestMapping(value="/user/find/mailsend", method = RequestMethod.GET) 
	public String mailSend() {
		return "user/find/mailsend";
	}

//================================================================================================
	//카카오 소셜로그인 성공...
	//카카오는 비즈회원(사업자등록증)없으면 가져올수있는값이 2개정도밖에없습니다
	@RequestMapping(value="/login/kakaocallback", method=RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code,HttpSession session,UserVO vo,Model model) throws Exception {
		System.out.println("#########" + code);
		String access_Token = UserService.getAccessToken(code);
		System.out.println("###access_Token#### : " + access_Token);
		HashMap<String, Object> userInfo = UserService.getUserInfo(access_Token);
		System.out.println("###access_Token#### : " + access_Token);
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		System.out.println("###kakaoid#### : " + userInfo.get("kakaoid"));
		String kakaoid = userInfo.get("kakaoid").toString();
			try {
				UserVO user = userService.kakaologin(kakaoid);
				if(user != null) {
					System.out.println("존재함");
					System.out.println(user);
					session.setAttribute("user", user); 
					System.out.println("유저확인하기"+user);
					System.out.println("유저확인하기"+user.getNickname());
					return "main/main";
					
				}else {
					System.out.println("user값없ㄷ을때");
					userService.kakaojoin(kakaoid);
					user = userService.kakaologin(kakaoid);
					System.out.println("유저확인하기"+user);
					session.setAttribute("user", user); 
					return "main/main";
				}
				
			}catch(Exception e) {
				userService.kakaojoin(access_Token);
				UserVO user = userService.kakaologin(access_Token);
				session.setAttribute("user", user); 
				return "main/main";
			}finally {
	
			}
    	}
	
//=======================================================================================================	
		//네이버 소셜로그인 완성....
		
	
	    @GetMapping("/login/naver")//네이버 로그인 창을 띄웁니다
	    public void naverLogin(HttpServletRequest request, HttpServletResponse response){
	        final String loginUrl = UserService.getRequestLoginUrl();
	        try {
	            response.sendRedirect(loginUrl);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	   @GetMapping("/login/navercallback") //네이버 로그인 값을 가져옵니다
	  // @ResponseBody
	    public Object requestAccessCallback( @RequestParam(value = "code") String authCode, 
	                                         @RequestParam(value = "state") String state,HttpSession session,UserVO vo){

		   
	      ResponseEntity<?> responseEntity = UserService.requestAccessToken(authCode, state);
	      System.out.println("컬백리퀘스트까지실행");
	      Object responseMessage = responseEntity.getBody();
	      System.out.println("리스폰스엔티티실행"+responseEntity);
	      Map<String, String> map = (Map<String, String>) responseMessage;
	      String accesstokenparsed = map.get("access_token");
	      String token = accesstokenparsed; // 네이버 로그인 접근 토큰;
	      String header = "Bearer " + token; // Bearer 다음에 공백 추가
	      String apiURL = "https://openapi.naver.com/v1/nid/me";
	      Map<String, String> requestHeaders = new HashMap<>();
	      requestHeaders.put("Authorization", header);
	      String responseBody = get(apiURL,requestHeaders);
	      System.out.println(responseBody);
	      
	      
	      
	      
	      
	      
	      
	      //naverid에 json파싱한값 넣어주는코드
	      String naverid = "sdfasdfasdsdfa";// 네이버아이디 가져온값 
	      ObjectMapper objectMapper = new ObjectMapper();
	      try {
	    	  System.out.println("============================================================================================");
			Map<String, Object> responsebodymap = objectMapper.readValue(responseBody, Map.class);
			System.out.println(responsebodymap);
			System.out.println(responsebodymap.get("response"));
			Object responsebodyresponse = responsebodymap.get("response");
			 Map<String, String> responsebodyresponseid = (Map<String, String>) responsebodyresponse;
			  naverid = responsebodyresponseid.get("id");
			    System.out.println(naverid);
			 System.out.println("============================================================================================");
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
	      
	      //로그인 처리 코드 
	      	try {
				UserVO user = userService.naverlogin(naverid);
				if(user != null) {
					System.out.println("존재함");
					System.out.println(user);
					session.setAttribute("user", user); 
					System.out.println("유저확인하기"+user);
					System.out.println("유저확인하기"+user.getNickname());
					return "main/main";
				}else {
					System.out.println("user값없ㄷ을때");
					userService.naverjoin(naverid);
					user = userService.naverlogin(naverid);
					System.out.println("유저확인하기"+user);
					session.setAttribute("user", user); 
					return "main/main";
				}
				
			}catch(Exception e) {
				return "main/main";
			}finally {
	
			}
	   }
	   

	   
	   //네이버 명세서에서 배껴온 코드블럭 3개(뭔가 하는데 뭘 하는지 잘 모르겟음..)
	   private static String get(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }
	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                return readBody(con.getInputStream());
	            } else { // 에러 발생
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }
	      
	   private static String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);
	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();
	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }
	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	    }

	    private static HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    } 
   



//=====================================================================================================================
	  
//
//	      
//	      
//	      //naverid에 json파싱한값 넣어주는코드
//	      String naverid = "sdfasdfasdsdfa";// 네이버아이디 가져온값 
//	      ObjectMapper objectMapper = new ObjectMapper();
//	      try {
//	    	  System.out.println("============================================================================================");
//			Map<String, Object> responsebodymap = objectMapper.readValue(responseBody, Map.class);
//			System.out.println(responsebodymap);
//			System.out.println(responsebodymap.get("response"));
//			Object responsebodyresponse = responsebodymap.get("response");
//			 Map<String, String> responsebodyresponseid = (Map<String, String>) responsebodyresponse;
//			  naverid = responsebodyresponseid.get("id");
//			    System.out.println(naverid);
//			 System.out.println("============================================================================================");
//		} catch (JsonMappingException e1) {
//			e1.printStackTrace();
//		} catch (JsonProcessingException e1) {
//			e1.printStackTrace();
//		}
//	      
//	      //로그인 처리 코드 
//	      	try {
//				UserVO user = userService.naverlogin(naverid);
//				if(user != null) {
//					System.out.println("존재함");
//					System.out.println(user);
//					session.setAttribute("user", user); 
//					System.out.println("유저확인하기"+user);
//					System.out.println("유저확인하기"+user.getNickname());
//					return "main/main";
//				}else {
//					System.out.println("user값없ㄷ을때");
//					userService.naverjoin(naverid);
//					user = userService.naverlogin(naverid);
//					System.out.println("유저확인하기"+user);
//					session.setAttribute("user", user); 
//					return "main/main";
//				}
//				
//			}catch(Exception e) {
//				return "main/main";
//			}finally {
//	
//			}
//	   }
//	   
	    
//==================================================================================================================
	//로그인 메인 페이지 
	@RequestMapping(value="/login/main", method = RequestMethod.GET) 
	public String main() {
		return "login/main";
	}
	
	//로그인 페이지
	@RequestMapping(value="/login/email", method = RequestMethod.GET) 
	public String login() {
		return "login/email";
	}
	
	//로그인 기능구현
	@RequestMapping(value="/login/email", method = RequestMethod.POST) 
	public String login(UserVO vo ,HttpSession session,Model model,HttpServletResponse response) throws IOException {
		UserVO user = userService.login(vo);
		//로그인 실패
		if(user == null) {
			response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('비밀번호가 일치하지 않거나 없는 계정입니다.');");
		    out.println("history.back();"); // 또는 다른 페이지로 이동
		    out.println("</script>");
		    out.flush();
		    out.close();
		}
		//로그인 성공
		session.setAttribute("user", user); 
		return "redirect:/main/main";
	}
	
	//로그아웃 구현
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/main/main";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//메인으로 이동시켜주는메소드
	@RequestMapping(value="main/main", method = RequestMethod.GET) 
	public String main(HttpSession session) {
		session.getAttribute("user");
		return "main/main";
	}
	
	
	
	
	
	
//================================================================================================================================	
	

//	//유저 개인 정보 수정 기능 페이지 (이메일회원)
//	@RequestMapping(value="/user/mypage/account", method = RequestMethod.GET) 
//	public String useraccount(UserVO vo, HttpSession session) throws IOException {
//		try {
//			vo = (UserVO) session.getAttribute("user");
//			//1.로그인이 되어있는지 체크한다
//			if(vo == null) {
//				return "redirect:/login/main";		//2.소셜로그인으로 로그인했는지 체크한다.	
//			}else if(vo.getSocial() == "y") { 
//				return "redirect:/user/mypage/account-social"; 
//			}	
//		}catch(Exception e) {
//			
//		}finally {
//			
//		}
//		return null;
//	}
//	
//	//유저 개인 정보 수정 기능 페이지 (소셜로그인)
//	@RequestMapping(value="/user/mypage/account-social", method = RequestMethod.GET) 
//	public String useraccountsocial(UserVO vo, HttpSession session) throws IOException {
//		try {
//			vo = (UserVO) session.getAttribute("user");
//			//1.로그인이 되어있는지 체크한다
//			if(vo == null) {
//				return "redirect:/login/main";		//2.소셜로그인으로 로그인했는지 체크한다.	
//			}else if(vo.getSocial() == "y") { 
//				return "redirect:/user/mypage/account-social"; 
//			}	
//		}catch(Exception e) {
//			
//		}finally {
//			
//		}
//		return null;
//	}
//	
//	//유저 개인 정보 수정 기능 (이메일회원)
//	@RequestMapping(value="/user/mypage/account", method = RequestMethod.POST) 
//	public Response useraccount(UserVO vo,HttpSession session,HttpServletResponse response) {
//		try {
//			UserVO user = userService.useraccount(vo);
//			
//		}catch(Exception e) {
//			response.setContentType("text/html;charset=UTF-8");
//		    PrintWriter out = response.getWriter();
//		    out.println("<script>");
//		    out.println("alert('정보 수정 중 오류가 발생했습니다. 다시 시도해주세요');");
//		    out.println("</script>");
//		    out.flush();
//		    out.close();
//		}
//		return null;
//	}
//	
//
//
//	
//	//유저 개인 정보 수정 기능 (이메일회원)
//	@RequestMapping(value="/user/mypage/account-social", method = RequestMethod.POST) 
//	public Response useraccountsocial(UserVO vo,HttpSession session,HttpServletResponse response) {
//		try {
//			UserVO user = userService.useraccountsocial(vo);
//			
//		}catch(Exception e) {
//			response.setContentType("text/html;charset=UTF-8");
//		    PrintWriter out = response.getWriter();
//		    out.println("<script>");
//		    out.println("alert('정보 수정 중 오류가 발생했습니다. 다시 시도해주세요');");
//		    out.println("</script>");
//		    out.flush();
//		    out.close();
//		}
//		return null;
//	}	
////================================================================================================================	
//	//유저 질문 페이지로 이동
//		@RequestMapping(value="/user/mypage/question", method = RequestMethod.GET) 
//		public String userquestion(HttpSession session,HttpServletResponse response,UserVO vo,Model model) throws IOException {
//			System.out.println("콘솔테스트1111");
//			try {
//				if(session.getAttribute("user") == null) {
//					System.out.println(session.getAttribute("user"));
//					response.setContentType("text/html;charset=UTF-8");
//				    PrintWriter out = response.getWriter();
//				    out.println("<script>");
//				    out.println("alert('로그인 해주세요');");
//				    out.println("location.href='/vaca/login/main'"); // 또는 다른 페이지로 이동
//				    out.println("</script>");
//				    out.flush();
//				    out.close();
//				    System.out.println("콘솔테스트2222");
//					return "login/main";
//				}else {
//						System.out.println("콘솔테스트3333");
//						vo = (UserVO) session.getAttribute("user");
//						System.out.println("콘솔테스트4444");
//
//				        // 1. 로그인한 사용자의 ID를 가져옴
// 				        int userId = vo.getId();
//				    
//				        System.out.println("콘솔테스트5555");
//				        // 2. 서비스 호출하여 해당 사용자의 게시글 목록을 조회
//				       List<Object> myPosts = userService.getPostsByUserId(userId);
//				        System.out.println("mypost의 값");
//				        System.out.println( userService.getPostsByUserId(userId) + "리스트값");
//				        
//				        // 3. Model에 게시글 목록을 담아 JSP로 전달
//				       model.addAttribute("myPosts", myPosts);
//				       System.out.println(myPosts);
//				        System.out.println("콘솔테스트6666");
//
//
//				        // mypage.jsp 뷰를 반환
//				        return "user/mypage/question";
//				    }
//
//				
//				
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//			return "user/mypage/question";
//		}
//
////===========================================================================================================================
//		
//		//유저 예약 페이지로 이동
//				@RequestMapping(value="/user/mypage/reserv", method = RequestMethod.GET) 
//				public String userreserv(HttpSession session,HttpServletResponse response,UserVO vo,Model model) throws IOException {
//					try {
//						if(session.getAttribute("user") == null) {
//							response.setContentType("text/html;charset=UTF-8");
//						    PrintWriter out = response.getWriter();
//						    out.println("<script>");
//						    out.println("alert('로그인 해주세요');");
//						    out.println("location.href='/vaca/login/main'"); // 또는 다른 페이지로 이동
//						    out.println("</script>");
//						    out.flush();
//						    out.close();
//							return "login/main";
//						}else {
//								System.out.println("콘솔테스트3333");
//								vo = (UserVO) session.getAttribute("user");
//								System.out.println("콘솔테스트4444");
//
//						        // 1. 로그인한 사용자의 ID를 가져옴
//		 				        int userId = vo.getId();
//						    
//						        System.out.println("콘솔테스트5555");
//						        // 2. 서비스 호출하여 해당 사용자의 게시글 목록을 조회
//						       List<Object> myPosts = userService.getReservByUserId(userId);
//						        System.out.println( userService.getReservByUserId(userId) + "리스트값");
//						        
//						        // 3. Model에 게시글 목록을 담아 JSP로 전달
//						       model.addAttribute("myPosts", myPosts);
//						       System.out.println(myPosts);
//						        System.out.println("콘솔테스트6666");
//
//
//						        // mypage.jsp 뷰를 반환
//						        return "user/mypage/question";
//						    }
//
//						
//						
//					}catch(Exception e) {
//						e.printStackTrace();
//					}
//					return "user/mypage/question";
//				}
////======================================================================================================================		
//		//유저 찜목록으로이동
//		@RequestMapping(value="/user/mypage/wishlist", method = RequestMethod.GET) 
//		public String userwishlist(HttpSession session,HttpServletResponse response) throws IOException {
//			try {
//				if(session.getAttribute("user") == null) {
//					System.out.println(session.getAttribute("user"));
//					response.setContentType("text/html;charset=UTF-8");
//				    PrintWriter out = response.getWriter();.
//				    out.println("<script>");
//				    out.println("alert('로그인 해주세요');");
//				    out.println("location.href='/vaca/login/main'"); // 또는 다른 페이지로 이동
//				    out.println("</script>");
//				    out.flush();
//				    out.close();
//					return "login/main";
//				}else {
//					System.out.println(session.getAttribute("user"));
//					return "user/mypage/wishlist";
//				}
//				
//			}catch(Exception e) {
//				
//			}
//			return "login/main";
//		}
//
//	
//	


//========================================================================================================================================
}
