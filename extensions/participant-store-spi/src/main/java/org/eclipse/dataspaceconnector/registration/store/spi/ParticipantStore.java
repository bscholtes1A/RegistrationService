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

package org.eclipse.dataspaceconnector.registration.store.spi;

import java.util.List;

import org.eclipse.dataspaceconnector.registration.store.model.Participant;

public interface ParticipantStore {
    List<Participant> listParticipants();

    void deleteParticipant(String name);

    void addParticipant(Participant participant);
}
