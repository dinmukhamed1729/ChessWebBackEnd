package ChessWeb.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Set;
@ToString(exclude = "friends")
@EqualsAndHashCode(exclude = "friends")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
@Entity
@Table(name = "_user")
public class User {
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "_user_friend",
            joinColumns = @JoinColumn(name = "_user_id"),
            inverseJoinColumns = @JoinColumn(name = "_friend_id")
    )
    private Set<User> friends;



    public void addFriend(User friend) {
        friends.add(friend);
    }
}
