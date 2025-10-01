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
	
	//회원가입동의페이지
	@RequestMapping(value="/user/join/agree", method = RequestMethod.GET)
	public String agree() {
		return "user/join/agree";
	}
	
	//회원가입폼 페이지 
	@RequestMapping(value="/user/join/form", method = RequestMethod.GET)
	public String join() {
		return "user/join/form";
	}
	
	//회원가입 폼 기능
	@RequestMapping(value="user/join/form", method = RequestMethod.POST) 
	public String join(UserVO vo) {
		userService.join(vo);
		return "redirect:/user/join/finish";
	}
	
	//회원가입 시 이메일 중복체크 기능
	@RequestMapping(value="user/join/form/emailCheck", method = RequestMethod.POST) 
	@ResponseBody
	public Response emailCheck(@RequestParam(value="email") String email) {
		int cnt = userService.emailCheck(email);
		Response res = new Response();
		res.setCode(cnt);
		res.setResult("success");
		return res;
	}
	
	//회원가입 완료 페이지
	@RequestMapping(value="/user/join/finish", method = RequestMethod.GET) 
	public String finish() {
		return "user/join/finish";
	}
	
	//이메일 찾기 페이지
	@RequestMapping(value="/user/find/email", method = RequestMethod.GET) 
	public String findEmail() {
		return "user/find/email";
	}
	
	//이메일 찾기 기능
	@RequestMapping(value="/user/find/email", method = RequestMethod.POST) 
	public String findEmail(UserVO vo,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		try {
			String email = userService.findEmail(vo).getEmail();
			model.addAttribute("email", email);
			response.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = response.getWriter();
		    writer.println("<script>");
		    writer.println("alert('가입한 이메일은"+email+"입니다.');");
		    writer.println("window.location.href = '/vaca/login/email';");
		    writer.println("</script>");
		    writer.flush(); //버퍼 초기화

		}catch(NullPointerException e){
			model.addAttribute("email", "가입한 이메일이 없습니다");
			response.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = response.getWriter();
		    writer.println("<script>");
		    writer.println("alert('가입한 이메일이 없습니다");
		    writer.println("window.location.href = '/vaca/user/find/email';");
		    writer.println("</script>");
		    writer.flush(); //버퍼 초기화

		}finally{

		}
		return null;

	}
	
	//비밀번호 찾기 페이지
	@RequestMapping(value="/user/find/pw", method = RequestMethod.GET) 
	public String findPw() {
		return "user/find/pw";
	}
	
	//비밀번호 찾기 메일이 보내짐을 알리는 페이지 
	@RequestMapping(value="/user/find/mailsend", method = RequestMethod.GET) 
	public String mailSend() {
		return "user/find/mailsend";
	}

//================================================================================================
	//카카오 소셜로그인

	@RequestMapping(value="/login/kakaocallback", method=RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code,HttpSession session,UserVO vo,Model model) throws Exception {
		String access_Token = UserService.getAccessToken(code);
		HashMap<String, Object> userInfo = UserService.getUserInfo(access_Token);
		String kakaoid = userInfo.get("kakaoid").toString();
			try {
				UserVO user = userService.kakaologin(kakaoid);
				if(user != null) {
					session.setAttribute("user", user); 
					return "redirect:/";
					
				}else {
					userService.kakaojoin(kakaoid);
					user = userService.kakaologin(kakaoid);
					session.setAttribute("user", user); 
					return "redirect:/";
				}
				
			}catch(Exception e) {
				userService.kakaojoin(access_Token);
				UserVO user = userService.kakaologin(access_Token);
				session.setAttribute("user", user); 
				return "redirect:/";
			}finally {
	
			}
    	}
	
