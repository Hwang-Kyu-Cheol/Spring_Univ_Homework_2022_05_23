package examples.spmvc.lec9.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examples.spmvc.lec9.Domain.Item;
import examples.spmvc.lec9.Repository.ItemRepository;

@Service
public class ItemService {

	private final ItemRepository itemRepository;
	
	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	public ArrayList<Item> getAllItems(){
		return itemRepository.findAll();
	}
	
	public Item getItemById(int id) {
		return itemRepository.findById(id);
	}
	
	public ArrayList<Item> getItemsByName(String name){
		return itemRepository.findByName(name);
	}
}
