package org.pms.samlogin.enums;

public enum AuthorityType {

    OWNER(1, "OWNER"),
    ADMIN(2, "ADMIN"),
    MEMBER(3, "MEMBER"),
    EQCR(4, "EQCR"),
    PARTNER(5, "PARTNER");

    private final int code;

    private final String description;

    AuthorityType(int code, String description) {
        this.code = code;

        this.description = description;
    }

    public int code() {
        return code;
    }

    public String description() {
        return description;
    }

    public static AuthorityType fromCode(int v) {
        for (AuthorityType c: AuthorityType.values()) {
            if (c.code == v) {
                return c;
            }
        }
        throw new IllegalArgumentException(String.valueOf(v));
    }

}

