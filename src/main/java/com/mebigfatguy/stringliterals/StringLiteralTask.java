package com.mebigfatguy.stringliterals;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;

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
    }
}
