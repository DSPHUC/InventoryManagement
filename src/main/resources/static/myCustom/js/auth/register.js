const api = `http://localhost:9001/api/`

let registerForm = $('#registerForm');
registerForm.validate({
    onkeyup: function (element) {
        $(element).valid();
    },
    onclick: false,
    onfocusout: false,
    rules: {
        fullName: {
            required: true,
            minlength: 5
        },
        email: {
            required: true,
            email: true
        },
        username: {
            required: true,
            minlength: 5
        },
        password: {
            required: true,
            // password:true
        }
    },
    messages: {
        fullName: {
            required: 'Please enter a name',
            minlength: 'Minimum 5 characters'
        },
        email: {
            required: 'Please enter a  email',
            email: 'Please enter the correct email format'
        },
        username: {
            required: 'Please enter a username',
            minlength: 'Minimum 5 characters'
        },
        password: {
            required: 'Please enter a password',
            password: 'Password must contain at least one number and one character and be at least 5 characters long'
        }
    },
    submitHandler: () => {
        registerValid()
    }
})
registerValid = async () => {
    let username = $('#username').val();
    let password = $('#password').val();
    let fullName = $('#fullName').val();
    let email = $('#email').val();
    let phoneNumber = $('#phoneNumber').val();
    const data = {
        username: username,
        password: password,
        fullName: fullName,
        email: email,
        phoneNumber: phoneNumber
    }
    try {
        const register = await fetch(api + `auth/register`, {
            method: `POST`,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        registerForm.find('span').empty();
        if (register.ok) {
            webToast.Success({
                status: `Đăng kí tài khoản thành công`,
                message: '',
                delay: 2000,
                align: 'topright'
            });
            registerForm[0].reset();
        } else {
            const errorText = await register.json();

            Object.keys(errorText).forEach((fieldName) => {
                const errorMessage = errorText[fieldName];
                const errorSpanId = fieldName + 'Error';
                const errorSpan = $('#' + errorSpanId);
                const div = $('#' + fieldName).closest('div');

                errorSpan.text("")
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
            });
            throw new Error('Đăng kí tài khoản thất bại');
        }
    } catch
        (error) {
        console.log(error);
    }
}
registerForm.onsubmit = function (event) {
    event.preventDefault();
    registerForm.trigger("submit");

}