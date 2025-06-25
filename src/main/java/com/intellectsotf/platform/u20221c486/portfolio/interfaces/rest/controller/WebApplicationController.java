package com.intellectsotf.platform.u20221c486.portfolio.interfaces.rest.controller;

import com.intellectsotf.platform.u20221c486.portfolio.interfaces.rest.resources.WebApplicationResource;
import com.intellectsotf.platform.u20221c486.portfolio.interfaces.rest.transform.CreateWebApplicationCommandFromResourceAssembler;

import com.intellectsotf.platform.u20221c486.portfolio.domain.services.WebApplicationCommandService;
import com.intellectsotf.platform.u20221c486.portfolio.interfaces.rest.resources.CreateWebApplicationResource;
import com.intellectsotf.platform.u20221c486.portfolio.interfaces.rest.transform.WebApplicationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/clients/{clientId}/web-applications", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Web Applications", description = "Available Web Application Endpoints")
public class WebApplicationController {

    private final WebApplicationCommandService webApplicationCommandService;

    public WebApplicationController(WebApplicationCommandService webApplicationCommandService) {
        this.webApplicationCommandService = webApplicationCommandService;
    }

    @PostMapping()
    @Operation(summary = "Create a new web application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Web Application created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public ResponseEntity<WebApplicationResource> createWebApplication(
            @PathVariable Long clientId,
            @Valid @RequestBody CreateWebApplicationResource resource) {
        var createWebApplicationCommand =  CreateWebApplicationCommandFromResourceAssembler.toCommandFromResource(clientId, resource);
        var webApplication =  webApplicationCommandService.handle(createWebApplicationCommand);
        if (webApplication.isEmpty()) return ResponseEntity.badRequest().build();
        var createdWebApp = webApplication.get();
        var webAppResource = WebApplicationResourceFromEntityAssembler.toResourceFromEntity(createdWebApp);
        return new ResponseEntity<>(webAppResource, HttpStatus.CREATED);
    }
}

