package Vertx;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class VertxConfig extends AbstractVerticle {

    /**
     * 公司项目：先加载yaml配置，然后加载vert.x core的context内容。后者可以在初始化时被修改
     * 配置文件直接在resources目录下
     * @param args
     */
    public static void main(String[] args) {
        VertxConfig config=new VertxConfig();
        config.get();
    }

    public void get(){
        Vertx vertx=Vertx.vertx();
        //配置存储器选项
        ConfigStoreOptions fileStore = new ConfigStoreOptions()
                .setType("file")
                .setOptional(true)
                .setFormat("yaml")
                .setConfig(new JsonObject()
                        .put("path", String.format("%s-config.yml", "full-service-name"))
                );
        ConfigStoreOptions jsonStore = new ConfigStoreOptions()
                .setType("json")
                .setOptional(true)
                .setConfig(config());

        //配置检索器选项
        ConfigRetrieverOptions options = new ConfigRetrieverOptions()
                .setScanPeriod(0L)
                .addStore(fileStore)
                .addStore(jsonStore);

        //配置检索器创建
        ConfigRetriever.create(vertx, options)
                .getConfig(ar ->{
                    System.out.println(ar.result());
                });
    }

}
