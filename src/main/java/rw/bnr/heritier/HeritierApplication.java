package rw.bnr.heritier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HeritierApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeritierApplication.class, args);
		System.out.println("\n\n\n\n\n\n\n" +
				new BCryptPasswordEncoder().encode("admin123"));
	}

}
