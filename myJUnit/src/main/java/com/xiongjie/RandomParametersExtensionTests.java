/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.xiongjie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @since 5.2
 * @see RandomParametersExtension
 */
@ExtendWith(RandomParametersExtension.class)
class RandomParametersExtensionTests {

	@Test
	void injectsInteger(@RandomParametersExtension.Random int i, @RandomParametersExtension.Random int j) {
		assertNotEquals(i, j);
	}

	@Test
	void injectsDouble(@RandomParametersExtension.Random double d) {
		assertEquals(0.0, d, 1.0);
	}

}
