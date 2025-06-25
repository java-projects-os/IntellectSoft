package com.intellectsotf.platform.u20221c486.portfolio.infrastructure.persistence.jpa.respositories;

import com.intellectsotf.platform.u20221c486.portfolio.domain.model.aggregates.WebApplication;
import com.intellectsotf.platform.u20221c486.shared.domain.model.valueobjects.WebAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebApplicationRepository extends JpaRepository<WebApplication, Long> {
    boolean existsByFrontendUrl(WebAddress frontendUrl);
    boolean existsByBackendUrl(WebAddress backendUrl);
}
