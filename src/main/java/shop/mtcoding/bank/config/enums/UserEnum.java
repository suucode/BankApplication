package shop.mtcoding.bank.config.enums;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum UserEnum {
    ADMIN("관리자"), CUSTOMER("고객");

    private final String value;
    
}
