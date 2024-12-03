package com.atuluttam.thymleafregisterlogin.repository;

import com.atuluttam.thymleafregisterlogin.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByUsername(String username);
}
