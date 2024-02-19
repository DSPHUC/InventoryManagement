const api = 'http://localhost:9001/api/'
const tBody = $(`#tBody`);
const ePagination = $('#pagination')
const eSearch = $(`#search-input`);
const pageInput = $(`#pageInput`);
let indexOfItem = 1;
let pageable = {
    page: 1,
    search: ''
}
let items = [];
let products;
let companies;
let warehouses;


const billImportForm = document.getElementById('billImportForm');
const formBody = $('#formBody');

let billSelected = {};
/*
billImportForm.onsubmit = async (e) => {
    console.log("check")
    e.preventDefault();
    let data = getDataFromForm(billImportForm);
    // let data = addBill(tbModalImport);
    data = {
        ...data,
        id: billSelected.id
    };
    if (!billSelected.id) {
        const response = await createBill(data);
        if (response.ok) {
            webToast.Success({
                status: 'Create Bill Import success',
                message: '',
                delay: 2000,
                align: 'upright'
            });
        } else {
            webToast.Danger({
                status: 'Create Bill Import unsuccessful',
                message: '',
                delay: 2000,
                align: 'upright'
            });

        }
    } else {
        console.log("false")
    }

    await renderTable();
    $('#staticModal').modal('hide');
}
*/

async function createBill(data) {
    return  await fetch( `/api/billImports`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

function getDataFromForm(form) {
    const data = new FormData(form);
    return Object.fromEntries(data.entries())
}

async function getList() {
    const sortParam = 'id,asc'
    const response =
        await fetch(api +
            `billImports?page=${pageable.page - 1 || 0}&search=${pageable.search || ''}`);
    if (!response.ok) {
        throw new Error("Failed to fetch data");
    }
    const result = await response.json();
    pageable = {
        ...pageable,
        ...result
    };
    renderTBody(result.content);
    await renderPagination();
    return result;
}

function renderTBody(items) {
    let str = '';
    items.forEach(e => {
        str += renderItemStr(e);
        indexOfItem++;
    })
    tBody.html(str);
}

const renderPagination = async () => {
    ePagination.empty();
    let str = '';
    if (pageable.page === 1) {
        indexOfItem = 1;
    }

    str += ` `
    str += `<li class="page-item ${pageable.first ? 'disabled' : ''}">
              <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
            </li>`

    for (let i = 1; i <= pageable.totalPages; i++) {
        str += ` <li class="page-item ${(pageable.page) === i ? 'active' : ''}" aria-current="page">
      <a class="page-link" href="#">${i}</a>
    </li>`
    }
    str += `<li class="page-item ${pageable.last ? 'disabled' : ''}">
              <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Next</a>
            </li>`
    pageInput.html(str);

    const ePages = pageInput.find('li');
    const ePrevious = ePages[0];
    const eNext = ePages[ePages.length - 1]

    ePrevious.onclick = () => {
        if (pageable.page === 1) {
            return;
        }
        pageable.page -= 1;
        getList();
    }
    eNext.onclick = () => {
        if (pageable.page === pageable.totalPages) {
            return;
        }
        pageable.page += 1;
        // Update indexOfProducts based on the current page
        indexOfItem = (pageable.page - 1) * 5 + 1;
        getList();
    }
    for (let i = 1; i < ePages.length - 1; i++) {
        if (i === pageable.page) {
            continue;
        }
        ePages[i].onclick = () => {
            pageable.page = i;
            // Update indexOfProducts based on the current page
            indexOfItem = (pageable.page - 1) * 5 + 1;
            getList();
        }
    }
}

function renderItemStr(item) {
    return ` <tr>
                <td>${item.id}</td>
                <td>${item.creator}</td>
                <td>${item.createAt}</td>
                <td>${item.total}</td>
<!--                <td>-->
<!--                   <button type="submit" id="bill-detail" class="btn btn-primary">Detail</button>-->
<!--                </td>-->
              </tr>`
}

async function getListCompany() {
    const response = await fetch(`/api/companies`);
    return response.json();
}

async function getListWarehouse() {
    const response = await fetch(`/api/warehouses`);
    return response.json();
}

async function getListProduct() {
    const response = await fetch(`/api/products`);
    return response.json();
}

async function renderTable() {
    const pageable = await getList();
    indexOfItem = 1;
    items = pageable.content;
    renderTBody(items);
}

const onSearch = async (e) => {
    e.preventDefault()
    pageable.search = eSearch.value;
    pageable.page = 1;
    await getList();
}
const searchInput = $('#search-input');
searchInput.on('input', async () => {
    await onSearch(event);
});

function showCreateBillImport() {
    $('#staticModalLabel').text('Create Bill Import');
    clearForm();
    renderForm(formBody, getDataInput())
}

function clearForm() {
    billImportForm.reset();
    billSelected = {};

}

function getDataInput() {
    return [
        {
            label: 'Product',
            name: 'product',
            id: products.id,
            value: billSelected.productId,
            type: 'select',
            option: products,
            required: true,
            message: 'Please choice Product',
        },
        {
            label: 'Quantity',
            name: 'quantity',
            // value:'' ,
            value: billSelected.quantity,
            required: true,
            pattern: "[1-9][0-9]{1,10}",
            message: 'quantity must bigger than 0',

        }, {
            label: 'Price',
            name: 'price',
            // value: '',
            value: billSelected.price,
            required: true,
            pattern: "[1-9][0-9]{1,10}",
            message: 'Price must bigger than 0',

        },
        {
            label: 'Company',
            name: 'company',
            value: billSelected.companyId,
            // value: billSelected.company,
            type: 'select',
            option: companies,
            required: true,
            message: 'Please choice Company',
        },
        {
            label: 'Warehouse',
            name: 'warehouse',
            value: billSelected.warehouseId,
            // value: billSelected.warehouse,
            type: 'select',
            option: warehouses,
            required: true,
            message: 'Please choice Warehouse',
        }
    ];
}

window.onload = async () => {
    companyList = companies = await getListCompany();
    warehouseList = warehouses = await getListWarehouse();
    productList = products = await getListProduct();
    await renderTable();
    renderForm(formBody, getDataInput());
}
