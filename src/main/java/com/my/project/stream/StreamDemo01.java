package com.my.project.stream;

import java.util.Arrays;
import java.util.List;

/**
 * 题目要求：一分钟内完成此题，只能用一行代码实现
 * 现在有5个用户，筛选：
 * 1. ID 必须是偶数
 * 2. 年龄必须大于23岁
 * 3. 用户名转为大写字母
 * 4. 用户名字母倒着排序
 * 5. 只能输出一个用户
 */
public class StreamDemo01 {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(5, "e", 26);
        // 集合就是存储

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        list.stream()
                .filter((u) -> u.getAge() % 2 == 0 && u.getAge() > 23)
                .map((u) -> {
                    u.setName(u.getName().toUpperCase());
                    return u;
                })
                .sorted((user1, user2) -> user2.getName().compareTo(user1.getName()))
                .limit(1)
                .forEach(System.out::println);

    }
}
