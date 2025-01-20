package com.pixel.dbinit;

import com.pixel.entity.Employee;
import com.pixel.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Component
public class DataInitializer {

  /* @Bean
    public CommandLineRunner loadData(EmployeeRepository employeeRepository) {
        return args -> {
            employeeRepository.save(new Employee(1L,"John Doe", "Software Engineer", 70000));
            employeeRepository.save(new Employee(2L,"Jane Smith", "Product Manager", 90000));
        };
    }*/
}