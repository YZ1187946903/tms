import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author curry by 2022/6/15 12
 */
public class CurryTest {
	@Test
	public void mainTest() {
		// String类型两种声明方式在创建空间时是两种方式
// String name = "Hello";// 方式一
//name = "World";// Hello没变只是name重新创建了一个空间存World然后name指向了World。Hello准备垃圾回收
		// 如果常量池中没有Hello则在常量池中创建Hello然后name指向常量池中的Hello，
		// 如果常量池中有Hello直接将name指向常量池中的Hello，
		String name = new String("Hello"); // 方式二
		// 去常量池找没有则在里面创建，然后在堆中也创一个，然后name指向堆空间中Hello。
		// 如果有，则不再常量池中创建，然后依然在堆中创一个，然后name指向堆空间中的Hello
		// new出来的永远指向堆中的空间
		List<Integer> list = new ArrayList<>();
		list.add(1);
		change(list);
		Integer sum = 500;
		add(sum);
		Curry curry = new Curry("ysl",18);
		changeCurry(curry);

		System.out.println(sum);
		for (Integer integer : list) {
			System.out.println(integer);
		}
		String name1 = "Hello"; // name1中是常量池中Hello的地址，name中是堆空间中Hello的地址值
		System.out.println(name1 == name); // false
		System.out.println("*************");
		String n = "1";
		n = "2"; // 1存在于常量池中不能改变，重新开辟一个空间存2，让n指向2，而不是把1改为了2;
		System.out.println(n);
		System.out.println("*************");
	}

	@Test
	public void getDateByTimeZone() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
		format.format(date);
	}
	private void changeCurry(Curry curry) {
		curry.setAge(10);
	}

	private void add(Integer sum) {
		sum = 600;
	}

	public void change(List<Integer> list) {
		list = Arrays.asList(1,2,3);
		// name地址值赋值给s
		// s在常量池中也指向Hello，但是Hello改不了，就只能重新创建一个变量值为：World。然后s指向World
	}
}
class Curry {
	String name;
	Integer age;

	public Curry(String name, Integer age) {
		this.name = name;
		this.age = age;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
