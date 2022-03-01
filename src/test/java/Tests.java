import com.vmware.python2jep.file.FileBasedPythonRunner;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class Tests
{
    @SneakyThrows
    @Test
    @DisplayName("Null scripts should not be accepted")
    void testNullScript(){
        FileBasedPythonRunner runner = new FileBasedPythonRunner();
        runner.execute(Paths.get("test.py"));
    }
}
