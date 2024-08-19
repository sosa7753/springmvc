package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestParamController {

  @RequestMapping("/request-param-v1")
  public void requestParamV1(
      HttpServletRequest request, HttpServletResponse response) throws IOException {

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    log.info("username={}, age={}", username, age);

    response.getWriter().write("ok"); // void 타입에 response에 값을 쓰면 쓴 값이 나옴.
  }

  @ResponseBody
  @RequestMapping("/request-param-v2")
  public String requestParamV2(
      @RequestParam("username") String memberName,
      @RequestParam("age") int memberAge) {

    log.info("username={}, age={}", memberName, memberAge);

    return "ok"; // @Controller는 view를 찾지만, @ResponseBody를 붙이면 바로 Body에 넣는다.
  }

  @ResponseBody
  @RequestMapping("/request-param-v3")
  public String requestParamV3(
      @RequestParam String username,
      @RequestParam int age) {

    log.info("username={}, age={}", username, age);
    return "ok";
  }

  /**
   * @RequestParam 사용
   * String, int 등의 단순 타입이면 @RequestParam 도 생략 가능
   * 그러나 너무 생략하는 것은 또 과할 수 있다.
   */

  @ResponseBody
  @RequestMapping("/request-param-v4")
  public String requestParamV4(String username, int age) {
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-required")
  public String requestParamRequired(
      @RequestParam(required = true) String username, // 파라미터가 없어도 가능.
      @RequestParam(required = false) Integer age) { // int는 null이 못들어감.
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-default")
  public String requestParamDefault(
      @RequestParam(defaultValue = "guest") String username, // 빈문자도 default로 처리
      @RequestParam(required = false, defaultValue = "-1") int age) {
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-map")
  public String requestParamMap(
      @RequestParam Map<String, Object> paramMap) {
    log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/model-attribute-v1")
  public String modelAttributeV1(@ModelAttribute HelloData helloData) {
    /**
     * @ModelAttribute 동작
     * HelloData 객체 생성
     * 요청 파라미터 이름으로 HelloData 객체의 프로퍼티를 찾음.(set~ get~)
     * setter를 호출해서 파라미터의 값을 바인딩.
     */

    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
    log.info("helloData={}", helloData); // @Data에 @ToString이 있어서 이렇게 가능.
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/model-attribute-v2")
  public String modelAttributeV2(HelloData helloData) {
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
    log.info("helloData={}", helloData); // @Data에 @ToString이 있어서 이렇게 가능.
    return "ok";
  }
}
