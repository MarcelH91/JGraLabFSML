Workspace setup

For the FSML project to work properly, you'll need 3 different libraries.

0. The FSML Project: Put it into your workspace.
1. ANTLR: The library can be found at the projects 'lib' folder. It has to be added to the build path.
2. jgralab: Download the jgralab project from https://github.com/jgralab/ and put it into the same workspace
3. GraphViz: Install GraphViz. Don't forget to add the location of the dot.exe file to your paths.

The build file still has to be improved to set up the whole project. The jgralab project has to be
built first, which will be done by the improved build file automatically.

A Sample of how the FSML Project works can be found at /src/fsml/FSMLMain.java
