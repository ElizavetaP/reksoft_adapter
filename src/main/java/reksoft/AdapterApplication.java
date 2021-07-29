package reksoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {AdapterApplication.BASE_PACKAGE})
@EntityScan(AdapterApplication.BASE_PACKAGE)
public class AdapterApplication {
    public static final String BASE_PACKAGE = "reksoft";

    public static void main(String[] args) {
        SpringApplication.run(AdapterApplication.class, args);
    }
}
