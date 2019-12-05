package io.vertx.infinispan.jgroups;

import io.vertx.core.Vertx;
import io.vertx.junit5.Checkpoint;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName("ConsumerVerticle Test")
@ExtendWith(VertxExtension.class)
public class ConsumerVerticleTest {

    @DisplayName("Deploy Verticle Test")
    @Test
    void deployVerticleTest(final Vertx vertx, final VertxTestContext context) {
        final Checkpoint deploymentCheckpoint = context.checkpoint();

        vertx.deployVerticle(new ConsumerVerticle(),
                context.succeeding(id -> deploymentCheckpoint.flag()));
    }
}
