package atlassian.mapdecoder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyMapDecoder implements MapDecoder {
	@Override
	public Map<String, String> decode(String args) {
		if (args == null)
			return null;
		if (args.isEmpty())
			return Collections.emptyMap();

		Map<String, String> result = new HashMap<String, String>();

		final String[] pairs = args.split("&");

		for (int i = 0; i < pairs.length; i++) {

			String[] pair = pairs[i].split("=");

			if (pair.length == 2) {

				result.put(pair[0], pair[1]);

			} else if (pair.length == 1) {
				final String s = pairs[i];
				if (s.charAt(0) == '=') {
					result.put("", s.substring(1));
				} else if (s.charAt(s.length() - 1) == '=') {
					result.put(s.substring(0, s.length() - 1), "");
				} else
					throw new IllegalArgumentException("Corrupted =");
			} else
				throw new IllegalArgumentException("Corrupted &");

		}

		return result;
	}
}
