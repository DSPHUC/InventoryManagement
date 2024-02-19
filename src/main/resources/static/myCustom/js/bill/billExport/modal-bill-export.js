let productExport = {
    "productExportDetailRequests": []
}
let productList = [];
let warehouseList = [];
let companyList = [];

let tbModalExport = $("#tb-export-body");

$(function () {
    $("#addProductExport").click(function (e) {
        productExport.productExportDetailRequests.push({
            productId: $("#product").val(),
            quantity: $("#quantity").val(),
            price: $("#price").val(),
            companyId: $("#company").val(),
            warehouseId: $("#warehouse").val()

        })
        renderTbModalExport(productExport.productExportDetailRequests);
    });
});

function renderTbModalExport(productExportDetailRequests) {
    let str = '';
    productExportDetailRequests.forEach(e => {
        str += renderTbExportBody(e);
    })
    tbModalExport.html(str);
}

renderTbExportBody = (e) => {
    return `
        <tr>
            <th  value="${getProductById(e.productId).id}">${getProductById(e.productId).name}</th>
            <th type="number" value="${e.quantity}">${e.quantity}</th>
            <th type="number" value="${e.price}">${e.price}</th>
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
            url: "http://localhost:9001/api/billExports",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(productExport),
            success: function () {

                $('#staticModal').modal('hide');
                tbModalExport.empty();
                productExport.productExportDetailRequests = []
                // getList()
            }
        })
    })
});