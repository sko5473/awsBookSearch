package kwang.ho;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@SpringBootApplication(scanBasePackages = {"kwang.ho.service","kwang.ho"})*/
@SpringBootApplication
@MapperScan(basePackages = "kwang.ho.mapper")
public class HoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoApplication.class, args);
	}
}
