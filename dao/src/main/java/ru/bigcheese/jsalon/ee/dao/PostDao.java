package ru.bigcheese.jsalon.ee.dao;

import ru.bigcheese.jsalon.core.model.Post;

/**
 * Created by BigCheese on 10.03.15.
 */
public interface PostDao extends BaseDao<Post, Long> {
    Post getPostByName(String name);
    boolean existsByName(String name);
}
