package ru.school21;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlgoTypes {
	A_STAR("A*");

	final String name;
}
