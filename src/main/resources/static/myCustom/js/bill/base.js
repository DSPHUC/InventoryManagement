function createInput(props) {
    return `<div class="${props.classContainer || ''}">
                <label class="${props.classLabel || ''} form-label">${props.label}</label>
                <input class="input-custom form-control ${props.classInput || ''}" 
                id="${props.name}"
                type="${props.type || 'text'}" name="${props.name}"
                ${props.pattern ? `pattern="${props.pattern}"` : ""} 
                value="${props.value || ''}"
                ${props.required ? `required="${props.required}"` : ''} 
                ${props.readonly ? " readonly " : ""} 
                ${props.disabled ? " disabled " : ""}
                />
                <span class="error form-text ${props.classError}"></span>
            </div>`
}

function createSelect(props) {
    let optionsStr = "";
    let x;
    props.option.forEach(e => {
        if (e.value === props.value) {
            optionsStr += `<option value="${e.id } "  selected >${e.name}</option>`;
        } else {
            optionsStr += `<option value="${e.id}">${e.name}</option>`;
        }
        x = e;
    });

    return `<div class="${props.classContainer || ''}"> <!-- Fix the template -->
                <label class="${props.classLabel || ''} form-label" >${props.label}</label>
                <select id="${props.name}" class="input-custom form-control ${props.classSelect || ''}" 
                type="${props.type || 'text'}" name="${props.name}" 
                ${props.pattern ? `pattern=${props.pattern}` : ""} 
                value="${props.id}"
                ${props.required ? 'required' : ''}>
                    <option value>---Choose---</option>
                    ${optionsStr}
                </select>
                <span class="error ${props.classError || ''} form-text"></span>
            </div>`;
}


function createFieldForm(props) {
    if (props.type === 'select') {
        return createSelect(props);
    }
    return createInput(props);
}

const onFocus = (formBody, index) => {
    const inputsForm = formBody.find('.input-custom')
    inputsForm.eq(index).attr("focused", "true"); // add 1 attribute focused=true.
}

function renderForm(formBody, inputs) {

    formBody.empty(); //clear ô input cũ
    $.each(inputs, function (index, input) {
        formBody.append(createFieldForm(input));
    });

    const inputElements = formBody.find('.input-custom');
    for (let i = 0; i < inputElements.length; i++) {
        inputElements[i].onblur = function () {
            onFocus(formBody, i)
            validateInput(inputs[i], inputElements[i], i)
        }
        inputElements[i].oninput = function () {
            validateInput(inputs[i], inputElements[i], i)
        }
    }
}
