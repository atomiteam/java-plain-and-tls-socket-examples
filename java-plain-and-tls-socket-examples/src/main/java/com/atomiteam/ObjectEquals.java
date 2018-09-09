package com.atomiteam;

import java.util.ArrayList;
import java.util.Collection;
//java -cp 'lib/*' com.at.Main  /repos/ykv/org.jaces.side.jcr.File yucelkulturvakfi.org org.jaces.side.jcr.File
//java -cp 'lib/*' com.at.Main  repo/org.jaces.side.jcr.File bursverenler.org org.jaces.side.jcr.File
class Count {
	public int i = 0;
}

public class ObjectEquals {

	public static void main(String[] args) {
		
		final Collection<? extends Number> foo = new ArrayList<Number>();
		foo.add(null);
		System.out.println(foo);
		
		Object o1 = new Object();
		Object o2 = new Object();
		
		System.out.println(o1 == o2);
		System.out.println(o1.equals(o2));
		o1 = o2;
		System.out.println(o1 == o2);
		System.out.println(o1.equals(o2));
				
		String a = "1";
		String b;
		switch(a) {
		case "aa":
				b="22";
				throw new IllegalArgumentException("dsdsd");
		}
		
		
	}

}
