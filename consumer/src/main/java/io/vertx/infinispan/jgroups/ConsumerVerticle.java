package io.vertx.infinispan.jgroups;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.EventBus;

public class ConsumerVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerVerticle.class);

    @Override
    public void start() {
        LOGGER.info("Starting ConsumerVerticle...");

        final EventBus eb = vertx.eventBus();

        eb.<Integer>consumer("stats", stats -> LOGGER.info(String.format("Received stats: %s", stats)));

        eb.<String>consumer("message",
                message -> LOGGER.info("Received Message from channel news: " + message.body())
        );
    }
}
