package com.intellectsotf.platform.u20221c486.portfolio.interfaces.rest.transform;

import com.intellectsotf.platform.u20221c486.portfolio.domain.model.commandservices.CreateWebApplicationCommand;
import com.intellectsotf.platform.u20221c486.portfolio.interfaces.rest.resources.CreateWebApplicationResource;

public class CreateWebApplicationCommandFromResourceAssembler {
    public static CreateWebApplicationCommand toCommandFromResource(Long clientId, CreateWebApplicationResource resource) {
        return new CreateWebApplicationCommand(
                resource.name(),
                clientId,
                resource.frontendStack(),
                resource.frontendUrl(),
                resource.backendStack(),
                resource.backendUrl(),
                resource.cloudPlatform(),
                resource.description(),
                resource.availabiltyPercentage(),
                resource.launchDate()
        );
    }
}


