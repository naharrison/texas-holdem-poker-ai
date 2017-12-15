#!/bin/csh -f

mvn install

mvn dependency:copy-dependencies

java -cp "target/texasai-1.0-SNAPSHOT.jar:target/dependency/aopalliance-1.0.jar:target/dependency/guice-3.0.jar:target/dependency/guice-assistedinject-3.0.jar:target/dependency/guice-servlet-3.0.jar:target/dependency/h2-1.3.168.jar:target/dependency/hamcrest-core-1.1.jar:target/dependency/javax.inject-1.jar:target/dependency/junit-4.10.jar" edu.ntnu.texasai.Play demo
