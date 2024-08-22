### Run normal:

To run the tests, you can do the following command:
```bash
  mvn clean test
  ```
After each test, it will generate a report in the target folder,
  where you can check the results of the tests by doing next command:
#### Allert ⚠️
The allure report will only generate for Excellent and Outstanding projects tests!
```bash
  mvn allure:serve
  ```

### Run with Make:

We can run the test with Make following the next commands:

```bash
  make test
  ```

After making the tests, we can generate the report with the next command:
```bash
  make report
  ```
### Allert ⚠️
The allure report will only generate for Excellent and Outstanding projects tests!

### Run only Outstanding project
The outstanding project it's the final product of the automation project,
where we have all the best practices and the best way to create an automation project.

To run only the outstanding project, you can do the following command:
```bash
  make test-outstanding
  ```
