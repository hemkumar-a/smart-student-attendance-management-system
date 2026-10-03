package config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DatabaseConfig {

    private static final String DEFAULT_URL =
            "jdbc:mysql://localhost:3306/smart_student_attendance"
            + "?useSSL=false&allowPublicKeyRetrieval=true";

    private static final String DEFAULT_USERNAME = "root";

    private static final Properties properties =
            new Properties();

    static {

        Path configPath =
                Paths.get(
                        "config",
                        "db.properties"
                );

        if (Files.exists(configPath)) {

            try (InputStream input =
                         Files.newInputStream(configPath)) {

                properties.load(input);

            } catch (IOException e) {

                System.err.println(
                        "Unable to read config/db.properties."
                );
            }
        }
    }

    public static String getUrl() {

        return getValue(
                "DB_URL",
                DEFAULT_URL
        );
    }

    public static String getUsername() {

        return getValue(
                "DB_USERNAME",
                DEFAULT_USERNAME
        );
    }

    public static String getPassword() {

        String password =
                getValue("DB_PASSWORD", null);

        if (password == null
                || password.isBlank()) {

            throw new IllegalStateException(
                    "Database password is not configured."
            );
        }

        return password;
    }

    private static String getValue(
            String key,
            String defaultValue) {

        String environmentValue =
                System.getenv(key);

        if (environmentValue != null
                && !environmentValue.isBlank()) {

            return environmentValue;
        }

        String propertyValue =
                properties.getProperty(key);

        if (propertyValue != null
                && !propertyValue.isBlank()) {

            return propertyValue;
        }

        return defaultValue;
    }
}