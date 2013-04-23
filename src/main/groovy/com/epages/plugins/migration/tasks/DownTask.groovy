package com.epages.plugins.migration.tasks

import org.apache.ibatis.migration.commands.DownCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.google.common.io.NullOutputStream

class DownTask extends DefaultTask {

    File baseDir
    String environment
    Boolean force

    String steps = ""

    public DownTask(){
        setDescription("Executes migration down command.Configurable params: steps");
        setGroup("Migration");
    }

    @TaskAction
    void status() {
        def command = new DownCommand(baseDir, environment, force)
        command.setPrintStream(new PrintStream(new NullOutputStream()))
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute(steps)
    }
}
