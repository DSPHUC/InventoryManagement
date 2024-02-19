const api = `http://localhost:9001/api/`
const tBody = $(`#tBody`);
const eSearch = $(`#search-input`);
const pageInput = $(`#pageInput`);
const ePagination = $('#pagination')

let pageable = {
    page: 1,
    search: ''
}
let indexOfItem = 1;
let items = [];

async function getList() {
    const sortParam = 'product,asc';
    const response =
        await fetch(api +
            `items?page=${pageable.page - 1 || 0}&search=${pageable.search || ''}`);
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
    // Cập nhật indexOfProducts
    if (pageable.page === 1) {
        indexOfItem = 1;
    }

    str += ` `
    //generate preview truoc
    str += `<li class="page-item ${pageable.first ? 'disabled' : ''}">
              <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
            </li>`
    //generate 1234

    for (let i = 1; i <= pageable.totalPages; i++) {
        str += ` <li class="page-item ${(pageable.page) === i ? 'active' : ''}" aria-current="page">
      <a class="page-link" href="#">${i}</a>
    </li>`
    }
    //
    //generate next truoc
    str += `<li class="page-item ${pageable.last ? 'disabled' : ''}">
              <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Next</a>
            </li>`
    //generate 1234
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
                <td>${item.product}</td>
                <td>${item.company}</td>
                <td>${item.warehouse}</td>
                <td>${item.stock}</td>
              </tr>`
}

window.onload = async () => {
    await renderTable();
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

