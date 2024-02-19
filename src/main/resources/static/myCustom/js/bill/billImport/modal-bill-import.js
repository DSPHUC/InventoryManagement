let tbModalImport = $("#tb-import-body");

$(function () {
    $("#addProductImport").click(function (e) {
        productImport.productImportDetailRequests.push({
            productId: $("#product").val(),
            quantity: $("#quantity").val(),
            price: $("#price").val(),
            companyId: $("#company").val(),
            warehouseId: $("#warehouse").val()

        })
        renderTbModalImport(productImport.productImportDetailRequests);
    });
});

function renderTbModalImport(productImportDetailRequests) {
    let str = '';
    productImportDetailRequests.forEach(e => {
        str += renderTbImportBody(e);
    })
    tbModalImport.html(str);
}

renderTbImportBody = (e) => {
    return `
        <tr>
            <th value="${getProductById(e.productId).id}">${getProductById(e.productId).name}</th>
            <th value="${e.quantity}">${e.quantity}</th>
            <th value="${e.price}">${e.price}</th>
            <th value="${getCompanyById(e.companyId).id}">${getCompanyById(e.companyId).name}</th>
            <th value="${getWarehouseById(e.companyId).id}">${getWarehouseById(e.warehouseId).name}</th>
        </tr>
            `
        ;
};

function getProductById(id) {
    return productList.find((e) => e.id == id);
}

function getCompanyById(id) {
    return companyList.find((e) => e.id == id);
}

function getWarehouseById(id) {
    return warehouseList.find((e) => e.id == id);
}

$(function () {
    $("#saveChangesButton").on("click", function () {

        $.ajax({
            url: "http://localhost:9001/api/billImports",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(productImport),
            success: function () {

                $('#staticModal').modal('hide');
                tbModalImport.empty();
                productImport.productImportDetailRequests = []
                // getList()
            }
        })
    })
});