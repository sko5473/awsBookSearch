package kwang.ho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@SpringBootApplication(scanBasePackages = {"kwang.ho.service"})*/
@SpringBootApplication
public class HoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoApplication.class, args);
	}
}
