package com.intellectsotf.platform.u20221c486.portfolio.interfaces.rest.transform;

import com.intellectsotf.platform.u20221c486.portfolio.domain.model.aggregates.WebApplication;
import com.intellectsotf.platform.u20221c486.portfolio.interfaces.rest.resources.WebApplicationResource;

public class WebApplicationResourceFromEntityAssembler {
    public static WebApplicationResource toResourceFromEntity(WebApplication entity) {
        return new WebApplicationResource(
                entity.getId(),
                entity.getName(),
                entity.getClientId(),
                entity.getFrontendStack(),
                entity.getFrontendUrl(),
                entity.getBackendStack(),
                entity.getBackendUrl(),
                entity.getCloudPlatform(),
                entity.getDescription(),
                entity.getAvailabiltyPercentage(),
                entity.getLaunchDate()
        );
    }
}