package com.gmul.toy.example.validator;

// stub : 구현을 단순한 것으로 대체 , 테스트에 맞게 단순히 원하는 동작을 수행
public class StubWeakPasswordChecker implements WeakPasswordChecker {
    private boolean weak;

    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    @Override
    public boolean checkPasswordWeak(String pw) {
        return weak;
    }
}
