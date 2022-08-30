package kwang.ho;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude={MultipartAutoConfiguration.class})
@MapperScan(basePackages = "kwang.ho.mapper")
public class HoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoApplication.class, args);
	}
}
