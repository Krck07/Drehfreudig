import Exeptions.FailedReadingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Reader {

    public static String readString(String filepath) {
        String res;

        try{
            res = Files.readString(Paths.get("src/main/input",filepath));
        } catch (IOException e){
            throw new FailedReadingException("This file might not exist. -- "+filepath);
        }

        if(res.chars()
                .filter(c -> !Character.isWhitespace(c))
                .allMatch(c -> c == '(' || c == ')'))
            return res.chars()
                    .filter(c -> !Character.isWhitespace(c))
                    .collect(StringBuilder::new,
                            (sb, c) -> sb.append((char) c),
                            StringBuilder::append)
                    .toString();
        else throw new FailedReadingException("This file contains unwanted characters. -- "+filepath);
    }

    public static char[] readChars(String filepath) {
        return readString(filepath).toCharArray();
    }
}
