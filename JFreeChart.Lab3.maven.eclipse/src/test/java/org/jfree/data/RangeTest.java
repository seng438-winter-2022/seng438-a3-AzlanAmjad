package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class RangeTest {

	private Range exampleRange;

	@BeforeEach
	public void setUp() throws Exception {
		// setup a new Range object
		exampleRange = new Range(-200, -100);
	}

	@AfterEach
	public void tearDown() throws Exception {
		// tear down of Range object
		exampleRange = null;
	}

	// TEST getCentralValue()
	@Test
	void calculatingDoubleMaxtoDoubleMin() {
		/*
		 * Testing getCentralValue in Range Testing the boundary of max double and min
		 * double A and B Test passes
		 */
		Range exampleRange = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
		double center = Double.MAX_VALUE / 2 + Double.MIN_VALUE / 2;
		assertEquals(center, exampleRange.getCentralValue(),
				"The central value of Double.MAX_VALUE and Double.MIN_VALUE should result in large number");
	}

	@Test
	void calculatingDoubleMaxPlusOneDoubleMinPlusOne() {
		/*
		 * Testing getCentralValue in Range Testing the boundary of max double + 1 and
		 * min double + 1 A + 1 and B + 1 This is outside of boundary As a result, when
		 * comparing with center calculated using Double.MAX_VALUE + 10 and
		 * Double.MIN_VALUE + 10, it passes Even though we are inputting
		 * Double.MIN_VALUE + 1 and Double.MAX_VALUE + 1
		 */
		Range exampleRange = new Range(Double.MIN_VALUE + 1, Double.MAX_VALUE + 1);
		double center = (Double.MAX_VALUE + 10) / 2 + (Double.MIN_VALUE + 10) / 2;
		assertEquals(center, exampleRange.getCentralValue(),
				"The central value of Double.MAX_VALUE + 1 and Double.MIN_VALUE+1 should result in overflow");
	}

	@Test
	void calculatingDoubleMaxMinusOneDoubleMinMinussOne() {
		/*
		 * Testing getCentralValue in Range Testing the boundary of max double - 1 and
		 * min double - 1 A - 1 and B - 1 This is within boundary Test "fails" as
		 * compared to Double.MAX_VALUE and Double.MIN_VALUE results in pass
		 */
		Range exampleRange = new Range(Double.MIN_VALUE - 1, Double.MAX_VALUE - 1);
		double center = (Double.MAX_VALUE) / 2 + (Double.MIN_VALUE) / 2;
		assertEquals(center, exampleRange.getCentralValue(),
				"The central value of Double.MIN_VALUE-1 and Double.MAX_VALUE-1 should result in large number");
	}

	@Test
	void calculatingZeroAndHundred() {
		/*
		 * Testing getCentralValue in Range Testing common path of 0 and 100 Test passes
		 * as is it common path.
		 */
		Range exampleRange = new Range(0, 100);
		double center = (0) / 2 + (100) / 2;
		assertEquals(center, exampleRange.getCentralValue(), "The central value of 0 and 100 should be 50");
	}

	@Test
	void calculatingCentralValueOfTwoNegativeRangeValues() {
		// tests CentralValue by inputting -200 as lower bound, -100 as upper bound
		// the central value between the two integers is expected to be -150
		assertEquals(-150, exampleRange.getCentralValue(), "The central value of -100 and -200 should be -150");
	}

	// TEST expand()
	@Test
	void expandUpperBoundByPositivePercentage() {
		// tests the expand method by expanding the upper bound by 50%
		// Equivalence test
		exampleRange = Range.expand(exampleRange, 0, 0.5);
		assertEquals(-50, exampleRange.getUpperBound(),
				"Expanding the upper bound by 50% of the range length should result in a new upper bound of -50");
	}

	@Test
	void expandLowerBoundByPositivePercentage() {
		// tests the expand method by expanding the lower bound by 25%
		// Equivalence test
		exampleRange = Range.expand(exampleRange, 0.25, 0);
		assertEquals(-225, exampleRange.getLowerBound(),
				"Expanding the lower bound by 25% of the range length should result in a new lower bound of -225");
	}

	@Test
	void expandUpperBoundByNegativePercentage() {
		// tests the expand method by expanding the upper bound by -50%
		// Boundary test
		exampleRange = Range.expand(exampleRange, 0, -0.5);
		assertEquals(-150, exampleRange.getUpperBound(),
				"Expanding the upper bound by -50% of the range length should result in a new upper bound of -150");
	}

	@Test
	void expandLowerBoundByNegativePercentage() {
		// tests the expand method by expanding the lower bound by -25%
		// Boundary test
		exampleRange = Range.expand(exampleRange, -0.25, 0);
		assertEquals(-175, exampleRange.getLowerBound(),
				"Expanding the lower bound by -25% of the range length should result in a new lower bound of -175");
	}

	@Test
	void expandUpperBoundByPercentageGreateThan100() {
		// tests the expand method by expanding the upper bound by 150%
		// Boundary test
		exampleRange = Range.expand(exampleRange, 0, 1.5);
		assertEquals(50, exampleRange.getUpperBound(),
				"Expanding the upper bound by 150% of the range length should result in a new upper bound of 50");
	}

	@Test
	void expandLowerBoundByPercentageGreaterThan100() {
		// tests the expand method by expanding the lower bound by 125%
		// Boundary test
		exampleRange = Range.expand(exampleRange, 1.25, 0);
		assertEquals(-325, exampleRange.getLowerBound(),
				"Expanding the lower bound by 125% of the range length should result in a new lower bound of -325");
	}

	@Test
	void expandUpperBoundByPercentageOf100() {
		// tests the expand method by expanding the upper bound by 100%
		// Boundary test
		exampleRange = Range.expand(exampleRange, 0, 1);
		assertEquals(0, exampleRange.getUpperBound(),
				"Expanding the upper bound by 100% of the range length should result in a new upper bound of 0");
	}

	@Test
	void expandLowerBoundByPercentageOf100() {
		// tests the expand method by expanding the lower bound by 100%
		// Boundary test
		exampleRange = Range.expand(exampleRange, 1, 0);
		assertEquals(-300, exampleRange.getLowerBound(),
				"Expanding the lower bound by 100% of the range length should result in a new lower bound of -300");
	}

	// TEST combine()
	@Test
	public void testCombineRangeMinBoundary() {
		// Testing combine(Range r1, Range r2)
		exampleRange = new Range(-Double.MAX_VALUE, 100);
		Range exampleRange2 = new Range(5, 1000);
		// exampleRange [-Double.MAX_Value, 100]
		// exampleRange2 [5, 1000]
		exampleRange = Range.combine(exampleRange, exampleRange2);

		// Expected: exampleRange [-Double.MAX_Value, 1000]
		// Test both upper and lower bounds
		assertEquals(-Double.MAX_VALUE, exampleRange.getLowerBound(), .000000001d,
				"The lower bound should be -Double.MAX_Value");
		assertEquals(1000, exampleRange.getUpperBound(), .000000001d, "The upper bound should be 1000");

	}

	// TEST getLowerBound()
	// getLowerBound() Equivalence Class Test
	@Test
	void testGetRangeLowerBound() {
		Range r = new Range(1.5, 1000.0);
		double actual = r.getLowerBound();
		assertEquals(1.5, actual, "getting lower bound");
	}

	// TEST getUpperBound()
	// getUpperBound() Equivalence Class Test
	@Test
	void testGetRangeUpperBound() {
		Range r = new Range(1.5, 1000.0);
		double actual = r.getUpperBound();
		assertEquals(1000.0, actual, "getting upper bound");
	}
}
