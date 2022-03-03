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

	// TEST Range() constructor
	// Increasing branch and MC/DC control flow coverage
	@Test
	void RangeLowerBoundGreaterThanUpperBound() {
		assertThrows(IllegalArgumentException.class, () -> {
			exampleRange = new Range(100, 0);
		}, "creating Range with lower > upper");
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
	void calculatingDoubleMaxMinusOneDoubleMinMinusOne() {
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

	// Increasing branch and MC/DC control flow coverage
	@Test
	public void expandLowerBoundGreaterThanUpperBound() {
		exampleRange = Range.expand(exampleRange, -2, -3);
		assertEquals(-400, exampleRange.getLowerBound(), "Expanding upper bound by a margin of -300%, now should be lower bound -400");
		assertEquals(0, exampleRange.getUpperBound(), "Expanding lower bound by a margin of -200%, now should be upper bound 0");
	}
	
	// TEST combine()
	@Test
	public void combineRangeMinBoundary() {
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
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void combineNullRange1() {
		// Testing combine (Range range1, Range range2)
		Range nullRange = null; // a null range
		Range combined = Range.combine(nullRange, exampleRange);
		
		// expected combined = exampleRange
		assertSame(exampleRange, combined, "Expect exampleRange to be returned from combine");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void combineNullRange2() {
		// Testing combine (Range range1, Range range2)
		Range nullRange = null; // a null range
		Range combined = Range.combine(exampleRange, nullRange);
				
		// expected combined = exampleRange
		assertSame(exampleRange, combined, "Expect exampleRange to be returned from combine");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void combineWithOneNaNinBothRanges() {
		Range range1 = new Range(Double.NaN, 30);
		Range range2 = new Range(0, Double.NaN);
		Range expected = new Range(0, 30);
		Range combined = Range.combine(range1, range2);
		
		assertTrue(combined.equals(expected), "combining range (NaN, 30) with range (0, NaN)");
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
	
	// TEST combineIgnoringNaN()
	// Increasing branch and MC/DC control flow coverage
	@Test
	void combineIgnoringNaNRange1NullRange2NotNullAndNotisNaNRange() {
		// testing combineIgnoringNaN(Range range1, Range range2)
		// range1 = null
		// range2 != null
		// !range2.isNaNRange()
		Range nullRange = null;
		Range combinedRange = Range.combineIgnoringNaN(nullRange, exampleRange);
		assertSame(exampleRange, combinedRange, "Null range1 with not null range2 && not range2.isNaNRange()");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void combineIgnoringNaNRange1NullRange2NotNullAndisNaNRange() {
		// testing combineIgnoringNaN(Range range1, Range range2)
		// range1 = null
		// range2 != null
		// range2.isNaNRange();
		Range nullRange = null;
		Range nanRange = new Range(Double.NaN, Double.NaN);
		Range combinedRange = Range.combineIgnoringNaN(nullRange, nanRange);
		assertEquals(null, combinedRange, "Null range1 with not null range2 && range2.isNaNRange()");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void combineIgnoringNaNRange1NotNullAndNotisNaNRangeRange2Null() {
		// testing combineIgnoringNaN(Range range1, Range range2)
		// range1 != null
		// !range1.isNaNRange()
		// range2 = null
		Range nullRange = null;
		Range combinedRange = Range.combineIgnoringNaN(exampleRange, nullRange);
		assertSame(exampleRange, combinedRange, "Null range2 with not null range1 && not range1.isNaNRange()");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void combineIgnoringNaNRange1NotNullAndisNaNRangeRange2Null() {
		// testing combineIgnoringNaN(Range range1, Range range2)
		// range1 != null
		// range1.isNaNRange()
		// range2 = null
		Range nullRange = null;
		Range nanRange = new Range(Double.NaN, Double.NaN);
		Range combinedRange = Range.combineIgnoringNaN(nanRange, nullRange);
		assertEquals(null, combinedRange, "Not null range1 and range1.isNaNRange with null range2");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void combineIgnoringNaNRange1NotNullRange2NotNull() {
		// testing combineIgnoringNaN(Range range1, Range range2)
		// range1 != null
		// range2 != null
		Range range1 = new Range(3, 10);
		Range range2 = new Range(4, 40);
		Range combinedRange = Range.combineIgnoringNaN(range1, range2);
		assertEquals(3, combinedRange.getLowerBound(), "combining range with lower bounds 3, and 4");
		assertEquals(40, combinedRange.getUpperBound(), "combining range with upper bounds 10, and 40");
	}
	
	// TEST intersects(double b0, double b1)
	// Increasing branch and MC/DC control flow coverage
	@Test
	void intersectsTrueFromBottom() {
		// b0 <= this.lower
		// b1 > this.lower
		boolean val = exampleRange.intersects(-300, -150);
		assertEquals(true, val, "range (-300, -150) intersect with range (-200, -100)");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void intersectsFalseFromBottom() {
		// b0 <= this.lower
		// b1 <= this.lower
		boolean val = exampleRange.intersects(-400, -300);
		assertEquals(false, val, "range (-400, -300) intersect with range (-200, -100)");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void intersectTrueFromTop() {
		// b0 > this.lower
		// b0 < this.upper
		// b1 >= b0
		boolean val = exampleRange.intersects(-150, 0);
		assertEquals(true, val, "range (-150, 0) intersect with range (-200, -100)");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void intersectFalseFromTop() {
		// b0 > this.lower
		// b0 >= this.upper
		// b1 >= b0
		boolean val = exampleRange.intersects(-50, 0);
		assertEquals(false, val, "range (-50, 0) intersect with range (-200, -100)");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void intersectFalseFromTopb0GreaterThanb1() {
		// b0 > this.lower
		// b0 < this.upper
		// b0 > b1
		assertThrows(IllegalArgumentException.class, () -> {
			exampleRange.intersects(-150, -200);
		}, "range (-150, -200) -> (min, max) intersect with (-200, -100)");
	}
	
	// TEST constrain(double value)
	// Increasing branch and MC/DC control flow coverage
	@Test
	void constrainValueInRange() {
		double val = exampleRange.constrain(-150);
		assertEquals(-150, val, "constrain value -150 in range (-200, -100)");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void constrainValueAboveRange() {
		double val = exampleRange.constrain(0);
		assertEquals(-100, val, "constrain value 0 above range (-200, -100)");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void constrainValueBelowRange() {
		double val = exampleRange.constrain(-300);
		assertEquals(-200, val, "constrain value -300 below range (-200, -100)");
	}
	
	// TEST equals(Object obj)
	// Increasing branch and MC/DC control flow coverage
	@Test
	void equalsNotRangeObject() {
		Double notRangeObject = new Double(43.2);
		boolean val = exampleRange.equals(notRangeObject);
		assertEquals(false, val, "Double object equals Range");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void equalsLowerBoundNotEqual() {
		Range newRange = new Range(-300, -100);
		boolean val = exampleRange.equals(newRange);
		assertEquals(false, val, "Range (-300, -100) equal Range (-200, -100)");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void equalsUpperBoundNotEqual() {
		Range newRange = new Range(-200, 0);
		boolean val = exampleRange.equals(newRange);
		assertEquals(false, val, "Range (-200, 0) equal Range (-200, -100)");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void equalsRangeEqual() {
		Range newRange = new Range(-200, -100);
		boolean val = exampleRange.equals(newRange);
		assertEquals(true, val, "Range (-200, -100) equal Range (-200, -100)");
	}
	
	// TEST expandToInclude(Range range, double value)
	// Increasing branch and MC/DC control flow coverage
	@Test
	void expandToIncludeNullRange() {
		Range nullRange = null;
		Range newRange = Range.expandToInclude(nullRange, 20);
		assertEquals(20, newRange.getLowerBound(), "lower bound of range = null range include 20");
		assertEquals(20, newRange.getUpperBound(), "upper bound of range = null range include 20");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void expandToIncludeValueBelowRange() {
		Range newRange = Range.expandToInclude(exampleRange, -300);
		assertEquals(-300, newRange.getLowerBound(), "lower bound of range (-200, -100) include -300");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void expandToIncludeValueAboveRange() {
		Range newRange = Range.expandToInclude(exampleRange, 0);
		assertEquals(0, newRange.getUpperBound(), "upper bound of range (-200, -100) include 0");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void expandToIncludeValueInRange() {
		Range newRange = Range.expandToInclude(exampleRange, -150);
		assertSame(exampleRange, newRange, "expand to include -150 in range (-200, -100)");
	}
	
	// TEST shift(Range base, double delta, boolean allowZeroCrossing)
	// Increasing branch and MC/DC control flow coverage
	@Test
	void shiftBaseNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.shift(null, 0, true);
		}, "shifting with null base");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void shiftAllowZeroCrossing() {
		Range expected = new Range(100, 200);
		Range actual = Range.shift(exampleRange, 300, true);
		assertTrue(actual.equals(expected), "shift range (-200, -100) by delta of 300");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void shiftDontAllowZeroCrossing() {
		Range expected = new Range(-50, 0);
		Range actual = Range.shift(exampleRange, 150, false);
		assertTrue(actual.equals(expected), "shift range (-200, -100) by delta of 150 with no zero crossing");
	}
	
	// TEST scale(Range base, double factor)
	// Increasing branch and MC/DC control flow coverage
	@Test
	void scaleBaseNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.scale(null, 200);
		}, "scaling null base");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void scaleNegativeFactor() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.scale(exampleRange, -200);
		}, "scaling factor negative");
	}
	
	// Increasing branch and MC/DC control flow coverage
	@Test
	void scaleBase() {
		Range expected = new Range(-400, -200);
		Range actual = Range.scale(exampleRange, 2);
		assertTrue(actual.equals(expected), "scaling range (-200, -100) by a factor of 2");
	}
	
	// TEST toString()
	// Increasing branch and MC/DC control flow coverage
	@Test
	void toStringRange() {
		String expected = "Range[-200.0,-100.0]";
		String actual = exampleRange.toString();
		assertTrue(actual.equals(expected), "to string range (-200, -100)");
	}
	
	// TEST shift(Range base, double delta)
	// Increasing branch and MC/DC control flow coverage
	@Test
	void shiftBase() {
		Range expected = new Range(0.0, 100.0);
		Range actual = Range.shift(exampleRange, 200.0);
		assertTrue(actual.equals(expected), "shift range (-200, -100) by delta 200");
	}
	
	// TEST intersects(Range range)
	@Test
	void intersectsRange() {
		Range newRange = new Range(-150, 300);
		boolean val = exampleRange.intersects(newRange);
		assertTrue(val, "range (-150, 300) intersects with range (-200, -100)");
	}
	
	// TEST hashcode()
	@Test
	void hashcodeInt() {
		assertNotNull(exampleRange.hashCode(), "getting hashcode from range (-200, -100)");
	}
}
