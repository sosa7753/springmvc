package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
// @Controller는 반환 값이 String이면 View 이름으로 인식 -> 뷰를 찾고 뷰가 랜더링
// @RestController는 반환 값을 그대로 HTTP 메세지 바디에 바로 입력.
public class LogTestController {

//  private final Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping("/log-test")
  public String logTest() {
    String name = "Spring";

    System.out.println("name = " + name);

    log.trace("trace log={}", name);
//    log.trace("trace my log=" + name); // 불필요한 문자열 연산
    log.debug("debug log={}", name);
    log.info(" info log={}", name);
    log.warn(" warn log={}", name);
    log.error("error log={}", name);

    return "ok";
  }
}
