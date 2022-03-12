package utils.common;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vinod Kumar
 */
@Slf4j
public class ExecuteTerminalCmd {

    private ExecuteTerminalCmd() {
    }

    @SneakyThrows
    public static List<String> executeCmd(String cmd) {
        List<String> output = new ArrayList<>();
        Process p = Runtime.getRuntime().exec(cmd);
        p.waitFor();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            output.add(line);
        }
        return output;
    }


    @SneakyThrows
    public static String executeCmd(String dirPath, String cmd) {
        StringBuilder output = new StringBuilder();
        File dir = new File(dirPath);
        Process p = Runtime.getRuntime().exec(cmd, null, dir);
        p.waitFor();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        return output.toString();
    }

}
