package com.gmul.toy.dao;

import com.gmul.toy.domain.exampledb.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
