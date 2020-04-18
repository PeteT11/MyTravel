/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chordant.mytravelskill;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import com.chordant.mytravel.handlers.CancelandStopIntentHandler;
import com.chordant.mytravel.handlers.HelpIntentHandler;
import com.chordant.mytravel.handlers.LaunchRequestHandler;
import com.chordant.mytravel.handlers.SessionEndedRequestHandler;
import com.chordant.mytravelskill.handlers.StartJourneyIntentHandler;
import com.chordant.mytravelskill.handlers.StopJourneyIntentHandler;

public class HelloWorldStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new StartJourneyIntentHandler(),
                        new StopJourneyIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler())
                .build();
    }

    public HelloWorldStreamHandler() {
        super(getSkill());
    }

}
