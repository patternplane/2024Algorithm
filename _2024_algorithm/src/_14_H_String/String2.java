//결과 제출시 : 삭제
package _14_H_String;

//결과 제출시 : 학번 이름 기재하기

//결과 제출시 : 클래스 명은 HW2
public class String2 {
	public static void main(String[] args) {
		char[] ipStr;

		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		try {
			ipStr = br.readLine().toCharArray();
		} catch (Exception e) {
			System.out.println("Input Error");
			return;
		}

		for (String s : solutionByLoop(ipStr))
			System.out.println(s);

		String[] test = solutionByRecursive(ipStr);
	}

	static int charToIPUnit(char[] num, int s, int e) {
		int value = 0;
		while (s <= e) {
			value *= 10;
			if (num[s] < '0' || num[s] > '9')
				return -1;
			value += num[s] - '0';
			s++;
		}
		return value;
	}

	static boolean isValidIP(int ip) {
		return (ip >= 0 && ip <= 255);
	}

	static String[] solutionByLoop(char[] ipNumbers) {
		int dotSelection = 0;
		final int maxDotSelection = 3;
		if (ipNumbers.length <= maxDotSelection)
			return new String[0];

		TST answer = new TST();
		java.util.Stack<Integer> dotIndexes = new java.util.Stack<Integer>();
		java.util.Stack<Integer> ipUnits = new java.util.Stack<Integer>();
		dotIndexes.add(-1);

		int ipUnit;
		while (!dotIndexes.isEmpty()) {
			int newUnitEndIdx = (dotSelection == maxDotSelection ? ipNumbers.length - 1 : dotIndexes.peek() + 1);
			ipUnit = charToIPUnit(ipNumbers, dotIndexes.peek() + 1, newUnitEndIdx);

			if (isValidIP(ipUnit)) {
				ipUnits.push(ipUnit);
				dotIndexes.push(newUnitEndIdx);
				dotSelection++;
			}

			if (!isValidIP(ipUnit) || dotSelection > maxDotSelection) {
				if (dotSelection > maxDotSelection) {
					StringBuilder newIp = new StringBuilder();
					for (int i = 0; i < ipUnits.size(); i++) {
						newIp.append(ipUnits.get(i));
						if (i != ipUnits.size() - 1)
							newIp.append('.');
					}
					answer.add(newIp.toString());
				}

				while (true) {
					if (dotIndexes.peek() == -1) {
						dotIndexes.pop();
						break;
					} else {
						int currentIdx = dotIndexes.pop();
						ipUnits.pop();
						dotSelection--;

						if (currentIdx + 1 <= ipNumbers.length - 1 - (maxDotSelection - dotSelection)) {
							ipUnit = charToIPUnit(ipNumbers, dotIndexes.peek() + 1, currentIdx + 1);
							if (isValidIP(ipUnit)) {
								ipUnits.push(ipUnit);
								dotIndexes.push(currentIdx + 1);
								dotSelection++;
								break;
							}
						}
					}
				}
			}
		}

		return answer.toArray();
	}

	static java.util.Stack<Integer> r_IPUnits;
	static TST r_IPs;
	static char[] r_IPNumStr;

	static String[] solutionByRecursive(char[] ipNumbers) {
		r_IPUnits = new java.util.Stack<Integer>();
		r_IPs = new TST();
		r_IPNumStr = ipNumbers;

		getIP(0, 4);

		r_IPUnits = null;
		String[] answer = r_IPs.toArray();
		r_IPs = null;
		r_IPNumStr = null;

		return answer;
	}

	static void getIP(int startIdx, int remainUnits) {
		if (remainUnits <= 1) {
			int lastIpUnit = charToIPUnit(r_IPNumStr, startIdx, r_IPNumStr.length - 1);
			if (isValidIP(lastIpUnit)) {
				r_IPUnits.add(lastIpUnit);

				StringBuilder newIp = new StringBuilder();
				for (int i = 0; i < r_IPUnits.size(); i++) {
					newIp.append(r_IPUnits.get(i));
					if (i != r_IPUnits.size() - 1)
						newIp.append('.');
				}
				r_IPs.add(newIp.toString());

				r_IPUnits.pop();
			}
		} else {
			remainUnits--;
			for (int unitEnd = startIdx; unitEnd <= r_IPNumStr.length - 1 - remainUnits; unitEnd++) {
				int newIpUnit = charToIPUnit(r_IPNumStr, startIdx, unitEnd);
				if (isValidIP(newIpUnit)) {
					r_IPUnits.add(newIpUnit);
					getIP(unitEnd + 1, remainUnits);
					r_IPUnits.pop();
				}
			}
		}
	}
}

class TST {
	private TSTNode root = null;
	private int n = 0;
	private int maxLen = 0;

	public void add(String value) {
		if (root == null && value.length() > 0)
			root = new TSTNode(value.charAt(0));
		TSTNode node = root;
		for (int i = 0, size = value.length(); i < size;) {
			if (node.c == value.charAt(i)) {
				if (i == size - 1) {
					if (!node.isFinal)
						n++;
					node.isFinal = true;
					if (maxLen < size)
						maxLen = size;
				} else {
					if (node.same == null)
						node.same = new TSTNode(value.charAt(i + 1));
					node = node.same;
				}
				i++;
			} else if (node.c < value.charAt(i)) {
				if (node.next == null)
					node.next = new TSTNode(value.charAt(i));
				node = node.next;
			} else {
				if (node.previous == null)
					node.previous = new TSTNode(value.charAt(i));
				node = node.previous;
			}
		}
	}

	private String[] r_result = null;
	private char[] r_CharA = null;
	private int r_charIdx = 0;
	private int r_idx = 0;

	public String[] toArray() {
		String[] result = new String[n];
		r_result = result;
		r_CharA = new char[maxLen];
		r_charIdx = 0;
		r_idx = 0;

		getStringRecursive(root);

		r_result = null;
		r_CharA = null;
		r_charIdx = 0;
		r_idx = 0;
		return result;
	}

	private void getStringRecursive(TSTNode root) {
		if (root == null)
			return;

		if (root.previous != null)
			getStringRecursive(root.previous);
		r_CharA[r_charIdx++] = root.c;
		if (root.isFinal == true)
			r_result[r_idx++] = new String(r_CharA, 0, r_charIdx);
		if (root.same != null)
			getStringRecursive(root.same);
		r_charIdx--;
		if (root.next != null)
			getStringRecursive(root.next);
	}
}

class TSTNode {
	public char c;
	public boolean isFinal;
	public TSTNode previous;
	public TSTNode same;
	public TSTNode next;

	public TSTNode(char c) {
		this.c = c;
		this.isFinal = false;
		this.previous = null;
		this.same = null;
		this.next = null;
	}
}
