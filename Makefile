Compile:
	mvn clean test

Compile-skipTests:
	mvn clean install -DskipTests

View-report:
	mvn allure:serve

View-checkstyle:
	mvn checkstyle:checkstyle

