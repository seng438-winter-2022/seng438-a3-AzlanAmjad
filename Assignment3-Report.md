**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**

| Group \#:      | 04          |
| -------------- | ----------- |
| Student Names: | Azlan Amjad |
|                | Saud Agha   |
|                | Sajid Hafiz |
|                | Rohan Amjad |

# 1 Introduction

Text…

# 2 Manual data-flow coverage calculations for X and Y methods

Saud and Rohan

# 3 A detailed description of the testing strategy for the new unit test

White-box testing: Control Flow and Data Flow Coverage

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

For the first part of the test design component, we measure the adequacy of our current test suite using one or more code coverage tools.

## Measuring Control Flow Coverage

To measure control flow coverage of our current test suite, we have decided to use EclEmma.

The first step is to select classes for instrumentation. We have selected **org.jfree.data.DataUtilities** and **org.jfree.data.Range**, since these are the classes we wrote unit tests for in the previous assignment. For the second step, we ran the EclEmma code coverage analysis tool on these classes.

For our current test suite we will report three coverage metrics. The three metrics are listed below

DataUtilities
- **Statement/Instruction** Coverage = 175 / 329 * 100% = **53.2%**
- **Branch** Coverage = 22 / 48 * 100% = **45.8%**
- **Method** Coverage = 5 / 10 * 100% = **50.0%**

Range
- **Statement/Instruction** Coverage = 87 / 465 * 100% = 18.7%
- **Branch** Coverage = 4 / 72 * 100% = **5.6%**
- **Method** Coverage = 7 / 23 * 100% = **30.4%**

## Measuring Data Flow Coverage Manually



## Test Suite Development



# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

Text…

# 6 Pros and Cons of coverage tools used and Metrics you report

...using one or more code coverage tools and report about the pros and cons of your metrics and tool choices

You need to report the pros and cons of tools you tried and the metrics you chose

EclEmma




# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

Text…

# 8 A discussion on how the team work/effort was divided and managed

Text…

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

Text…

# 10 Comments/feedback on the lab itself

Text…
