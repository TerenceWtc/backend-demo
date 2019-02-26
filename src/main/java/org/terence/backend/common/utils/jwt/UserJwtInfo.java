package org.terence.backend.common.utils.jwt;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author terence
 * @since 2019/2/19 9:31
 */
public class UserJwtInfo implements Serializable, IUserIwtInfo {
    private static final long serialVersionUID = 7252149547575110979L;

    private String id;

    private String username;

    private String name;

    public UserJwtInfo(String id, String userName, String name) {
        this.id = id;
        this.username = userName;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserJwtInfo that = (UserJwtInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name);
    }
}
