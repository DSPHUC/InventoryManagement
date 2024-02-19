// const api = 'http://localhost:9001/api/';
// const apiBill = api + 'billImports';
//
// const apiWarehouse = api + 'warehouses';
// const apiProduct = api + 'products';
// const apiCompany = api + 'companies';
// const billForm = $("#billImportForm");
// billForm.submit(async (e) => {
//     e.preventDefault();
//     let detail = getDataFromForm(billForm);
//     detail = {
//         // productId:
//         ...detail,
//
//     }
// })
//
// async function fetchAllProduct() {
//     return await $.ajax({
//         uri: apiProduct,
//         method: "GET",
//         dataType: "json"
//     })
// }
//
// async function fetchAllCompany() {
//     return await $.ajax({
//         uri: apiCompany,
//         method: "GET",
//         dataType: "json"
//     })
// }
//
// async function fetchAllWarehouse() {
//     return await $.ajax({
//         uri: apiWarehouse,
//         method: "GET",
//         dataType: "json"
//     })
// }
//
// getAllProduct = async () => {
//     const dataProducts = await fetchAllProduct();
//     productList= dataProducts;
//     renderProduct(dataProducts);
//
// }
// getAllCompany = async () => {
//     const dataCompanies = await fetchAllCompany();
//     companyList = dataCompanies;
//     return createSelect(dataCompanies);
// };
// getAllWarehouse = async () => {
//     const dataWarehouses = await fetchAllWarehouse();
//     warehouseList = dataWarehouses;
//     return createSelect(dataWarehouses);
// };
//
//
// function getDataFromForm(form) {
//     const data = new FormData(form);
//     return Object.fromEntries(data.entries())
// }
//
// renderProduct = (props) => {
//     let optionsStr = "";
//     props.option.forEach(e => {
//         if (e.value === props.value) {
//             optionsStr += `<option value="${e.id } "  selected>${e.name}</option>`;
//         } else {
//             optionsStr += `<option value="${e.id}">${e.name}</option>`;
//         }
//     });
//     return `
//             <div class="${props.classContainer || ''}"> <!-- Fix the template -->
//                 <label class="${props.classLabel || ''} form-label">${props.name}</label>
//                 <select class="input-custom form-control ${props.classSelect || ''}"
//                 type="${'select'}" name="${props.name}"
//                 value="${props.id}"
//                 ${props.required ? 'required' : ''}>
//                     <option value>---Choose---</option>
//                     ${optionsStr}
//                 </select>
//                 <span class="error ${props.classError || ''} form-text"></span>
//             </div>
//     `
// }
//
//
// $(async () => {
//     await getAllProduct();
//     await getAllCompany();
//     await getAllWarehouse();
// })