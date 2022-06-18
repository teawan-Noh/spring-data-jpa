package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter // 사용하지 않는게 좋지만 예제이므로 사용
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자
@ToString(of = {"id", "username", "age"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)  // ManyToOne은 fetchType.LAZY (지연로딩)설정 -> 후에 성능 최적화
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(team != null){
            changeTeam(team);
        }
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    // 팀 변경
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this); // 객체기 때문에 반대쪽에도 바꿔줘야함
    }
}
