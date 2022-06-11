package examples.spmvc.lec9.Repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import examples.spmvc.lec9.Domain.Item;

@Repository
public class ItemRepository {

	private static ArrayList<Item> itemList = new ArrayList<Item>();
	
	public ItemRepository() {
		
		Item item1 = new Item();
		item1.setId(1);
		item1.setName("검정색 바지");
		item1.setPrice(10000);
		item1.setDescription("이 검정 바지는 길이가 길고 통풍이 잘 됩니다.");
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setName("파란색 바지");
		item2.setPrice(11000);
		item2.setDescription("이 파란색 바지는 색깔이 형광 파랑색에 가깝습니다.");
		
		Item item3 = new Item();
		item3.setId(3);
		item3.setName("노란색 바지");
		item3.setPrice(12000);
		item3.setDescription("이 노란색 바지는 주름이 잘 잡혀있습니다.");
		
		Item item4 = new Item();
		item4.setId(4);
		item4.setName("하늘색 바지");
		item4.setPrice(13000);
		item4.setDescription("이 하늘색 바지는 허리에 밴딩처리가 되어있습니다.");
		
		Item item5 = new Item();
		item5.setId(5);
		item5.setName("빨간색 바지");
		item5.setPrice(14000);
		item5.setDescription("이 빨간색 바지는 땀 배출이 잘 되며, 잘 늘어납니다.");
		
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		itemList.add(item4);
		itemList.add(item5);
	}
	
	public ArrayList<Item> findAll(){
		return itemList;
	}
	
	public Item findById(int id) {
		for(Item item: itemList) {
			if(item.getId() == id) {
				return item;
			}
		}
		return null;
	}
	
	public ArrayList<Item> findByName(String name){
		ArrayList<Item> items = new ArrayList<Item>();
		
		for(Item item: itemList) {
			if(item.getName().contains(name)) {
				items.add(item);
			}
		}
		
		return items;
	}
}
