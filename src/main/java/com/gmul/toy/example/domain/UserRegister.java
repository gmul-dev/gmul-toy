package com.gmul.toy.example.domain;

import com.gmul.toy.example.api.EmailNotifier;
import com.gmul.toy.example.dao.UserRepository;
import com.gmul.toy.example.exception.DupIdException;
import com.gmul.toy.example.exception.WeakPasswordException;
import com.gmul.toy.example.validator.WeakPasswordChecker;

public class UserRegister {
    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;
    private EmailNotifier emailNotifier;

    public UserRegister(WeakPasswordChecker passwordChecker,
                        UserRepository userRepository,
                        EmailNotifier emailNotifier) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }


    public void register(String id, String pw, String email) {
//        throw new WeakPasswordException();
        if(passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
//        throw new DupIdException();
        User user = userRepository.findById(id);
        if(user != null) {
            throw new DupIdException();
        }
//        userRepository.save(new User("id", "pw", "email"));
        userRepository.save(new User(id, pw, email));

        emailNotifier.sendRegisterEmail(email);
    }
}
