/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jespanol.client.speaker;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Controller
@Path("speaker")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SpeakerController {

    @Inject
    private Models models;

    @Inject
    @RestClient
    private SpeakerService speakerService;

    @GET
    @View("speaker.html")
    public void home() {
        this.models.put("speakers", speakerService.findAll());
    }


    @Path("add")
    @GET
    @View("speaker-add.html")
    public void add() {
        this.models.put("speaker", new Speaker());
    }

    @Path("delete/{id}")
    @GET
    @View("speaker.html")
    public void delete(@PathParam("id") Integer id) {
        speakerService.remove(id);
        this.models.put("speakers", speakerService.findAll());
    }

    @Path("edit/{id}")
    @GET
    @View("speaker-add.html")
    public void edit(@PathParam("id") Integer id) {
        final Speaker speaker = Optional.ofNullable(speakerService.findById(id))
                .orElse(new Speaker());
        this.models.put("speaker", speaker);
    }

    @Path("add")
    @POST
    @View("speaker.html")
    public void add(@BeanParam Speaker speaker) {
        if (speaker.getId() == null) {
            speakerService.insert(speaker);
        } else {
            speakerService.update(speaker.getId(), speaker);
        }
        this.models.put("speakers", speakerService.findAll());
    }


}