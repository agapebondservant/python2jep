package com.vmware.python2jep.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Utility class for processing files
 * @author oawofolu
 */
public class FileUtil {
    public static String readFile(Path path) throws IOException{
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( path, StandardCharsets.UTF_8 )) {
            stream.forEach(s -> contentBuilder.append(s).append( System.lineSeparator() ));
        }

        return contentBuilder.toString();
    }
}
