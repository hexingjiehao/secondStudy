package other.jsonSchema;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaFactory;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * 使用json schema验证一个json格式文件是否符合要求
 * 暂时没有成功，可能是vertx版本的问题
 */
public class DemoJsonSchema {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        ConfigStoreOptions fileStore = new ConfigStoreOptions()
                .setType("file")
                .setOptional(true)
                .setFormat("yaml")
                .setConfig(new JsonObject()
                        .put("path", "tenant-general-purchase-eb-config.yml"));


        ConfigStoreOptions jsonStore = new ConfigStoreOptions()
                .setType("json")
                .setOptional(true)
                .setConfig(new JsonObject("{\"eventbus\":{\"pg\":{\"connect\":{\"database\":\"postgres\",\"user\":\"postgres\",\"password\":\"postgres\",\"host\":\"localhost\",\"port\":32769},\"pool\":{\"maxSize\":1}}}}"));

        ConfigRetrieverOptions options = new ConfigRetrieverOptions()
                .setScanPeriod(0)
                .addStore(fileStore)
                .addStore(jsonStore);

        ConfigRetriever.create(vertx, options).getConfig()
                .compose(DemoJsonSchema::validateJson)
                .setHandler(ar -> {
                    if (ar.succeeded()) {
                        System.out.println("ok");
                    } else {
                        System.out.println("fail");
                    }
                });

    }

    private static Future<Void> validateJson(@NotNull JsonObject config) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance();

        Vertx vertx = Vertx.vertx();
        vertx.fileSystem().readFile("tenant-general-purchase-eb.json", ar -> {
            Buffer buffer = ar.result();
            try {
                Set errors = factory
                        .getSchema(buffer.toString())
                        .validate(new ObjectMapper().readTree(config.encode()));

                if (errors.size() > 0) {
                    StringBuilder error = new StringBuilder(1024);
                    errors.forEach(m -> error.append("\n").append(m));
                    throw new IllegalArgumentException(error.toString());
                }
                System.out.println("验证成功！！！");
            } catch (Exception e) {
                System.out.println("验证失败！！！");
            }
        });
        return Future.succeededFuture();
    }

}
