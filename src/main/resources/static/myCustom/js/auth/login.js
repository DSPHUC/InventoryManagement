const api = `http://localhost:9001/api/`
let loginForm = $('#loginForm');
loginForm.validate({
    onkeyup: function (element) {
        $(element).valid();
    },
    onclick: false,
    onfocusout: false,
    rules: {
        username: {
            required: true
        },
        password: {
            required: true
        }
    },
    messages: {
        username: {
            required: "Please enter a username"
        },
        password: {
            required: "Please enter a password"
        }
    },
    showErrors: function (errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            $("#loginForm .area-error")
                .removeClass("hide")
                .addClass("show");
        } else {
            $("#loginForm .area-error")
                .removeClass("show")
                .addClass("hide").empty();
            $("#loginForm input.error").removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: () => {
        loginValid()
    }
})
loginValid = async ()=>{
    let username = $('#username').val();
    let password = $('#password').val();
    const data = {
        username: username,
        password: password,
    }
    const response = await fetch( api+`auth/login`,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),


    })
    if (response.ok) {
        const jwtRes = await response.json();
        webToast.Success({
            status: 'login success',
            messages: '',
            delay: 2000,
            align: 'topright'
        });
        localStorage.setItem("idUser", jwtRes.id);
        localStorage.setItem("fullNameUser", jwtRes.fullName);
        console.log(jwtRes);
        console.log("log oke");
        window.location.href = '/home';
    }else {

        webToast.Danger({
            status: 'login unsuccess',
            messages: '',
            delay: 2000,
            align: 'topright'
        });
        console.log("log false");

        const errorText = await response.json();
        Object.keys(errorText).forEach((errorPass)=>{
            const errorMessage = errorText[errorPass];
            const errorSpanId = errorPass + 'Error';
            const errorSpan = $('#' + errorSpanId);
            const div = $('#' + errorPass).closest('div');
            errorSpan.empty();
            if (errorMessage) {
                const newErrorSpan = $('<label>')
                    .attr('id', errorSpanId)
                    .text(errorMessage).addClass('error');
                div.append(newErrorSpan);
            } else {
                div.removeChild(errorSpan)
                errorSpan.parentNode.removeChild(errorSpan)
                errorSpan.remove();
            }
            if (errorSpan.length > 0) {
                errorSpan.remove();
            }

        })
    }
    $('#staticModal').modal('hide');
}
loginForm.onsubmit = function (event) {
    event.preventDefault();
    loginForm.trigger("submit");
};
