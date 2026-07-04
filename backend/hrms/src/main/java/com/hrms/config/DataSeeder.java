package com.hrms.config;

import com.hrms.entity.Employee;
import com.hrms.enums.Role;
import com.hrms.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // Seed default Admin
        if (!employeeRepository.existsByEmail("admin@hrms.com")) {
            Employee admin = Employee.builder()
                    .employeeId("EMP0001")
                    .firstName("System")
                    .lastName("Admin")
                    .email("admin@hrms.com")
                    .password(passwordEncoder.encode("Admin@123"))
                    .department("Administration")
                    .designation("System Administrator")
                    .role(Role.ADMIN)
                    .dateOfJoining(LocalDate.now())
                    .active(true)
                    .build();
            employeeRepository.save(admin);
            log.info("✅ ADMIN seeded: admin@hrms.com / Admin@123  →  loginType: ADMIN");
        }

        // Seed default HR
        if (!employeeRepository.existsByEmail("hr@hrms.com")) {
            Employee hr = Employee.builder()
                    .employeeId("EMP0002")
                    .firstName("HR")
                    .lastName("Manager")
                    .email("hr@hrms.com")
                    .password(passwordEncoder.encode("Hr@12345"))
                    .department("Human Resources")
                    .designation("HR Manager")
                    .role(Role.HR)
                    .dateOfJoining(LocalDate.now())
                    .active(true)
                    .build();
            employeeRepository.save(hr);
            log.info("✅ HR seeded: hr@hrms.com / Hr@12345  →  loginType: ADMIN");
        }

        // Seed default Employee
        if (!employeeRepository.existsByEmail("emp@hrms.com")) {
            Employee emp = Employee.builder()
                    .employeeId("EMP0003")
                    .firstName("Test")
                    .lastName("Employee")
                    .email("emp@hrms.com")
                    .password(passwordEncoder.encode("Emp@12345"))
                    .department("Engineering")
                    .designation("Software Developer")
                    .role(Role.EMPLOYEE)
                    .dateOfJoining(LocalDate.now())
                    .active(true)
                    .build();
            employeeRepository.save(emp);
            log.info("✅ EMPLOYEE seeded: emp@hrms.com / Emp@12345  →  loginType: EMPLOYEE");
        }
    }
}
