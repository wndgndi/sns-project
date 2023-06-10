package com.practice.sns.service;

import com.practice.sns.exception.ErrorCode;
import com.practice.sns.exception.SnsApplicationException;
import com.practice.sns.model.entity.PostEntity;
import com.practice.sns.model.entity.UserEntity;
import com.practice.sns.repository.PostEntityRepository;
import com.practice.sns.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostEntityRepository postEntityRepository;
    private final UserEntityRepository userEntityRepository;

    @Transactional
    public void create(String title, String body, String userName) {
        UserEntity userEntity = userEntityRepository.findByUserName(userName).orElseThrow(() -> new SnsApplicationException(
            ErrorCode.USER_NOT_FOUND, "%s not founded, userName"));

        postEntityRepository.save(PostEntity.of(title, body, userEntity));

    }
}
