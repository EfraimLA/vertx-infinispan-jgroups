package io.vertx.infinispan.jgroups;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.junit5.Checkpoint;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ProducerVerticle Test!")
@ExtendWith(VertxExtension.class)
public class ProducerVerticleTest {

    private static final int PORT = 8081;

    @DisplayName("Testing Http Route")
    @Test
    void callGreetingTest(final Vertx vertx, final VertxTestContext context) {
        final WebClient client = WebClient.create(vertx);
        final Checkpoint deploymentCheckpoint = context.checkpoint();
        final Checkpoint requestCheckpoint = context.checkpoint();

        vertx.deployVerticle(new ProducerVerticle(),
                new DeploymentOptions().setConfig(new JsonObject().put("http.port", PORT)),
                context.succeeding(id -> {
                    deploymentCheckpoint.flag();

                    client.get(PORT, "localhost", "/")
                            .send(context.succeeding(res -> context.verify(() -> {
                                assertThat(res.statusCode()).isEqualTo(200);
                                assertThat(res.bodyAsString()).isEqualTo("Message Sent!");
                                requestCheckpoint.flag();
                            })));
                })
        );
    }
}
