package org.terence.backend.web.config.jpa;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.terence.backend.common.utils.jwt.IUserJwtInfo;

import java.util.Optional;

/**
 * @author terence
 * @since 2019/7/18 14:39
 */
@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof IUserJwtInfo) {
            String sysUsername = ((IUserJwtInfo) principal).getUsername();
            return Optional.of(sysUsername);
        }
        return Optional.of(authentication.toString());
    }
}
