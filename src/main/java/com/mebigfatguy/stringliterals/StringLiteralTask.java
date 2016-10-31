package com.mebigfatguy.stringliterals;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Resource;
import org.objectweb.asm.ClassReader;

public class StringLiteralTask extends Task {

    private Path path;

    public void setClassPath(Path classPath) {
        path = classPath;
    }

    @Override
    public void execute() {

        if (path == null) {
            throw new BuildException("Property 'path' not set");
        }

        try {

            for (Resource resource : path) {

                if (!resource.isDirectory() && resource.getName().endsWith(".class")) {

                    try (InputStream is = new BufferedInputStream(resource.getInputStream())) {
                        ClassReader reader = new ClassReader(is);
                        reader.accept(new SLClassVisitor(), ClassReader.SKIP_DEBUG);
                    }
                }
            }
        } catch (IOException e) {
            throw new BuildException("Failed parsing class", e);
        }
    }
}
