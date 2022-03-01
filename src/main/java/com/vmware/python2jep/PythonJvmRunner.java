package com.vmware.python2jep;

import jep.Interpreter;
import jep.MainInterpreter;
import jep.SharedInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * Base interface which executes a given python script in the JVM using JEP:
 * see <a href="#{@link}">{@link> https://github.com/ninia/jep}</a>
 * @param <T> - represents the python script to be converted
 * @author oawofolu
 */
public interface PythonJvmRunner<T> {

    static Logger LOG = LoggerFactory.getLogger( PythonJvmRunner.class );

    /**
     * Main method responsible for executing the python script
     * @param pythonObject
     */
    public default void execute( T pythonObject ) throws Exception {
        LOG.info( "Executing: Python object = {}", pythonObject );

        initialize();

        try (Interpreter interpreter = new SharedInterpreter()) {

            String pythonScript = loadPython(pythonObject);

            interpreter.exec(pythonScript);
        } catch ( Exception ex ) {
            LOG.error( ex.getLocalizedMessage(), ex );
        }
    }

    default void initialize() {

        String dyldPath = System.getenv("DYLD_LIBRARY_PATH");
        String pythonHomePath = System.getenv("PYTHONHOME");
        String ldflagsPath = System.getenv("LDFLAGS");

        LOG.info("\nBase path = {}\n Python Home = {}", dyldPath, pythonHomePath);

        // set JEP Library Path (necessary so that the Classloader knows where to load the native C libraries from)
        MainInterpreter.setJepLibraryPath(
            Paths.get(dyldPath,"python3.8", "site-packages", "jep", "libjep.jnilib")
                    .normalize().toAbsolutePath().toString());

//        MainInterpreter.setJepLibraryPath(
//                Paths.get(".","lib", "libiconv.2.dylib").normalize().toAbsolutePath().toString());
//
//        MainInterpreter.setJepLibraryPath(
//                Paths.get(".","lib", "libarchive.dylib").normalize().toAbsolutePath().toString());

        // DYLD_LIBRARY_PATH=/Users/oawofolu/opt/anaconda3/lib:/usr/lib

//        System.loadLibrary(
//                Paths.get(".","lib", "jep.cpython-38-darwin.so").normalize().toAbsolutePath().toString());
//        System.loadLibrary(
//                Paths.get(dyldPath,"libiconvs.2.dylib").normalize().toAbsolutePath().toString());

    }


    /**
     * Retrieves a String representation of the python script
     * @param pythonObject - script to be executed on the JVM
     */
    String loadPython( T pythonObject ) throws IOException;

}
