package hello.springmvc.basic;

import lombok.Data;

@Data// lombok 라이브러리를 사용하면 getter, setter, toString 등을 자동으로 생성해준다.
public class HelloData {
	private String username;
	private int age;
}
