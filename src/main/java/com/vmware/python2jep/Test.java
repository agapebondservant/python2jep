package com.vmware.python2jep;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import jep.Interpreter;
import jep.MainInterpreter;
import jep.SharedInterpreter;

public class Test {
    public static void main(String[] args) throws IOException {
        String pathToFile =
                String.format("python %s/%s/testing.py",
                    Paths.get(".").normalize().toAbsolutePath(),"lib");
        System.out.println(pathToFile);

        Process p = Runtime.getRuntime().exec(pathToFile);
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String ret = in.readLine();
        System.out.println("the jep's built C library is at: "+ret);
        MainInterpreter.setJepLibraryPath(ret);
        Interpreter interp = new SharedInterpreter();
        interp.exec("from java.lang import System");
        interp.exec("s = 'Hello World!!!'");
        interp.exec("System.out.println(s)");
        interp.exec("print(s)");
        interp.exec("print(s[1:-1])");
        interp.exec("x=1+1");
        System.out.println(interp.getValue("x"));
    }
}
