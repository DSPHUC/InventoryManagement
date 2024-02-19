package com.example.inventorymanagement.service.billImport;

import com.example.inventorymanagement.model.BillImport;
import com.example.inventorymanagement.model.ImportDetail;
import com.example.inventorymanagement.model.Item;
import com.example.inventorymanagement.model.Product;
import com.example.inventorymanagement.model.await.User;
import com.example.inventorymanagement.model.await.UserInfo;
import com.example.inventorymanagement.model.dto.billImportDTO.BillImportRequest;
import com.example.inventorymanagement.model.dto.billImportDTO.response.BillImportListResponse;
import com.example.inventorymanagement.model.dto.importDetailDTO.ImportDetailListResponse;
import com.example.inventorymanagement.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class BillImportServiceImpl implements IBillImportService {
    @Autowired
    private BillImportRepository billImportRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImportDetailRepository importDetailRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(BillImportRequest billImportRequest) {

        BillImport billImport = new BillImport();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedCreateAt = LocalDateTime.now().format(formatter);
        billImport.setCreateAt(formattedCreateAt);
        billImportRepository.save(billImport);

        List<ImportDetail> list = billImportRequest.getProductImportDetailRequests()
                .stream()
                .map(productDetailRequest -> {
                    Product product = productRepository.findById(productDetailRequest.getProductId()).get();

                    ImportDetail importDetail = new ImportDetail();
                    importDetail.setQuantity(productDetailRequest.getQuantity());
                    importDetail.setPrice(productDetailRequest.getPrice());
                    importDetail.setTotal(productDetailRequest.getQuantity().multiply(productDetailRequest.getPrice()));
                    importDetail.setProduct(product);
                    importDetail.setProductName(product.getName());
                    importDetail.setBillImport(billImport);

//                    importDetailRepository.save(importDetail);
                    Item item = new Item();
                    item.setImport_detail(importDetail);
                    item.setTotalPriceBuy((importDetail.getQuantity()).multiply(importDetail.getPrice()));
                    item.setTotalPriceSell(BigDecimal.ZERO);
                    item.setStock(importDetail.getQuantity());
                    item.setCompany(companyRepository.findById(productDetailRequest.getCompanyId()).get());
                    item.setWarehouse(warehouseRepository.findById(productDetailRequest.getWarehouseId()).get());
                    item.setSoldOut(0);
                    item.setSold(BigDecimal.ZERO);

                    itemRepository.save(item);
                    return importDetail;
                }).collect(Collectors.toList());

        importDetailRepository.saveAll(list);
        User user;
        if (billImportRequest.getIdUser() == null) {
            user = userRepository.findById(1L).get();
        } else {
            user = userRepository.findById(Long.valueOf(billImportRequest.getIdUser())).get();
        }

        UserInfo userInfo = userInfoRepository.findUserInfoByUserId(user.getId());
        billImport.setUserInfo(userInfo);
        billImport.setImportDetails(list);
        billImportRepository.save(billImport);
    }

    @Override
    public List<BillImport> findAll() {
        return billImportRepository.findAll();
    }

    @Override
    public List<ImportDetailListResponse> findAllImportDetailByIdBillImport(Long idBillImport) {
        return importDetailRepository.findAllByBillImportId(idBillImport);
    }

    @Override
    public BillImport findById(Long aLong) {
        return null;
    }

    @Override
    public void save(BillImport billImport) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Page<BillImportListResponse> findAllBill(Pageable pageable, String search) {
        search = "%" + search + "%";
        return billImportRepository.findAllBill(pageable, search)
                .map(bill -> {
                    BillImportListResponse billImportListResponse = new BillImportListResponse();
                    billImportListResponse.setId(bill.getId());
                    billImportListResponse.setCreator(bill.getCreator());
                    billImportListResponse.setCreateAt(bill.getCreateAt());
                    billImportListResponse.setTotal(bill.getTotal());
                    return billImportListResponse;
                });
    }
}
