package inu.amigo.orderIt.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 멤버 역할 엔티티 - OWNER(사업자), ADMIN(운영자)
 */

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_GUEST"),
    ADMIN("ROLE_ADMIN");
    private final String key;
}