package com.vimcoder.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/valid-sudoku/description/
 * 
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。 数字 1-9 在每一行只能出现一次。 数字 1-9
 * 在每一列只能出现一次。 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 
 * @author zhang
 *
 */
public class ValidSudoku {
	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			// 横向验证
			Set<Character> row = new HashSet<>();
			// 纵向验证
			Set<Character> col = new HashSet<>();
			// 小九宫格验证
			Set<Character> sm = new HashSet<>();

			char tmp;
			for (int j = 0; j < 9; j++) {
				if ((tmp = board[i][j]) != '.') {
					if (row.contains(tmp)) {
						return false;
					} else {
						row.add(tmp);
					}
				}
				if ((tmp = board[j][i]) != '.') {
					if (col.contains(tmp)) {
						return false;
					} else {
						col.add(tmp);
					}
				}

				// i标志位于哪个大的九宫格中（每次步进3），j标志位于当前九宫格中具体坐标
				int rowIndex = i / 3 * 3 + j / 3;
				int colIndex = i % 3 * 3 + j % 3;
				if ((tmp = board[rowIndex][colIndex]) != '.') {
					if (sm.contains(tmp)) {
						return false;
					} else {
						sm.add(tmp);
					}
				}

			}
		}

		return true;
	}
}
