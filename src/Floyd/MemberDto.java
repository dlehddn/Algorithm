package Floyd;

import lombok.Getter;

@Getter
public class MemberDto {
    private final String name;
    private final String password;

    public MemberDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
