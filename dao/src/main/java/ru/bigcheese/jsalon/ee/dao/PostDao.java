package ru.bigcheese.jsalon.ee.dao;

import ru.bigcheese.jsalon.core.model.Post;

import java.util.List;

/**
 * Created by BigCheese on 10.03.15.
 */
public interface PostDao extends BaseDao<Post, Long> {
    List<Post> getPostsByName(String name);
}
