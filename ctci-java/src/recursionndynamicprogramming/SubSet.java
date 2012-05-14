package recursionndynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSet {

	public static void main(String[] args) {
		List<ArrayList<Integer>> powerSet = new ArrayList<ArrayList<Integer>>();
		List<Integer> set = Arrays.asList(1, 2, 3, 6, 4, 8);
		powerSet = getSubSets(set, 0);

		for (ArrayList a : powerSet) {
			System.out.println(Arrays.toString(a.toArray()));
		}

	}

	private static ArrayList<ArrayList<Integer>> getSubSets(List<Integer> set,
			int i) {
		ArrayList<ArrayList<Integer>> allSubSets;
		if (i == set.size()) {
			allSubSets = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> emptySet = new ArrayList<Integer>();
			allSubSets.add(emptySet);
		} else {
			allSubSets = getSubSets(set, i + 1);
			ArrayList<ArrayList<Integer>>moreSubSets = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> s : allSubSets) {
				
				ArrayList<Integer> moreSets = new ArrayList<Integer>();
				moreSets.addAll(s);
				moreSets.add(set.get(i));
				moreSubSets.add(moreSets);
			}

			allSubSets.addAll(moreSubSets);

		}
		return allSubSets;
	}

	static ArrayList<ArrayList<Integer>> getSubsets(List<Integer> set, int index) {
		ArrayList<ArrayList<Integer>> allsubsets;
		if (set.size() == index) {
			allsubsets = new ArrayList<ArrayList<Integer>>();
			allsubsets.add(new ArrayList<Integer>()); // Empty set
		} else {
			allsubsets = getSubsets(set, index + 1);
			int item = set.get(index);
			ArrayList<ArrayList<Integer>> moresubsets = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> subset : allsubsets) {
				ArrayList<Integer> newsubset = new ArrayList<Integer>();
				newsubset.addAll(subset); //
				newsubset.add(item);
				moresubsets.add(newsubset);
			}
			allsubsets.addAll(moresubsets);
		}

		return allsubsets;
	}

}
