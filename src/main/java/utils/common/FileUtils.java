package utils.common;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author Vinod Kumar
 */


@Slf4j
public class FileUtils {
    private FileUtils() {
    }


    public static Document readHtmlFile(String htmlFilePath) {
        try {
            return Jsoup.parse(new File(htmlFilePath), "ISO-8859-1");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }


    @SneakyThrows
    public static void zipFiles(String sourceFilePath, String destFilePath) {
        Path destinationPath = Files.createFile(Paths.get(destFilePath));
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(destinationPath))) {
            Path sPath = Paths.get(sourceFilePath);
            try (Stream<Path> walk = Files.walk(sPath)) {
                walk.filter(path -> !Files.isDirectory(path))
                        .forEach(path -> {
                                    ZipEntry zipEntry = new ZipEntry(sPath.relativize(path).toString());
                                    try {
                                        zs.putNextEntry(zipEntry);
                                        Files.copy(path, zs);
                                        zs.closeEntry();
                                    } catch (IOException e) {
                                        log.error(e.getMessage());
                                    }
                                }
                        );
            }
        }
    }
}
