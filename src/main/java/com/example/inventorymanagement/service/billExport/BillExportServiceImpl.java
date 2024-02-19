package com.example.inventorymanagement.service.billExport;

import com.example.inventorymanagement.model.*;
import com.example.inventorymanagement.model.await.User;
import com.example.inventorymanagement.model.await.UserInfo;
import com.example.inventorymanagement.model.dto.billExportDTO.BillExportRequest;
import com.example.inventorymanagement.model.dto.billExportDTO.response.BillExportListResponse;
import com.example.inventorymanagement.model.dto.billImportDTO.response.BillImportListResponse;
import com.example.inventorymanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BillExportServiceImpl implements IBillExportService {

    @Autowired
    private BillExportRepository billExportRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ExportDetailRepository exportDetailRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<BillExport> findAll() {return billExportRepository.findAll();}

    @Override
    public BillExport findById(Long aLong) {
        return null;
    }

    @Override
    public void save(BillExport billExport) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void create(BillExportRequest billExportRequest) {
        BillExport billExport = new BillExport();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedCreateAt = LocalDateTime.now().format(formatter);
        billExport.setCreateAt(formattedCreateAt);
        billExportRepository.save(billExport);
        List<ExportDetail> list = billExportRequest.getProductExportDetailRequests()
                .stream()
                .map(productDetailRequest -> {
                    Product product = productRepository.findById(productDetailRequest.getProductId()).get();

                    ExportDetail exportDetail = new ExportDetail();
                    exportDetail.setQuantity(productDetailRequest.getQuantity());
                    exportDetail.setProduct(product);
                    exportDetail.setProductName(product.getName());
                    BigDecimal priceSell = productDetailRequest.getPrice();
                    exportDetail.setPrice(priceSell);
                    exportDetail.setTotal(productDetailRequest.getPrice().multiply(productDetailRequest.getQuantity()));
                    exportDetail.setBillExport(billExport);

                    List<Item> listItemFind = itemRepository
                            .findAllByProductAndCompanyAndWarehouseAndStock(
                                    productDetailRequest.getProductId()
                                    , productDetailRequest.getCompanyId()
                                    , productDetailRequest.getWarehouseId());

                    BigDecimal current = productDetailRequest.getQuantity();
                    for (int i = 0; i < listItemFind.size() && current.compareTo(BigDecimal.ZERO)>0 ; i++) {
                        BigDecimal countAvailable = listItemFind.get(i).getStock().subtract(listItemFind.get(i).getSold())  ;
                        if (current.compareTo(countAvailable) >= 0) {
                            BigDecimal stock = listItemFind.get(i).getStock();
                            listItemFind.get(i).setSold(stock);

                            listItemFind.get(i).setSoldOut(1);
                            listItemFind.get(i).setTotalPriceSell(
                                    listItemFind.get(i).getTotalPriceSell().add(
                                            priceSell.multiply(
                                                    stock
                                            )
                                    )
                            );
                            current = current.subtract(countAvailable) ;
                            continue;
                        }
                        BigDecimal stock = listItemFind.get(i).getSold().add(current);
                        listItemFind.get(i).setTotalPriceSell(listItemFind.get(i).getTotalPriceSell().add(
                                priceSell.multiply(
                                        stock
                                )
                        ));
                        listItemFind.get(i).setSold(stock);
                        current = BigDecimal.ZERO;
                    }

                    itemRepository.saveAll(listItemFind);
                    return exportDetail;
                }).collect(Collectors.toList());

        exportDetailRepository.saveAll(list);

        User user ;
        if (billExportRequest.getIdUser() == null) {
            user = userRepository.findById(1L).get();
        } else {
            user =userRepository.findById(Long.valueOf(billExportRequest.getIdUser())).get();

        }
        UserInfo userInfo = userInfoRepository.findUserInfoByUserId(user.getId());
        billExport.setUserInfo(userInfo);
        billExport.setExportDetails(list);
        billExportRepository.save(billExport);
    }

    public Page<BillExportListResponse> findAllBill(Pageable pageable, String search) {
        search = "%" + search + "%";
        return billExportRepository.findAllBill(pageable, search)
                .map(bill -> {
                    BillExportListResponse billExportListResponse = new BillExportListResponse();
                    billExportListResponse.setId(bill.getId());
                    billExportListResponse.setCreator(bill.getCreator());
                    billExportListResponse.setCreateAt(bill.getCreateAt());
                    billExportListResponse.setTotal(bill.getTotal());
                    return billExportListResponse;
                });
    }
}
