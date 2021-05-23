package com.atriviss.raritycheck.dto_jpa.rc_users;

import com.atriviss.raritycheck.validator.ValidEmail;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Setter
@EqualsAndHashCode
@Entity
@Table(name = "user", schema = "public", catalog = "rc_users")
public class UserJpaDto {
    private Integer id;
    private String username;
    private String password;

    private String name;
    private String surname;
    @ValidEmail
    private String email;
    private String timezone;
    private OffsetDateTime lastSeen;

    private String authorities;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 100)
    public String getUsername() {
        return username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 100)
    public String getSurname() {
        return surname;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    @Basic
    @Column(name = "timezone", nullable = false, length = 20)
    public String getTimezone() {
        return timezone;
    }

    @Basic
    @Column(name = "last_seen", nullable = false)
    public OffsetDateTime getLastSeen() {
        return lastSeen;
    }

    @Basic
    @Column(name = "authorities", nullable = false, length = 200)
    public String getAuthorities() {
        return authorities;
    }

    @Basic
    @Column(name = "account_non_expired", nullable = false)
    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    @Basic
    @Column(name = "account_non_locked", nullable = false)
    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    @Basic
    @Column(name = "credentials_non_expired", nullable = false)
    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public Boolean getEnabled() {
        return enabled;
    }
}
