package problem.misc;

/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1), and among
 * them, there may exist one celebrity. The definition of a celebrity is that
 * all the other n - 1 people know him/her, but he/she does not know any of
 * them.
 * 
 * Now you want to find out who the celebrity is or verify that there is not
 * one. The only thing you are allowed to do is to ask questions like: "Hi, A.
 * Do you know B?" to get information about whether A knows B. You need to find
 * out the celebrity (or verify there is not one) by asking as few questions as
 * possible (in the asymptotic sense).
 * 
 * You are given a helper function bool knows(a, b) which tells you whether A
 * knows B. Implement a function int findCelebrity(n). There will be exactly one
 * celebrity if he/she is in the party. Return the celebrity's label if there is
 * a celebrity in the party. If there is no celebrity, return -1.
 * 
 */
public class Solution227 {

	static class Relation {

		public boolean knows(int a, int b) {
			return true;
		}
	}

	static class Solver extends Relation {

		private int numberOfPeople;

		public int findCelebrity(int n) {
			numberOfPeople = n;
			int celebrityCandidate = 0;
			for (int i = 0; i < n; i++) {
				if (knows(celebrityCandidate, i)) {
					celebrityCandidate = i;
				}
			}
			if (isCelebrity(celebrityCandidate)) {
				return celebrityCandidate;
			}
			return -1;
		}

		private boolean isCelebrity(int i) {
			for (int j = 0; j < numberOfPeople; j++) {
				if (i == j)
					continue; // Don't ask if they know themselves.
				if (knows(i, j) || !knows(j, i)) {
					return false;
				}
			}
			return true;
		}

	}

}
