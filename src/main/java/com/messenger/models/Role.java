package com.messenger.models;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

import static com.messenger.constants.tables.TableNames.ROLE;

@Entity
@Table(name = ROLE)
public class Role implements GrantedAuthority {

    private static abstract class TypeAnnotation {
        private static final String UUID_CHAR_TYPE = "org.hibernate.type.UUIDCharType";
    }

    private static abstract class ColumnNames
    {
        private static final String ROLES = "roles";
    }

    @Id
    @Type(type = TypeAnnotation.UUID_CHAR_TYPE)
    private UUID id;
    private String name;

    @Transient
    @ManyToMany(mappedBy = ColumnNames.ROLES)
    private Set<User> users;

    public Role() {
    }

    public Role(UUID id) {
        this.id = id;
    }

    public Role(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

}