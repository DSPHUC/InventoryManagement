package com.example.inventorymanagement.service.item;

import com.example.inventorymanagement.model.Item;
import com.example.inventorymanagement.model.dto.itemDTO.ItemListResponse;
import com.example.inventorymanagement.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements IItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Page<ItemListResponse> findAllItemPage(Pageable pageable, String search) {
        search = "%" + search + "%";
        return itemRepository.findAllItem(pageable,search).map(item -> {
            ItemListResponse itemListResponse = new ItemListResponse();
            itemListResponse.setWarehouse(item.getWarehouse());
            itemListResponse.setCompany(item.getCompany());
            itemListResponse.setProduct(item.getProduct());
            itemListResponse.setStock(item.getStock());
            return itemListResponse;
        });
    }


}
