const api = `http://localhost:9001/api/`
const tBodyExport = $(`#tBodyExport`);
const eSearch = $(`#search-input`);
const pageInput = $(`#pageInput`);
const ePagination = $('#pagination')

let pageable = {
    page: 1,
    search: ''
}
let indexOfItem = 1;
let items = [];
let products;
let companies;
let warehouses;

const billExportForm = document.getElementById(`billExportForm`);
const formBodyExport =$('#formBodyExport');
let billSelectedExport = {};
async function getList()
{
    const sortParam = 'product,asc';
    const response =
        await fetch(api +
            `billExports?page=${pageable.page - 1 || 0}&search=${pageable.search || ''}`);
    if (!response.ok) {
        throw new Error("Failed to fetch data");
    }
    const result = await response.json();
    pageable = {
        ...pageable,
        ...result
    };
    renderTBodyExport(result.content);
    await renderPagination();
    return result;
}

function renderTBodyExport(items) {
    let str = '';
    items.forEach(e => {
        str += renderItemStr(e);
        indexOfItem++;
    })
    tBodyExport.html(str);
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
    //
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
              </tr>`
}

window.onload = async () => {
    await renderTableExport();
    companyList = companies = await getListCompany();
    warehouseList = warehouses = await getListWarehouse();
    productList = products = await getListProduct();
    renderForm(formBodyExport, getDataInputExport());
}
async function renderTableExport() {
    const pageable = await getList();
    indexOfItem = 1;
    items = pageable.content;
    renderTBodyExport(items);
}
const onSearchExport = async (e) => {
    e.preventDefault()
    pageable.search = eSearch.value;
    pageable.page = 1;
    await getList();
}
const searchInput = $('#search-input');
searchInput.on('input', async () => {
    await onSearchExport(event);
});
function showCreateBillExport(){
    $('#staticModalLabel').text('Create Bill Export');
    clearFormExport();
    renderForm(formBodyExport, getDataInputExport())
}
function clearFormExport(){
    billExportForm.reset();
    billSelectedExport = {};
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

function getDataInputExport() {
    return [
        {
            label: 'Product',
            name: 'product',
            id: products.id,
            value: billSelectedExport.productId,
            type: 'select',
            option: products,
            required: true,
            message: 'Please choice Product',
        },
        {
            label: 'Quantity',
            name: 'quantity',
            // value:'' ,
            value: billSelectedExport.quantity,
            required: true,
            pattern: "[1-9][0-9]{1,10}",
            message: 'quantity must bigger than 0',

        }, {
            label: 'Price',
            name: 'price',
            // value: '',
            value: billSelectedExport.price,
            required: true,
            pattern: "[1-9][0-9]{1,10}",
            message: 'Price must bigger than 0',

        },
        {
            label: 'Company',
            name: 'company',
            value: billSelectedExport.companyId,
            // value: billSelectedExport.company,
            type: 'select',
            option: companies,
            required: true,
            message: 'Please choice Company',
        },
        {
            label: 'Warehouse',
            name: 'warehouse',
            value: billSelectedExport.warehouseId,
            // value: billSelectedExport.warehouse,
            type: 'select',
            option: warehouses,
            required: true,
            message: 'Please choice Warehouse',
        }
    ];
}
