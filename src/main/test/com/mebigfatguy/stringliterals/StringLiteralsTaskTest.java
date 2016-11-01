package com.mebigfatguy.stringliterals;

import java.io.File;
import java.net.URL;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.DirSet;
import org.apache.tools.ant.types.Path;
import org.junit.Test;

public class StringLiteralsTaskTest {

    @Test
    public void testMe() {
        String[] someStrings = { "Fee", "Fi", "Fo", "Fum" };

        Project p = new Project();
        StringLiteralTask task = new StringLiteralTask();
        task.setProject(p);

        Path classPath = new Path(p);
        DirSet ds = new DirSet();

        URL u = StringLiteralsTaskTest.class.getResource("/" + StringLiteralsTaskTest.class.getName().replace(".", "/") + ".class");
        String url = u.toString();
        String proto = u.getProtocol();
        if (proto.equals("file")) {
            int colonSlashPos = url.indexOf(":/");
            int classesPos = url.indexOf("classes/");
            String dir = url.substring(colonSlashPos + 1, classesPos + "classes/".length());
            ds.setDir(new File(dir));
        } else if (u.getProtocol().equals("jar")) {

        }

        ds.setIncludes("**/*.class");
        classPath.addDirset(ds);
        task.setClassPath(classPath);

        task.execute();
    }
}
