package tool;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.IOException;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: tool
 * @className JsonValidateTool
 * @description: 模板化方法校验json格式:  可以使用idea的插件pojo to json李大麦，生成json字符串；然后使用json schema tool工具生成 json schema字符串。最后使用json=schema-validate包校验json格式
 * @author: xj
 * @create: 2020-11-13 13:38:33
 **/
public class JsonValidateTool {

    public static void main(String[] args) {
        JsonValidateTool jsonValidateTool = new JsonValidateTool();
        String dataString = "";
        String schemaString = "";
        ProcessingReport processingMessages = jsonValidateTool.validate(dataString, schemaString);
        System.out.println(core.json.JsonUtil.toJsonString(processingMessages));
    }


    public ProcessingReport validate(String dataString, String schemaString) {
        ProcessingReport processingReport = null;
        try {
            JsonNode dataNode = JsonLoader.fromString(dataString);
            JsonNode schemaNode = JsonLoader.fromString(schemaString);
            JsonSchema schema = JsonSchemaFactory.byDefault().getJsonSchema(schemaNode);
            processingReport = schema.validate(dataNode);
            return processingReport;
        } catch (IOException | ProcessingException ex) {
            System.out.println(ex);
        }
        return processingReport;
    }
}
