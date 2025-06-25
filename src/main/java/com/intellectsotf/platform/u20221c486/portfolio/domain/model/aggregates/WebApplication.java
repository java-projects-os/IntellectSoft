package com.intellectsotf.platform.u20221c486.portfolio.domain.model.aggregates;

import com.intellectsotf.platform.u20221c486.portfolio.domain.model.commandservices.CreateWebApplicationCommand;
import com.intellectsotf.platform.u20221c486.portfolio.domain.model.valueobjects.BackendStack;
import com.intellectsotf.platform.u20221c486.portfolio.domain.model.valueobjects.ClientId;
import com.intellectsotf.platform.u20221c486.portfolio.domain.model.valueobjects.CloudPlatform;
import com.intellectsotf.platform.u20221c486.portfolio.domain.model.valueobjects.FrontendStack;
import com.intellectsotf.platform.u20221c486.portfolio.infrastructure.persistence.jpa.converters.BackendStackConverter;
import com.intellectsotf.platform.u20221c486.portfolio.infrastructure.persistence.jpa.converters.CloudPlatformConverter;
import com.intellectsotf.platform.u20221c486.portfolio.infrastructure.persistence.jpa.converters.FrontendStackConverter;
import com.intellectsotf.platform.u20221c486.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.intellectsotf.platform.u20221c486.shared.domain.model.valueobjects.WebAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Date;

@Entity
public class WebApplication extends AuditableAbstractAggregateRoot<WebApplication> {
    @Getter
    @NotBlank
    @Size(max = 80)
    @Column(name = "name", length = 80, nullable = false)
    private String name;

    @Embedded
    @AttributeOverride(name  = "clientId", column = @Column(name = "client_id", nullable = false))
    private ClientId clientId;


    @Convert(converter = FrontendStackConverter.class)
    @Column(name = "frontend_stack", nullable = false)
    private FrontendStack frontendStack;

    @Embedded
    @AttributeOverride(name  = "webAddress", column = @Column(name = "frontend_url", nullable = false))
    private WebAddress frontendUrl;

    @Convert(converter = BackendStackConverter.class)
    @Column(name = "backend_stack", nullable = false)
    private BackendStack backendStack;

    @Embedded
    @AttributeOverride(name  = "webAddress", column = @Column(name = "backend_url", nullable = false, length = 512))
    private WebAddress backendUrl;

    @Convert(converter = CloudPlatformConverter.class)
    @Column(name = "cloud_platform", nullable = false)
    private CloudPlatform cloudPlatform;

    @Getter
    @NotBlank
    @Size(max = 360)
    @Column(name = "description", length = 360, nullable = false)
    private String description;

    @Getter
    private Integer availabiltyPercentage;

    @Getter
    private Date launchDate;

    public WebApplication() {
        super();
    }

   public WebApplication (CreateWebApplicationCommand command) {
        this.name = command.name();
        this.clientId = new ClientId(command.clientId());
        this.frontendStack = FrontendStack.fromString(command.frontendStack());
        this.frontendUrl = new WebAddress(command.frontendUrl());
        this.backendStack = BackendStack.fromString(command.backendStack());
        this.backendUrl = new WebAddress(command.backendUrl());
        this.cloudPlatform = CloudPlatform.fromString(command.cloudPlatform());
        this.description = command.description();
        this.availabiltyPercentage = command.availabiltyPercentage();
        this.launchDate = command.launchDate();
   }

   public Long getClientId() {
        return this.clientId.getClientId();
   }

   public int getFrontendStack() {
        return frontendStack.getId();
   }

    public int getBackendStack() {
        return backendStack.getId();
    }

    public int getCloudPlatform() {
        return cloudPlatform.getId();
    }

    public String getFrontendUrl() {
        return frontendUrl.getWebAddress();
    }
    public String getBackendUrl() {
        return frontendUrl.getWebAddress();
    }
}
