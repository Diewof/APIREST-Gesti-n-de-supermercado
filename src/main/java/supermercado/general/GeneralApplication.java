package supermercado.general;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Punto de entrada de la aplicación. @SpringBootApplication activa el escaneo de componentes,
// la autoconfiguración y el arranque del servidor embebido (Tomcat).
@SpringBootApplication
public class GeneralApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneralApplication.class, args);
	}

}
