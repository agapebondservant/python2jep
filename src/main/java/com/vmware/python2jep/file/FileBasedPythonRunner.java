package com.vmware.python2jep.file;

import com.vmware.python2jep.PythonJvmRunner;
import com.vmware.python2jep.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * File based implementation of the JVM runner; converts a Python file into its String representation.
 * @author oawofolu
 */
public class FileBasedPythonRunner implements PythonJvmRunner<Path> {

    static Logger LOG = LoggerFactory.getLogger( FileBasedPythonRunner.class );

    @Override
    public String loadPython(Path pathToScript) throws IOException {

        String content = FileUtil.readFile(pathToScript);

        LOG.info("Python script to be executed: \n{}", content);

        return content;

    }
}
