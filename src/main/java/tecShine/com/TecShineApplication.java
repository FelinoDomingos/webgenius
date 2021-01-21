
package tecShine.com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;



@SpringBootApplication 
@EnableAsync
public class TecShineApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TecShineApplication.class, args);

	
	  
	}
 
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(TecShineApplication.class);
	}

}
                                             