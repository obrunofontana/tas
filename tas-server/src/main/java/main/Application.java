package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author brunofontana
 */
@SpringBootApplication
@EntityScan({
    "br.edu.materdei.tas.core.entity",
    "br.edu.materdei.tas.purchase.entity",
    "br.edu.materdei.tas.sale.entity",
    "br.edu.materdei.tas.stock.entity",
    "br.edu.materdei.tas.composition.entity"
})
@EnableJpaRepositories({
    "br.edu.materdei.tas.core.repository",
    "br.edu.materdei.tas.purchase.repository",
    "br.edu.materdei.tas.sale.repository",
    "br.edu.materdei.tas.stock.repository",
    "br.edu.materdei.tas.composition.repository"   
    
})
@ComponentScan("br.edu.materdei.tas.*")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);        
    }
}