//=======================================================================================================	
		//네이버 소셜로그인
		
	
	    @GetMapping("/login/naver")//네이버 소셜로그인 페이지로 이동
	    public void naverLogin(HttpServletRequest request, HttpServletResponse response){
	        final String loginUrl = UserService.getRequestLoginUrl();
	        try {
	            response.sendRedirect(loginUrl);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	   @GetMapping("/login/navercallback") //네이버 callback url로 이동시킴
	  // @ResponseBody
	    public Object requestAccessCallback( @RequestParam(value = "code") String authCode, 
	                                         @RequestParam(value = "state") String state,HttpSession session,UserVO vo){

		   
	      ResponseEntity<?> responseEntity = UserService.requestAccessToken(authCode, state);
	      Object responseMessage = responseEntity.getBody();
	      Map<String, String> map = (Map<String, String>) responseMessage;
	      String accesstokenparsed = map.get("access_token");
	      String token = accesstokenparsed; 
	      String header = "Bearer " + token; 
	      String apiURL = "https://openapi.naver.com/v1/nid/me";
	      Map<String, String> requestHeaders = new HashMap<>();
	      requestHeaders.put("Authorization", header);
	      String responseBody = get(apiURL,requestHeaders);
	      
	      
	      
	      
	      
	      
	      
	      //naverid에서 온 json 값을 파싱해주는 코드
	      String naverid = "sdfasdfasdsdfa";// naverid 값을 사용하려 변수 만들었는데 null이면 오류나서 넣은 임시값
	      ObjectMapper objectMapper = new ObjectMapper();
	      try {
			Map<String, Object> responsebodymap = objectMapper.readValue(responseBody, Map.class);
			Object responsebodyresponse = responsebodymap.get("response");
			 Map<String, String> responsebodyresponseid = (Map<String, String>) responsebodyresponse;
			  naverid = responsebodyresponseid.get("id");
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
	      
	    
	      	try {
				UserVO user = userService.naverlogin(naverid);
				if(user != null) {
					session.setAttribute("user", user); 
					return "redirect:/";
				}else {
					userService.naverjoin(naverid);
					user = userService.naverlogin(naverid);
					session.setAttribute("user", user); 
					return "redirect:/";
				}
				
			}catch(Exception e) {
				return "redirect:/";
			}finally {
	
			}
	   }
	   

	   
	   //네이버 소셜로그인 api 명세서에 있던 값 (무슨 코드인지 모름 ,자세한 내용은 네이버 소셜로그인 api 페이지)
	   private static String get(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }
	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { 
	                return readBody(con.getInputStream());
	            } else { 
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API ", e);
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
	            throw new RuntimeException("API.", e);
	        }
	    }

	    private static HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL: " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException(" " + apiUrl, e);
	        }
	    } 
   



//=====================================================================================================================
	  

	//로그인 메인 페이지
	@RequestMapping(value="/login/main", method = RequestMethod.GET) 
	public String main() {
		return "login/main";
	}
	
	//이메일로 로그인 페이지
	@RequestMapping(value="/login/email", method = RequestMethod.GET) 
	public String login() {
		return "login/email";
	}
	
	//이메일로 로그인 기능
	@RequestMapping(value="/login/email", method = RequestMethod.POST) 
	public String login(UserVO vo ,HttpSession session,Model model,HttpServletResponse response) throws IOException {
		UserVO user = userService.login(vo);

		if(user == null) {
			response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('일치하는 계정이 없습니다.');");
		    out.println("history.back();"); 
		    out.println("</script>");
		    out.flush();
		    out.close();
		}
		//계정 존재할떄
		session.setAttribute("user", user); 
		return "redirect:/";
	}
	
	//로그아웃 기능이자 페이지
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
	//메인화면을 띄워주는 페이지
	@RequestMapping(value="main/main", method = RequestMethod.GET) 
	public String main(HttpSession session) {
		session.getAttribute("user");
		return "main/main";
	}

//=========================================================================================================================
	
	//비밀번호 변경 페이지
		@RequestMapping(value="/user/mypage/pwchange", method = RequestMethod.GET) 
		public String userpwchange(UserVO vo, HttpSession session) throws IOException {
			try {
				String userid = (String) session.getAttribute("user");

				if(vo == null) {
					return "login/main";	
				}else { 
					return "user/mypage/pwchange"; 
				}	
			}catch(Exception e) {
				
			}finally {
				
			}
			return null;
		}
	
//	비밀번호 변경 기능 구현중	
//		@RequestMapping(value="/user/mypage/pwchange", method = RequestMethod.POST) 
//		public String userpwchangeFn(@RequestParamUserVO vo, HttpSession session,HttpServletResponse response,@RequestParam("userpw") String userpw,@RequestParam("pwchange") String pwchange) throws IOException {
//			try {
//				String userid = (String) session.getAttribute("user");
//
//				if(userService.checkuserpw(userid) == true) {
//					userService.userpwchange(userpw);
//				}else {
//					response.setContentType("text/html;charset=UTF-8");
//				    PrintWriter out = response.getWriter();
//				    out.println("<script>");
//				    out.println("alert('');");
//				    out.println("</script>");
//				    out.flush();
//				    out.close();
//				}
//			}catch(Exception e) {
//				
//			}finally {
//				
//			}
//			return null;
//		}
//	
	
