package com.intellectsotf.platform.u20221c486.portfolio.interfaces.rest.resources;

import java.util.Date;

public record WebApplicationResource(Long id, String name, Long clientId, int frontendStack, String frontendUrl, int backendStack, String backendUrl, int cloudPlatform, String description, Integer availabiltyPercentage, Date launchDate) {
}
