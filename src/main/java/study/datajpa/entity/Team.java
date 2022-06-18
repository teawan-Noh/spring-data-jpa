package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter // 사용하지 않는게 좋지만 예제이므로 사용
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자
@ToString(of = {"id", "name"})
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") // db 연관관계 세팅: mappedBy는 FK가 없는쪽에 걸어주는게 좋다
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

}
