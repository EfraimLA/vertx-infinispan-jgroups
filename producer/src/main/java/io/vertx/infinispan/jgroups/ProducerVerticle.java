package io.vertx.infinispan.jgroups;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.EventBus;

import java.util.concurrent.ThreadLocalRandom;

public class ProducerVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerVerticle.class);

    @Override
    public void start() {
        LOGGER.info("Starting ProducerVerticle...");

        final EventBus eb = vertx.eventBus();

        vertx.setPeriodic(4000, l -> {
            LOGGER.info("Sending stats...");
            Integer stats = ThreadLocalRandom.current().nextInt(1, 10);
            eb.send("stats", stats);
        });

        vertx.createHttpServer()
                .requestHandler(rc -> {
                    LOGGER.info("Handling request & sending message...");

                    String message = "This is the message: Hello cx";

                    if (rc.getParam("message") != null) message = rc.getParam("message");

                    eb.send("message", message);

                    rc.response().end("Message Sent!");
                }).rxListen(config().getInteger("http.port", 8080))
                .subscribe(httpServer -> LOGGER.info("Server started successfully!"));
    }
}
