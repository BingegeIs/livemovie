package bingege.movie.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 需要@EntityScan 才能扫描到module中的实体
 * 需要@EnableJpaRepositories 才能扫描到@repository接口
 */
@SpringBootApplication(scanBasePackages = {"bingege.movie"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "bingege.movie.dao")
@EntityScan(basePackages = "bingege.movie.model")
public class MovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}
