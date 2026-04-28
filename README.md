# OrangeHRM BDD Mini Framework

A minimal BDD-style automation framework for the OrangeHRM open-source demo
(`https://opensource-demo.orangehrmlive.com/web/index.php/auth/login`),
built with **Cucumber + Selenium WebDriver + JUnit 4 + Maven**.

It demonstrates the layering you typically want in a BDD UI framework:

```
.feature   ──>  step definitions  ──>  page objects (locators + actions)  ──>  driver
```

## Project layout

```
testIQ/
├─ pom.xml
├─ src/
│  ├─ main/java/com/orangehrm/
│  │   ├─ driver/DriverFactory.java         # ThreadLocal WebDriver, Chrome/Firefox
│  │   ├─ pages/                            # Page Object Model
│  │   │   ├─ BasePage.java
│  │   │   ├─ LoginPage.java                # locators + login actions
│  │   │   ├─ DashboardPage.java            # sidebar / header / logout
│  │   │   └─ AdminPage.java                # user search
│  │   └─ utils/
│  │       ├─ ConfigReader.java             # reads config.properties + -D overrides
│  │       └─ WaitUtils.java                # explicit-wait helpers
│  └─ test/
│     ├─ java/com/orangehrm/
│     │   ├─ hooks/Hooks.java               # @Before / @After + screenshot on fail
│     │   ├─ runners/TestRunner.java        # Cucumber JUnit runner
│     │   └─ stepdefs/                      # Glue
│     │       ├─ LoginSteps.java
│     │       ├─ DashboardSteps.java
│     │       └─ AdminSteps.java
│     └─ resources/
│         ├─ config/config.properties
│         └─ features/
│             ├─ login.feature
│             ├─ dashboard_navigation.feature
│             ├─ admin_user_search.feature
│             └─ logout.feature
```

## What is covered

| Feature file                     | Scenarios                                                   |
|----------------------------------|-------------------------------------------------------------|
| `login.feature`                  | Valid login, invalid credentials (outline), empty fields    |
| `dashboard_navigation.feature`   | Default dashboard, sidebar navigation to Admin / PIM / Leave |
| `admin_user_search.feature`      | Search for existing user, search returning no records       |
| `logout.feature`                 | Logout returns to login page                                |

## Prerequisites

- **JDK 11+**
- **Maven 3.8+**
- **Chrome** installed (or Firefox; selectable via `-Dbrowser=firefox`).
  WebDriverManager handles the matching driver binary automatically.

## Running

Run all features (headless Chrome by default):

```bash
mvn clean test
```

Run a specific tag (e.g. only smoke):

```bash
mvn clean test -Dcucumber.filter.tags="@smoke"
```

Run with a non-headless browser:

```bash
mvn clean test -Dheadless=false
```

Run on Firefox:

```bash
mvn clean test -Dbrowser=firefox
```

## Configuration

Defaults live in `src/test/resources/config/config.properties` and can be
overridden via `-D` system properties (`ConfigReader` checks system properties
first, then the file).

| Key                          | Default                                                                          |
|------------------------------|----------------------------------------------------------------------------------|
| `base.url`                   | `https://opensource-demo.orangehrmlive.com/web/index.php/auth/login`             |
| `browser`                    | `chrome`                                                                         |
| `headless`                   | `true`                                                                           |
| `implicit.wait.seconds`      | `5`                                                                              |
| `explicit.wait.seconds`      | `15`                                                                             |
| `page.load.timeout.seconds`  | `30`                                                                             |
| `default.username`           | `Admin`                                                                          |
| `default.password`           | `admin123`                                                                       |

## Reports

After a test run, open:

- `target/cucumber-reports/cucumber.html` — HTML report
- `target/cucumber-reports/cucumber.json` — JSON (for further processing)

Failed scenarios automatically attach a PNG screenshot via `Hooks.afterScenario`.

## Extending

To add a new flow:

1. Add a Page Object in `com.orangehrm.pages` with locators (`By` constants) and
   action methods.
2. Create or extend a `*.feature` file in `src/test/resources/features`.
3. Wire the Gherkin steps to Java in `com.orangehrm.stepdefs`.

That's the whole loop: **feature → step → page → driver**.
