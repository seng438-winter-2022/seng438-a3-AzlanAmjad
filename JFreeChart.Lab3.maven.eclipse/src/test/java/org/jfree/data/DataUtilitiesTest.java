package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataUtilitiesTest {

	private KeyedValues keyValue;
	private Values2D data;
	private Values2D values2D;

	@BeforeEach
	public void setUp() throws Exception {
		// setup mock objects
		keyValue = mock(KeyedValues.class);
		data = mock(Values2D.class);
		values2D = mock(Values2D.class);
	}

	@AfterEach
	public void tearDown() throws Exception {
		// tear down the mock after each
		keyValue = null;
		values2D = null;
	}

	// HELPER METHOD
	public void setupValues2D() {

		// Setup the Values2D Table
		// ________
		// |7|5|8 |
		// |5|8|13|
		// ‾‾‾‾‾‾‾‾
		when(data.getRowCount()).thenReturn(2);

		when(data.getValue(0, 0)).thenReturn(7);
		when(data.getValue(1, 0)).thenReturn(5);

		when(data.getValue(0, 1)).thenReturn(5);
		when(data.getValue(1, 1)).thenReturn(8);

		when(data.getValue(0, 2)).thenReturn(8);
		when(data.getValue(1, 2)).thenReturn(13);
	}

	// TEST getCumulativePercentages()
	@Test
	public void calculatingPercentageWithZero() {
		/*
		 * Testing for invalid parameter, as the input is 0 The function should return
		 * null as division by 0 to calculate percentage is not possible The assumed
		 * formula for Cumulative Percentage in this case is keyValue[0] (which is 0) /
		 * total (which is 0)
		 */
		when(keyValue.getItemCount()).thenReturn(1);
		when(keyValue.getValue(0)).thenReturn(0);
		when(keyValue.getKey(0)).thenReturn(0);
		KeyedValues temp = DataUtilities.getCumulativePercentages(keyValue);

		assertEquals(null, temp.getValue(0), "should return null due to division by 0");
	}

	// TEST equal()
	@Test
	public void infAndNanEdgeCase() {
		/*
		 * Boundary testing the edge cases of Not a Number and Infinity Expected is
		 * false as Not a Number and Infinity are not Equal
		 */
		double[][] a = new double[1][1];
		a[0][0] = 0.0 / 0.0;
		double[][] b = new double[1][1];
		b[0][0] = Double.POSITIVE_INFINITY;

		assertEquals(false, DataUtilities.equal(a, b), "NaN and INF are not equal");
	}

	@Test
	public void infAndInfEdgeCase() {

		/*
		 * Boundary testing the edge cases of Infinity and Infinity Expected is true as
		 * Infinity and Infinity are equal
		 */

		double[][] a = new double[1][1];
		a[0][0] = Double.POSITIVE_INFINITY;
		double[][] b = new double[1][1];
		b[0][0] = Double.POSITIVE_INFINITY;

		assertEquals(true, DataUtilities.equal(a, b), "INF and INF are equal");
	}

	@Test
	public void nanAndNaNEdgeCase() {

		/*
		 * Boundary testing the edge cases of NaN and NaN Expected is true as NaN and
		 * NaN are equal
		 */

		double[][] a = new double[1][1];
		a[0][0] = 0.0 / 0.0;
		double[][] b = new double[1][1];
		b[0][0] = 0.0 / 0.0;

		assertEquals(true, DataUtilities.equal(a, b), "NaN and NaN are equal");
	}

	// TEST calculateColumnTotal();
	@Test
	public void testCalculateFirstColumnTotal() {
		// Testing CalculateColumnTotal(Values2D data, int column)

		setupValues2D();
		// Sum up the values in the first column (0th column index <= boundary), should
		// be 12
		double result = DataUtilities.calculateColumnTotal(data, 0);

		assertEquals(12, result, "The summed values in the column should be 12");
	}

	// TEST createNumberArray()
	// public static Number[] createNumberArray(double[] data) Equivalence Class
	@Test
	void testCreateNumberArrayWithNotNullData() {
		double[] data = { 20.0, 14.0, 2.4, 58.8, 48.2, 5356.4, 453334 };
		Number[] expected = { 20.0, 14.0, 2.4, 58.8, 48.2, 5356.4, 453334.0 };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertArrayEquals(expected, actual, "creating Number array from not null double array");
	}

	// public static Number[] createNumberArray(double[] data) Equivalence Class
	@Test
	void testCreateNumberArrayWithNullData() {
		double[] data = null;
		assertThrows(Exception.class, () -> {
			DataUtilities.createNumberArray(data);
		}, "creating Number array when null data is passed");
	}

	// TEST calculateRowTotal()
	@Test
	public void calculateRowTotalWithPositiveColumnValues() {
		// tests with positive column values in row 0
		// Equivalence test

		// setup
		when(values2D.getColumnCount()).thenReturn(5);
		when(values2D.getValue(0, 0)).thenReturn(10);
		when(values2D.getValue(0, 1)).thenReturn(20);
		when(values2D.getValue(0, 2)).thenReturn(30);
		when(values2D.getValue(0, 3)).thenReturn(40);
		when(values2D.getValue(0, 4)).thenReturn(50);

		assertEquals(150, DataUtilities.calculateRowTotal(values2D, 0), "Total of row 0 should be 150");
	}

	@Test
	public void calculateRowTotalWithNegativeColumnValues() {
		// tests with negative columns values in row 0
		// Equivalence test

		// setup
		when(values2D.getColumnCount()).thenReturn(5);
		when(values2D.getValue(0, 0)).thenReturn(-10);
		when(values2D.getValue(0, 1)).thenReturn(-20);
		when(values2D.getValue(0, 2)).thenReturn(-30);
		when(values2D.getValue(0, 3)).thenReturn(-40);
		when(values2D.getValue(0, 4)).thenReturn(-50);

		assertEquals(-150, DataUtilities.calculateRowTotal(values2D, 0), "Total of row 0 should be -150");

	}

	@Test
	public void calculateRowTotalWithNullColumnValues() {
		// tests with column values that contain null in row 0
		// Boundary test

		// setup
		when(values2D.getColumnCount()).thenReturn(5);
		when(values2D.getValue(0, 0)).thenReturn(10);
		when(values2D.getValue(0, 1)).thenReturn(null);
		when(values2D.getValue(0, 2)).thenReturn(null);
		when(values2D.getValue(0, 3)).thenReturn(40);
		when(values2D.getValue(0, 4)).thenReturn(50);

		assertEquals(100, DataUtilities.calculateRowTotal(values2D, 0), "Total of row 0 should be 100");

	}

	@Test
	public void calculateRowTotalIfNoColumnsExist() {
		// tests when 0 columns exist in row 0
		// Boundary test

		// setup
		when(values2D.getColumnCount()).thenReturn(0);

		assertEquals(0, DataUtilities.calculateRowTotal(values2D, 0),
				"When 0 columns exist, 0 should be the total of a row");
	}

}
