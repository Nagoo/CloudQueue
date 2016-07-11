/******************************************************************************
 * Copyright (C) 2005 - 2016 Ventura24 S.L.U.                                *
 * *
 * Copyright and license details are included in Ventura24 license file.     *
 ******************************************************************************/
package com.codetron.cloud.queue.draw;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *****************************************************************************
 *  Copyright (C) 2005 - 2016 Ventura24 S.L.U.                                *
 *                                                                            *
 *  Copyright and license details are included in Ventura24 license file.     *
 ******************************************************************************
 * Created by jsanc on 11/07/16.
 */
public class Receiver {


    private static final AtomicInteger counter = new AtomicInteger(0);

    private DrawService drawService;

    private final static Integer MAX_BETS = 10;

    @Autowired
    public Receiver(final DrawService drawService) {
        this.drawService = drawService;
    }

    public void createDraw(final BetDTO bet) {

        if (counter.compareAndSet(MAX_BETS,0)) {
            // let's play draw !
            drawService.playDraw();
        } else {
            counter.incrementAndGet();
            this.drawService.storeNumber(bet.getNumber());
        }

    }
}
