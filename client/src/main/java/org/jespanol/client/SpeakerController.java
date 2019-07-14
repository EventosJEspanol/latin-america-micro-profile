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
package org.jespanol.client;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Collections;

@Controller
@Path("speaker")
public class SpeakerController {

    @Inject
    private Models models;

    @Inject
    private Messages message;

    @Inject
    private Errors erros;

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
        final Speaker speaker = new Speaker();
        speaker.setTwitter("otaviojava");
        speaker.setName("Otavio");
        speaker.setGithub("otaviojava");
        speaker.setId(12);
        speaker.setBio("Bio bio");
         this.models.put("speaker", speaker);
    }

    @Path("add")
    @POST
    @View("speaker.html")
    public void add(@BeanParam Speaker speaker) {
        speakerService.insert(speaker);
        this.models.put("speakers", speakerService.findAll());
    }


}