package ChessWeb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.Set;

import lombok.Builder;
import lombok.With;
import jakarta.persistence.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
@Data
@Entity
@Table(name = "_user")
public  class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> role;
}
