package atlassian.stringtomap;

import java.util.HashMap;
import java.util.Map;

public class MyStringToMapTransformer implements StringToMapTransformer {
	@Override
	public Map<String, Integer> parseNumbers(final String csvString) {

		final Map<String, Integer> result = new HashMap<String, Integer>();

		final String[] pairs = csvString.split(",");

		String tmp = "";

		for (int i = 0; i < pairs.length; i++) {

			tmp += pairs[i];

			String[] pair = tmp.split("->");

			if (pair.length == 2) {

				String name = pair[0];
				Integer age = Integer.valueOf(pair[1]);

				result.put(name, age);

				tmp = "";
			}
		}

		return result;
	}
}
