package ru.bigcheese.jsalon.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by BigCheese on 05.08.15.
 */
public final class DBUtils {

    public static List<String> extractSQLMessages(SQLException e) {
        List<String> messages = new ArrayList<>();
        if (e != null) {
            for (Throwable t : e) {
                messages.add(t.getMessage());
            }
        }
        return messages;
    }

    public static List<String> parseSQLQueries(String filename) throws IOException {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        InputStream stream = DBUtils.class.getClassLoader().getResourceAsStream(filename);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        StringTokenizer tokenizer = new StringTokenizer(sb.toString(), ";", false);
        while (tokenizer.hasMoreTokens()) {
            String query = tokenizer.nextToken().replaceAll("[\\n\\t\\r]", " ");
            result.add(query);
        }
        return result;
    }

    /**
     * Escape special characters that matter to LIKE statement
     * Default escape symbol is '!'
     * @param input String param for LIKE statement
     * @return Escaped string param
     */
    public static String likeSanitize(String input) {
        return input.replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
    }
}