//================================================================================================================================	
	

	//마이페이지 계정 설정 페이지
	@RequestMapping(value="/user/mypage/account", method = RequestMethod.GET) 
	public String useraccount(UserVO vo, HttpSession session,HttpServletResponse response) throws IOException {
		try {
			vo = (UserVO) session.getAttribute("user");
			
			if(vo == null) {
				return "login/main";		
			}else { 
				return "user/mypage/account"; 
			}	
		}catch(Exception e) {
			
		}finally {
			
		}
		return null;
	}
	

	
	//마이페이지 계정 설정 기능
	@RequestMapping(value="/user/mypage/account", method = RequestMethod.POST) 
	public String useraccountchange(UserVO vo,HttpSession session,HttpServletResponse response,@RequestParam("nickname") String nickname,@RequestParam("phone") String phone) throws IOException {
		vo = (UserVO) session.getAttribute("user");
		String accountchangenickname = nickname;
		
		String accountchangephone = phone;
		try {
	
			vo.setNickname(accountchangenickname);
			vo.setPhone(accountchangephone);
			userService.useraccountchange(vo);
			response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('변경되었습니다');");
		    out.println("</script>");
		    out.flush();
		    out.close();
			return "redirect:/user/mypage/account";
		}catch(Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('오류가 발생했습니다.');");
		    out.println("</script>");
		    out.flush();
		    out.close();
		}
		return null;
	}
	


	
//================================================================================================================	
	//마이페이지 질문 페이지
		@RequestMapping(value="/user/mypage/question", method = RequestMethod.GET) 
		public String userquestion(HttpSession session,HttpServletResponse response,UserVO vo,Model model) throws IOException {
			try {
				if(session.getAttribute("user") == null) {
					response.setContentType("text/html;charset=UTF-8");
				    PrintWriter out = response.getWriter();
				    out.println("<script>");
				    out.println("alert('로그인해 주세요');");
				    out.println("location.href='/vaca/login/main'"); // 로그인 안 됐을때 이 페이지로 이동
				    out.println("</script>");
				    out.flush();
				    out.close();
					return "login/main";
				}else {
						vo = (UserVO) session.getAttribute("user");

				        // 1. id값을 가져온다.
 				        int userId = vo.getId();
				    
				        // 2. id값을 이용해 내가 쓴 게시글만을 가져와서 페이지에서 사용가능하게 형변환해준다.
				       List<Object> myPosts = userService.getPostsByUserId(userId);
				        
				        // 3. Model에 myPosts라는 이름으로 내가 쓴 게시글들을 집어넣어 반환
				       model.addAttribute("myPosts", myPosts);


				       
				        return "user/mypage/question";
				    }

				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return "user/mypage/question";
		}

//===========================================================================================================================
		
		//예약 페이지
				@RequestMapping(value="/user/mypage/reserv", method = RequestMethod.GET) 
				public String userreserv(HttpSession session,HttpServletResponse response,UserVO vo,Model model) throws IOException {
					try {
						if(session.getAttribute("user") == null) {
							response.setContentType("text/html;charset=UTF-8");
						    PrintWriter out = response.getWriter();
						    out.println("<script>");
						    out.println("alert('로그인 해주세요');");
						    out.println("location.href='/vaca/login/main'"); // 로그인 안 되면 이 페이지로 이동
						    out.println("</script>");
						    out.flush();
						    out.close();
							return "login/main";
						}else {
								vo = (UserVO) session.getAttribute("user");

						        // 1. 아이디 가져온다.
		 				        int userId = vo.getId();
						    
						        // 2.위에처럼 사용가능하게 형변환해준다.
						       List<Object> myReserv = userService.getReservByUserId(userId);
						        
						        // 3. Model로 변환해서 페이지로 반환해준다.
						       model.addAttribute("myReserv", myReserv);
System.out.println(myReserv);

						     
						        return "user/mypage/reserv";
						    }

						
						
					}catch(Exception e) {
						e.printStackTrace();
					}
					return "user/mypage/question";
				}
//======================================================================================================================		
		//위시리스트 페이지 
		@RequestMapping(value="/user/mypage/wishlist", method = RequestMethod.GET) 
		public String userwishlist(HttpSession session,HttpServletResponse response) throws IOException {
			try {
				if(session.getAttribute("user") == null) {
					response.setContentType("text/html;charset=UTF-8");
				    PrintWriter out = response.getWriter();
				    out.println("<script>");
				    out.println("alert('로그인 해주세요');");
				    out.println("location.href='/vaca/login/main'"); // 로그인 안되었을시 이 페이지로 이동
				    out.flush();
				    out.close();
					return "login/main";
				}else {
					return "user/mypage/wishlist";
				}
				
			}catch(Exception e) {
				
			}
			return "login/main";
		}

	
	


//========================================================================================================================================
	
		

		@GetMapping("/admin/private_question")
		public String questionList(Model model) {
			List<NoticeVO> list = userService.selectQuestionList();
			model.addAttribute("list", list);
			return "/admin/private_question";
		}
		

		@RequestMapping(value="join")
		public String joinPage() {
			return "/join/main";
		}
}
