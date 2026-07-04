package com.hrms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private Employee recipient;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType type;     //leave applied,leaveapproved,leave rejected

    private String referenceType;      // leave request
    private Long referenceId;          // example of leaveRequest.id

    // "read" is a reserved word in MySQL — mapped to column "is_read" instead
    @Column(name = "is_read")
    @Builder.Default
    private boolean isRead = false;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public enum NotificationType {
        LEAVE_APPLIED, LEAVE_APPROVED, LEAVE_REJECTED, LEAVE_CANCELLED,
        ATTENDANCE_REMINDER, PAYROLL_GENERATED, GENERAL
    }
}