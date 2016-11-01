package com.mebigfatguy.stringliterals;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.selectors.FileSelector;
import org.junit.Assert;
import org.junit.Test;

public class StringLiteralsTaskTest {

    @Test
    public void testMe() {
        String[] someStrings = { "Fee", "Fi", "Fo", "Fum" };

        Project p = new Project();
        StringLiteralsTask task = new StringLiteralsTask();
        task.setProject(p);

        Path classPath = new Path(p);
        FileSet fs = new FileSet();
        fs.setProject(p);

        URL u = StringLiteralsTaskTest.class.getResource("/" + StringLiteralsTaskTest.class.getName().replace(".", "/") + ".class");
        String url = u.toString();
        String proto = u.getProtocol();
        if (proto.equals("file")) {
            int colonSlashPos = url.indexOf(":/");
            int classesPos = url.indexOf("classes/");
            String dir = url.substring(colonSlashPos + 1, classesPos + "classes/".length());
            File directory = new File(dir);
            Assert.assertTrue(directory.isDirectory());
            fs.setDir(directory);
        } else if (u.getProtocol().equals("jar")) {

        }

        fs.add(new FileSelector() {

            @Override
            public boolean isSelected(File basedir, String filename, File file) throws BuildException {
                return file.isDirectory() || filename.endsWith(".class");
            }

        });

        classPath.addFileset(fs);
        task.addConfiguredClasspath(classPath);

        final List<String> messages = new ArrayList<>();

        p.addBuildListener(new BuildListener() {

            @Override
            public void buildStarted(BuildEvent event) {
            }

            @Override
            public void buildFinished(BuildEvent event) {
            }

            @Override
            public void targetStarted(BuildEvent event) {
            }

            @Override
            public void targetFinished(BuildEvent event) {
            }

            @Override
            public void taskStarted(BuildEvent event) {
            }

            @Override
            public void taskFinished(BuildEvent event) {
            }

            @Override
            public void messageLogged(BuildEvent event) {
                if (event.getPriority() == 0) {
                    messages.add(event.getMessage());
                }
            }
        });

        task.execute();

        Assert.assertEquals(19, messages.size());
        Assert.assertTrue(messages.contains("\".class\" in com.mebigfatguy.stringliterals.StringLiteralsTask.execute()V"));

    }
}
