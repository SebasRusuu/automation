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

.PHONY: web
web:
	cd site && npm install && npm run deploy
