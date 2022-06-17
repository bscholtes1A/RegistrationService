/*
 *  Copyright (c) 2022 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *
 */

package org.eclipse.dataspaceconnector.registration.api;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import org.eclipse.dataspaceconnector.registration.store.model.Participant;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;

/**
 * Registration Service API controller to manage dataspace participants.
 */
@Tag(name = "Registry")
@Produces({ "application/json" })
@Consumes({ "application/json" })
@Path("/registry")
public class RegistrationApiController {

    private final RegistrationService service;
    private final Monitor monitor;

    /**
     * Constructs an instance of {@link RegistrationApiController}
     *
     * @param service service handling the registration service logic.
     * @param monitor
     */
    public RegistrationApiController(RegistrationService service, Monitor monitor) {
        this.service = service;
        this.monitor = monitor;
    }

    @Path("/participants")
    @GET
    @Operation(description = "Gets all dataspace participants.")
    @ApiResponse(description = "Dataspace participants.")
    public List<Participant> listParticipants() {
        return service.listParticipants();
    }

    @Path("/participant/{name}")
    @DELETE
    @Operation(description = "Delete a dataspace participant.")
    @ApiResponse(description = "Dataspace participants.")
    public void deleteParticipant(@PathParam("name") String name) {
        service.deleteParticipant(name);
    }

    @Path("/participant")
    @Operation(description = "Asynchronously request to add a dataspace participant.")
    @ApiResponse(responseCode = "204", description = "No content")
    @POST
    public void addParticipant(Participant participant) {
        if (isValid(participant)) {
            service.addParticipant(participant);
        } else {
            monitor.warning("Cannot register invalid participant: " + participant);
        }
    }

    private static boolean isValid(Participant participant) {
        return isNotNullOrBlank(participant.getName()) &&
                isNotNullOrBlank(participant.getUrl()) &&
                !participant.getSupportedProtocols().isEmpty();
    }

    private static boolean isNotNullOrBlank(String str) {
        return str != null && !str.isBlank();
    }
}
