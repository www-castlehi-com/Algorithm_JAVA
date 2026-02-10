package kakao;

import java.util.*;

class Solution {
	public int solution(String message, int[][] spoiler_ranges) {
		List<String> nonSpoilerWords = new ArrayList<>();
		for (int i = 0; i < message.length(); i++) {
			int j = i + 1;
			while (j < message.length() && message.charAt(j) != ' ') {
				j++;
			}

			boolean isSpoiler = false;
			for (int[] spoiler_range : spoiler_ranges) {
				if (spoiler_range[0] >= j) {
					break;
				}

				if (isInclude(i, j - 1, spoiler_range[0]) || isInclude(i, j - 1, spoiler_range[1]) || isInclude(spoiler_range[0], spoiler_range[1], i)
						|| isInclude(spoiler_range[0], spoiler_range[1], j - 1)) {
					isSpoiler = true;
					break;
				}
			}

			if (!isSpoiler) {
				nonSpoilerWords.add(message.substring(i, j));
			}

			i = j;
		}

		Set<String> spoilerWords = new HashSet<>();
		for (int[] spoiler_range : spoiler_ranges) {
			int start = spoiler_range[0];
			int end = spoiler_range[1];

			String[] words = getWords(message, start, end);
			for (String word : words) {
				if (!word.isBlank() && !nonSpoilerWords.contains(word))
					spoilerWords.add(word);
			}
		}

		return spoilerWords.size();
	}

	private boolean isInclude(int start, int end, int target) {
		return target >= start && target <= end;
	}

	private String[] getWords(String message, int start, int end) {
		while (start > 0) {
			if (message.charAt(start) == ' ') {
				break;
			}
			start--;
		}

		while (end < message.length()) {
			if (message.charAt(end) == ' ') {
				break;
			}
			end++;
		}

		return message.substring(start, end).split(" ");
	}
}
