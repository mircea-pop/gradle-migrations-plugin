package com.epages.plugins.migration.tasks;

import org.apache.ibatis.migration.commands.BootstrapCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.google.common.io.NullOutputStream

class BootstrapTask extends DefaultTask {

    File baseDir
    String environment
    Boolean force

    public BootstrapTask(){
        setDescription("Bootstrap migrations");
        setGroup("Migration");
    }

    @TaskAction
    void bootstrap() {
        def command = new BootstrapCommand(baseDir, environment, force)
        command.setPrintStream(new PrintStream(new NullOutputStream()))
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute()
    }
}
