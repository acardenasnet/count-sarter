package com.dou.countsarter.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.repository.CrudRepository;

import java.util.Arrays;
import java.util.Collection;

public class DbCountService {

  protected static final Log LOGGER = LogFactory.getLog(DbCountService.class);

  private static final String SCAN_PACKAGE = "com.dou";
  private static final String UNKNOWN_REPOSITORY = "UnknownRepository";

  private Collection<CrudRepository> repositories;

  public DbCountService(Collection<CrudRepository> repositories) {
    this.repositories = repositories;
  }

  public void printCount() {
    repositories.forEach(crudRepository ->
        LOGGER.info(String.format("%s has %s entries",
            getRepositoryName(crudRepository.getClass()),
            crudRepository.count())));
  }

  private static String getRepositoryName(Class crudRepositoryClass) {
    return Arrays.stream(crudRepositoryClass.getInterfaces())
        .filter(repositoryInterface -> repositoryInterface.getName().startsWith(SCAN_PACKAGE))
        .map(Class::getSimpleName)
        .findAny()
        .orElse(UNKNOWN_REPOSITORY);
  }

}
