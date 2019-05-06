package com.dou.countsarter.config;

import com.dou.countsarter.service.DbCountService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

@Configuration
public class DbCountAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public DbCountService dbCountRunner(Collection<CrudRepository> repositories) {
    return new DbCountService(repositories);
  }

}
