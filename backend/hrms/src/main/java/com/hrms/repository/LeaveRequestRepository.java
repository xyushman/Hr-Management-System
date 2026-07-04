package com.hrms.repository;

import com.hrms.entity.Employee;
import com.hrms.entity.LeaveRequest;
import com.hrms.enums.LeaveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    Page<LeaveRequest> findByEmployee(Employee employee, Pageable pageable);
    Page<LeaveRequest> findByStatus(LeaveStatus status, Pageable pageable);
    Page<LeaveRequest> findByEmployeeAndStatus(Employee employee, LeaveStatus status, Pageable pageable);

    List<LeaveRequest> findByEmployeeAndStatusAndStartDateBetween(
            Employee employee, LeaveStatus status, LocalDate from, LocalDate to);

    long countByEmployeeAndStatusAndStartDateBetween(
            Employee employee, LeaveStatus status, LocalDate from, LocalDate to);
}
