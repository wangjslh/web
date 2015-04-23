package com.test;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

public class Tests {

	@Test
	public void testUuid() {
		System.out.println(UUID.randomUUID());
	}

	@Test
	public void testParseValueOf() {
		Integer a = Integer.valueOf(127);
		Integer b = Integer.valueOf(127);

		Integer aa = Integer.valueOf(128);
		Integer bb = Integer.valueOf(128);

		Integer c = 127;
		Integer d = 127;

		Integer cc = 128;
		Integer dd = 128;

		Integer e = new Integer(234);
		Integer f = new Integer(234);

		System.out.println("(a==b) ? " + (a == b));
		System.out.println("(c==d) ? " + (c == d));
		System.out.println("(aa==bb) ? " + (aa == bb));
		System.out.println("(cc==dd) ? " + (cc == dd));
		System.out.println("(e==f) ? " + (e == f));
		/*(a==b) ? true
		(c==d) ? true
		(aa==bb) ? false
		(cc==dd) ? false
		(e==f) ? false*/
		//原因如下
		/*
		public static Integer valueOf(int i) {
			final int offset = 128;
			if (i >= -128 && i <= 127) {
				return IntegerCache.cache[i + offset];
			}
			return new Integer(i);
		}
		*/
	}

}
