package _14_H_String;

public class String2_n {
	public static void main(String[] args) {
		char[] ipStr;
		int strLen;

		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		try {
			ipStr = br.readLine().toCharArray();
			strLen = ipStr.length;
		} catch (Exception e) {
			System.out.println("Input Error");
			return;
		}

		int selected = 0;
		final int maxSelection = 3;
		final int maxUnitLen = 3;

		java.util.TreeSet<String> answer = new java.util.TreeSet<String>();
		java.util.Stack<Integer> selection = new java.util.Stack<Integer>();
		java.util.Stack<Integer> ip = new java.util.Stack<Integer>();
		selection.add(-1);

		int ipValue;
		while (!selection.isEmpty()) {
			int minIdx = strLen - 1 - maxUnitLen * (maxSelection - selected);
			int selectValue = Math.max(selection.peek() + 1, minIdx);
			ipValue = parse(ipStr, selection.peek() + 1, selectValue);

			if (isValidIPNum(ipValue)) {
				ip.push(ipValue);
				selection.push(selectValue);
				selected++;
			}
			
			if (!isValidIPNum(ipValue) || selected > maxSelection) {
				if (selected > maxSelection) {
					StringBuilder newIp = new StringBuilder();
					for (int i = 0; i < ip.size(); i++) {
						newIp.append(ip.get(i));
						if (i != ip.size() - 1)
							newIp.append('.');
					}
					answer.add(newIp.toString());
				}

				while (true) {
					if (selection.peek() == -1) {
						selection.pop();
						break;
					} else {
						int currentIdx = selection.pop();
						ip.pop();
						selected--;

						if (currentIdx + 1 <= strLen - 1 - (maxSelection - selected)
								&& currentIdx + 1 - selection.peek() <= maxUnitLen ) {
							ipValue = parse(ipStr, selection.peek() + 1, currentIdx + 1);
							if (isValidIPNum(ipValue)) {
								ip.push(ipValue);
								selection.push(currentIdx + 1);
								selected++;
								break;
							}
						}
					}
				}
			}
		}

		for (String s : answer)
			System.out.println(s);
	}

	static int parse(char[] num, int s, int e) {
		int value = 0;
		while (s <= e) {
			value *= 10;
			value += num[s] - '0';
			s++;
		}
		return value;
	}

	static boolean isValidIPNum(int ip) {
		return (ip >= 0 && ip <= 255);
	}
}
