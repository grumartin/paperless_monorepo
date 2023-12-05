package at.fhtw.swkom.paperless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication
@ComponentScan(
        basePackages = {
                "at.fhtw.swkom.paperless.services",
                "at.fhtw.swkom.paperless.config",
        },
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class PaperlessServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaperlessServicesApplication.class, args);
    }
}
