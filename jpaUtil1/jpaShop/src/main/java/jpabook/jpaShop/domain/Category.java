package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

	@Id @GeneratedValue
	@Column(name = "category_id")
	private Long id;

	private String name;

	// 다대다 관계는 중간에 연결 테이블이 필요하다.
	// 다대다 관계는 실무에서 쓰지 않는다.
	// 다대다 관계를 일대다, 다대일 관계로 풀어내는 연결 테이블을 만들어서 사용한다.
	// 다대다 관계를 일대다, 다대일 관계로 풀어내는 연결 테이블을 엔티티로 승격시킨다.
	// 다대다 관계를 일대다, 다대일 관계로 풀어내는 연결 테이블을 엔티티로 승격시키면
	// 연결 테이블에 필요한 컬럼을 추가할 수 있다.
	@ManyToMany
	@JoinTable(name = "category_item",
		joinColumns = @JoinColumn(name = "category_id"),
		inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<Item> items = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private List<Category> child = new ArrayList<>();

	// 연관관계 편의 메서드
	public void addChildCategory(Category child) {
		this.child.add(child);
		child.setParent(this);
	}
}
