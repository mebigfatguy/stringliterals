# stringliterals
A tool for parsing out all string literals used in a code base


Add an ant task such as

	<target name="literals" depends="jar" xmlns:stringliterals="antlib:com.mebigfatguy.stringliterals" description="generate report of string literals used in code">
		<stringliterals:stringliterals>
			<classpath id="source">
				<fileset dir="${main.classes.dir}">
					<include name="**/*.class"/>
				</fileset>
			</classpath>
		</stringliterals:stringliterals>
	</target>
  
  And you will get a report of all literals used by class
  
  
  Available on maven central with coordinates
  
      groupId: com.mebigfatguy.stringliterals
   artifactId: stringliterals
      version: 0.2.0
