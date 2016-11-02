/*
 * stringliterals - A tool for parsing out all string literals used in a code base
 * Copyright 2016-2016 MeBigFatGuy.com
 * Copyright 2016-2016 Dave Brosius
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations
 * under the License.
 */
package com.mebigfatguy.stringliterals;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Resource;
import org.objectweb.asm.ClassReader;

public class StringLiteralsTask extends Task {

    private Path path;

    public void addConfiguredClasspath(final Path classpath) {
        path = classpath;
    }

    @Override
    public void execute() {

        if (path == null) {
            throw new BuildException("No sub <classpath> element was found");
        }

        Project project = getProject();
        project.log("Path is " + path, Project.MSG_VERBOSE);

        try {

            project.log("Scanning classes...", Project.MSG_VERBOSE);
            SLClassVisitor visitor = new SLClassVisitor();
            for (Resource resource : path) {
                if (!resource.isDirectory() && resource.getName().endsWith(".class")) {

                    try (InputStream is = new BufferedInputStream(resource.getInputStream())) {
                        project.log("Parsing " + resource.getName(), Project.MSG_DEBUG);
                        ClassReader reader = new ClassReader(is);
                        reader.accept(visitor, ClassReader.SKIP_DEBUG);
                    }
                }
            }

            List<Literal> literals = new ArrayList<>(visitor.getLiterals());
            project.log(String.format("Sorting classes [%d]...", literals.size()), Project.MSG_VERBOSE);
            Collections.sort(literals);

            for (Literal literal : literals) {
                project.log(literal.toString(), Project.MSG_ERR);
            }

        } catch (IOException e) {
            throw new BuildException("Failed parsing class", e);
        }
    }
}
