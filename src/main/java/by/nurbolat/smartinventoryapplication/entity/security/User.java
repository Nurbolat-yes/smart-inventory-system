package by.nurbolat.smartinventoryapplication.entity.security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "Users", schema = "security")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String passwordHash;
    private String role; // Admin  Staff  Viewer

    private LocalDateTime createdAt = LocalDateTime.now();
}