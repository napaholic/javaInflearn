package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final이 있는 필드만 가지고 생성자를 만들어줌
public class ItemService {

	private final ItemRepository itemRepository;

	@Transactional // readOnly = false 로 바꿔줌 가장 가까운 것이 우선권을 가짐
	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	@Transactional
	public void updateItem(Long itemId, String name, int price, int stockQuantity) {
		Item findItem = itemRepository.findOne(itemId); // 영속 상태의 객체
		findItem.change(name, price, stockQuantity);// 영속 상태의 객체의 값만 바꿔줌, 변경 감지
		// change 메서드를 만들어서 변경 감지를 사용하는 것이 더 좋은 방법
		// merge 는 모든 필드를 변경해버림 사용하지않는게 좋음
		// change 메서드를 엔티티에 만들어서 사용하는 것이 더 좋은 방법 추적이 용이함
	}

	public List<Item> findItems() {
		return itemRepository.findAll();
	}

	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}
}
