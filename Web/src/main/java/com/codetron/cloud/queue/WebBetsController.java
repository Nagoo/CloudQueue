package com.codetron.cloud.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;

/**
 * Created by josete on 11/07/2016.
 */
@Controller
public class WebBetsController {


    private BetCreatorService betCreatorService;

    @Autowired
    public WebBetsController(BetCreatorService betCreatorService) {
        this.betCreatorService = betCreatorService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }


    @RequestMapping(value ="/bet/{USER}/{NUM}", method = RequestMethod.POST)
    public String createBets(@PathParam("USER") final Long user, @PathParam("NUM") final String number) {

        this.betCreatorService.createBet(user, number);

        return "redirect:/index";

    }
}
