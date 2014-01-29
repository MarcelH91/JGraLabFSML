Workspace setup

0. The FSML Project: Put it into your workspace.

1. Install an AspectJ Tool (http://www.eclipse.org/ajdt/).

2.1. JGralab: Download the JGralab project from https://github.com/JGralab/ and put it into the same workspace

2.2. JGralab: Execute JGralab's build.xml as an ANT Build.

3. GraphViz: Install GraphViz. Don't forget to add the location of the dot.exe file to your paths.

4. Execute the FSML project's build.xml as an ANT Build. The build should succeed while the error msg says something else.

A Sample of how the FSML Project works can be found at FSML/src/fsml/FSMLMain.java
-----------------------------------


FSMLSchema
The abstract Syntax is given here in a schemagraph using JGralab. It's
based on JGralab's grUML technology, which is close to UML's layers.
I.)There exists a schema for grUML, which could be compared to UML's MOF. 
II.) Instances of that meta schema are schemagraphs like in FSMLSchema.tg. They
are at the same layer as Metamodels in UML. FSMLGrumlGenerator is a class, which has the purpose
of creating a schemagraph for FSML.
III.) Instances of a schemagraph can be created by instanciating objects from
the fsml.schema-package. Those are on the same layer as actual UML-Models.



Operational Semantics Implementations
This project offers two implementations concerning the execution of an FSM.

I.) Using Graph-Traversion 
The first one can be found at fsml.semantics.traverse.TravSimulator. This
implementation uses a simple algorithm which traverses the graph in order to
achieve its goal. As an operational semantics it keeps track of an actual
configuration, which mainly contains a current active State-object. When
processing a new input-String, it directly searches a transition, which has
the matching input-attribute.

II.) Using Schema-Extension
The second one can be found at fsml.semantics.extending.SESimulator. Before
looking at this implementation, you have to take a look at some AspectJ in the
package fsml.semantics.extending.FSMLSchemaExtensionGen.aj. The basic Schema-
Implementation is extended by two operations. The first one adds the method 
getNextTransition(String input) to the State-class using aspect oriented 
programming. This is done via AspectJ, becausewe don't want to change the 
actual schema-implementation, but we still want toextend the schema with 
operations. Now a state-object is able to calculate thenext active transition
 and thus the next active state. The second methodmentioned in the aspect adds
the method getOutput() to the Transition-class inorder to let Transition-objects 
compute their own output individually. 
