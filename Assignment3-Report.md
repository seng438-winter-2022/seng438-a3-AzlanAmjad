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

DataUtilities - calculateColumnTotal(): 

Data Flow Graph: 

![calcColumnTotal](https://github.com/seng438-winter-2022/seng438-a3-AzlanAmjad/blob/919e828de1110f588dfd43e8bf421ae9fa7ff117/media/dfgcalcColumnTotal.png)

def-use sets per statement:

def(1): {data, column}

use(1): {}

def(2): {total, rowCount}

use(2): {data}

def(3): {r}

use(3): {r}

def(4): {n}

use(4): {data}

def(5): {}

use(5): {}

def(6): {}

use(6): {n}

def(7): {}

use(7): {}

def(8): {total}

use(8): {n}


DU-pairs per variable:

data:

{1,2}, {1,4}

column: 

{1,4}

total:


rowCount:

{2,4}

r:

{3,3}

n:

{4,6}, {4,8}



Which pairs are covered for each test case:

testCalculateFirstColumnTotal: {1,2}, {1,4}, {2,4}, {3,3}, {4,6}, {4,8}


DU-Pair coverage:

CU = 4

PU = 2

CUc = 4

CUp = 2

CUf = 0

PUf = 0

CU + PU / (CUc + PUc) - (CUf + PUf)

4 + 2 / (4 + 2) - (0 + 0) = 1 * 100% = 100% coverage



Range - getCentralValue(): 

Data Flow Graph: 

![getCentralValue](https://github.com/seng438-winter-2022/seng438-a3-AzlanAmjad/blob/20a429f0686042bdbb32bf35e15259464213dd35/media/Range.getCentralValue_Dataflow_Graph.jpg)

def-use sets per statement:

def(1): {lower, upper}

use(1): {}

def(2): {}

use(2): {lower, upper}

def(3): {}

use(3): {}

def(4): {lower, upper}

use(4): {}

def(6): {}

use(6): {lower, upper}


DU-pairs per variable:

lower: 

{1,2}, {1,6}, {4,6} 

upper: 

{1,2}, {1,6}, {4,6}


Which pairs are covered for each test case:

calculatingDoubleMaxtoDoubleMin: {1,2}, {1,6}, {4,6} 

calculatingDoubleMaxPlusOneDoubleMinPlusOne: {1,2}, {1,6}, {4,6} 

calculatingDoubleMaxMinusOneDoubleMinMinusOne: {1,2}, {1,6}, {4,6} 

calculatingZeroAndHundred: {1,2}, {1,6}, {4,6} 

calculatingCentralValueOfTwoNegativeRangeValues: {1,2}, {1,6}, {4,6} 


DU-Pair coverage:

CU = 1

PU = 1

CUc = 1

CUp = 1

CUf = 0

PUf = 0

CU + PU / (CUc + PUc) - (CUf + PUf)

1 + 1 / (1 + 1) - (0 + 0) = 1 * 100% = 100% coverage


# 3 A detailed description of the testing strategy for the new unit test

White-box testing: Control Flow and Data Flow Coverage

For the first part of the test design component, we measure the adequacy of our current test suite using one or more code coverage tools.

## Measuring Control Flow Coverage

To measure control flow coverage of our current test suite, we have decided to use EclEmma.

The first step is to select classes for instrumentation. We have selected **org.jfree.data.DataUtilities** and **org.jfree.data.Range**, since these are the classes we wrote unit tests for in the previous assignment. For the second step, we ran the EclEmma code coverage analysis tool on these classes.

For our current test suite we will report three coverage metrics. The three metrics for each class are listed below. Since the coverage tool that we are using (EclEmma) does not report condition coverage, we will be using method coverage as a metric instead.

DataUtilities
- **Statement/Instruction** Coverage = 175 / 329 * 100% = **53.2%**
- **Branch** Coverage = 22 / 48 * 100% = **45.8%**
- **Method** Coverage = 5 / 10 * 100% = **50.0%**

Range
- **Statement/Instruction** Coverage = 87 / 465 * 100% = **18.7%**
- **Branch** Coverage = 4 / 72 * 100% = **5.6%**
- **Method** Coverage = 7 / 23 * 100% = **30.4%**

## Measuring Data Flow Coverage Manually



## Test Suite Development

We will design new unit tests for the **DataUtilities** and **Range** classes so we can increase their code coverage. The new adequacy criteria are listed below.

Minimum coverage:
- 90% statement coverage
- 70% branch coverage
- 60% method coverage

To begin, we will document a testing plan.

Our group will split the two classes among two subgroups, two individuals will on work on increasing the code coverage of one class. We plan on helping each other where necessary.

The adequacy criteria includes statement, branch, and method coverage. When analyzing the code coverage, we can look at multiple control flow criteria, including statement/instruction/line, decision/branch, condition, and even path coverage.

From our studies we know that Modified Condition-Decision Criterion (MC/DC) subsumes all other testing criterion, including branch and statement coverage. And branch coverage subsumes statement coverage. Therefore if we are able to achieve 100% MC/DC coverage we can assume we have achieved 100% coverage for all other control flow metrics.

To reach the adequacy criteria, we plan to develop tests which focus on increasing the MC/DC coverage and branch coverage, this will ensure we reach the needed coverages for statement, and method. Because we do not have access to a direct condition coverage metric since we are using EclEmma, we will focus on increasing branch coverage, and instinctively develop tests to increase MC/DC coverage.


# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Text...

# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

Here is our coverages of test suite which was submitted in lab 2. 

Instruction Coverage: 

![InstructionCoverage](media/beforeInstruction.png)


Branch Coverage:

![BranchCoverage](media/beforeBranch.png)

Method Coverage:

![MethodCoverage](media/beforeMethod.png)

This is our coverages with the updated test suite (Note: Initial test suite from lab 2 is included, as we added more test cases to increase coverages): 

Instruction Coverage: 

![InstructionCoverage](media/afterInstruction.png)


Branch Coverage:

![BranchCoverage](media/afterBranch.png)

Method Coverage:

![MethodCoverage](media/afterMethod.png)

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
