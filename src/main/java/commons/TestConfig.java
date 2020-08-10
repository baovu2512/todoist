package commons;

import api.models.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mobile.core.AndroidDevice;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestConfig {
    private final static ObjectMapper MAPPER = new ObjectMapper();

    public final static String CONFIG_PATH =
            Paths.get(Paths.get("").toAbsolutePath().toString(), "src", "test", "resources", "configs")
                    .toString();
    public final static String DATA_PATH =
            Paths.get(Paths.get("").toAbsolutePath().toString(), "src", "test", "resources", "data")
                    .toString();
    public final static Path TARGET_PATH =
            Paths.get(Paths.get("").toAbsolutePath().toString(), "target");


    public static User getUserConfig() throws IOException {
        // read json
        File jsonFile = Paths.get(DATA_PATH, "user.json").toFile();
        String content = FileUtils.readFileToString(jsonFile, "utf-8");
        JsonNode node = MAPPER.readTree(content);

        User user = new User();
        user.setUsername(node.get("username").asText());
        user.setEmail(node.get("email").asText());
        user.setPassword(node.get("password").asText());

        return user;
    }

    public static AndroidDevice getAndroidDevice() throws IOException {
        // read json
        File jsonFile = Paths.get(CONFIG_PATH, "android", "devices.json").toFile();
        String content = FileUtils.readFileToString(jsonFile, "utf-8");
        JsonNode node = MAPPER.readTree(content);

        JsonNode deviceNode = node.get("device");

        AndroidDevice device = new AndroidDevice();
        device.setUdid(deviceNode.get("udid").asText());
        device.setSystemPort(deviceNode.get("systemPort").asInt());
        device.setPlatformVersion(deviceNode.get("platformVersion").asText());
        device.setPort(deviceNode.get("port").asInt());

        return device;
    }
}
