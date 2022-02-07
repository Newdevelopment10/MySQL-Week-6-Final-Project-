package math;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.params.provider.MethodSource;

import mathtastic.TestDemo;

public class TestDemoTest {

	private TestDemo testDemo;
	
	@BeforeEach
	void setUp() {
	  testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("math.TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);		
		}
		else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b))
			.isInstanceOf(IllegalArgumentException.class);		
	}
}
	
	static Stream<Arguments> argumentsForAddPositive() {
		// @formatter:off
		return Stream.of(
				arguments(2, 4, 6, false),
				arguments(3, 12, 15, false),
				arguments(-1, 7, 6, true),
				arguments(0, -11, -11, true)
			);
		// @formatter:on	
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		int a = 9;
		
		TestDemo mockDemo = spy(testDemo);
		doReturn(a).when(mockDemo).getRandomInt();
		
		int nineSquared = mockDemo.randomNumberSquared();
		assertThat(nineSquared).isEqualTo(81);
	}
	
}
