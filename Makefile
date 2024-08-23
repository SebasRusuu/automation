test:
	mvn test

test-outstanding:
	cd java-selenium4/outstanding && make test && make report

build:
	mvn clean install -DskipTests

report:
	mvn allure:report

checkstyle:
	mvn checkstyle:checkstyle

web:
	npm install && npm run deploy
