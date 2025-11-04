package co.edu.unbosque.bogotacup;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BogotacupApplication {

	public static void main(String[] args) {
		SpringApplication.run(BogotacupApplication.class, args);
	}
	
	@SpringBootApplication
	public class nombreproyectoApplication {
		@Bean
		public ModelMapper getModelMapper() {
			return new  ModelMapper();
		}
	}

}
