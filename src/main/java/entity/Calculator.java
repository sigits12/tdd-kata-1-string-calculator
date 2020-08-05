package entity;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculator {
	
	public static int add(String text) {
		return toListOfIntegers(toListOfStrings(text))
				.stream()
	    		.mapToInt(Integer::intValue)
	    		.sum();
	}
	
	private static boolean usesCustomDelimiterSyntax(String text) {
		return text.startsWith("//");
	}
	
	private static List<String> splitUsingCustomDelimiterSyntax(String text) {
		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
		m.matches();
		String customDelimiter = m.group(1);
		String numbers = m.group(2);
		String[] arrayOfStringsNumbers = numbers.split(Pattern.quote(customDelimiter));
		return Arrays.asList(arrayOfStringsNumbers);
	}
	
	private static List<String> toListOfStrings(String text) {
		if (text.isEmpty()) {
			return Arrays.asList();
		} else if (usesCustomDelimiterSyntax(text)) {
			return splitUsingCustomDelimiterSyntax(text);
		} else {
			return Arrays.asList(text.split(",|\n"));			
		}
	}
	
	private static List<Integer> toListOfIntegers(List<String> listOfStrings) {
		List<Integer> numbers = listOfStrings.stream().map(Integer::parseInt).collect(Collectors.toList());
		
		ensureAllNonNegative(numbers);
		
		return numbers;
	}

	private static void ensureAllNonNegative(List<Integer> numbers) throws RuntimeException {
		List<Integer> negatives = numbers.stream().filter(s -> s < 0).collect(Collectors.toList());
		
		if (negatives.size() > 0) {
			throw new RuntimeException("Negatives not allowed: " + negatives.toString());
		}
	}
	
}
