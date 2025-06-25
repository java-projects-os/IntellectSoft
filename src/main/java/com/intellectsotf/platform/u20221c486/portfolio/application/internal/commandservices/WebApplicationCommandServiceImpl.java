package com.intellectsotf.platform.u20221c486.portfolio.application.internal.commandservices;

import com.intellectsotf.platform.u20221c486.portfolio.domain.model.aggregates.WebApplication;
import com.intellectsotf.platform.u20221c486.portfolio.domain.model.commandservices.CreateWebApplicationCommand;
import com.intellectsotf.platform.u20221c486.portfolio.domain.services.WebApplicationCommandService;
import com.intellectsotf.platform.u20221c486.portfolio.infrastructure.persistence.jpa.respositories.WebApplicationRepository;
import com.intellectsotf.platform.u20221c486.shared.domain.model.valueobjects.WebAddress;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class WebApplicationCommandServiceImpl implements WebApplicationCommandService {
    private final WebApplicationRepository webApplicationRepository;

    public WebApplicationCommandServiceImpl(WebApplicationRepository webApplicationRepository) {
        this.webApplicationRepository = webApplicationRepository;
    }

    @Override
    public Optional<WebApplication> handle(CreateWebApplicationCommand command) {
        var backendUrl = new WebAddress(command.backendUrl());
        var frontendUrl = new WebAddress(command.frontendUrl());
        LocalDate ld = LocalDate.of(2007,7,15);
        var actualDate = new Date();
        var dateIntellectSoft = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (command.launchDate().before(dateIntellectSoft) || command.launchDate().after(actualDate)) {
            throw new IllegalArgumentException("Launch date is invalid");
        }
        if (webApplicationRepository.existsByBackendUrl(backendUrl)) {
            throw new IllegalArgumentException("Web Application with the same backend URL already exists");
        }
        if (webApplicationRepository.existsByFrontendUrl(frontendUrl)) {
            throw new IllegalArgumentException("Web Application with the same frontend URL already exists");
        }
        var webApplication = new WebApplication(command);
        webApplicationRepository.save(webApplication);
        return Optional.of(webApplication);
    }
}
