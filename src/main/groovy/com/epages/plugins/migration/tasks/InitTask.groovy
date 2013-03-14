package com.epages.plugins.migration.tasks;

import org.apache.ibatis.migration.commands.InitializeCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class InitTask extends DefaultTask {
  
  File baseDir
  String environment
  Boolean force
  
  public InitTask(){
    setDescription("Create migrations structure");
    setGroup("Migration");
  }
  
  @TaskAction
  void createDirectories() {
    
    if(baseDir.exists() && force) {
	  logger.info "Deleting directory."
	  baseDir.deleteDir()
    }

	if (!baseDir.exists()) {
		logger.info "Creating directory."
		baseDir.mkdirs();
	} else {
	   logger.info "migrations dir already exists: " + baseDir.getAbsolutePath()
	   return
	}
    
    def command = new InitializeCommand(baseDir, environment, force)
    CommandHelper.updateDriverClassLoader(project, command)
    command.execute()

    logger.info "Directory created at '${baseDir.absolutePath}'."
  }
}
