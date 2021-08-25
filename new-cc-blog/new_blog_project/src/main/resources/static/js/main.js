function refreshPage() {
    window.location.reload();
}

function jumpToIndex() {
    window.location.href = "/"
}

function jumpTo(url) {
    window.location.href = url;
}

function shareURL(data) {
    var clipboard = new ClipboardJS('.clipTarget', {
        text: function () {
            return window.location.host + data.id;
        }
    });
    clipboard.on('success', function (e) {
        toastr.info("分享链接已复制到粘贴板");
        e.clearSelection();
        clipboard.destroy();
    });
    clipboard.on('error', function (e) {
        toastr.error('Action:', e.action);
        toastr.error('Trigger:', e.trigger);
    });
}

function checkEmailType(emailData) {
    return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(emailData);
}

var time = 60;

//  发送计时器
function setTime(obj) {
    if (time === 0) {
        obj.html("重新发送");
        obj.removeAttr("disabled");
        time = 60;
        return;
    } else {
        obj.attr("disabled", "disabled");
        obj.html(time.toString() + "秒后重发");
        time--;
    }
    setTimeout(function () {
        setTime(obj);
    }, 1000);
}

//  登录
$("#btn-login").click(function () {
    var loginEmail = $("#login-email").val().trim();
    var loginPassword = $("#login-password").val().trim();
    if (checkEmailType(loginEmail)) {
        $.ajax({
            url: "/api/user/login",
            dataType: "JSON",
            data: {
                "userEmail": loginEmail, "userPassword": loginPassword
            },
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                console.log(data);
                if (data.code === "200") {
                    toastr.info(data.msg);
                    setTimeout(function () {
                        window.location.href = "/";
                    }, 2000);
                } else {
                    //登录失败，用户名或密码错误
                    toastr.error(data.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                toastr.error(XMLHttpRequest.status);
                toastr.error(XMLHttpRequest.readyState);
                toastr.error(textStatus);
                toastr.error(errorThrown);
            }
        })
    } else {
        toastr.error("邮箱格式不正确")
    }

});

//  注册验证码
$("#btn-registered-verification").click(function () {
    var registeredEmail = $("#registered-email").val().trim();
    if (checkEmailType(registeredEmail)) {
        $.ajax({
            url: "/api/verification/registered",
            dataType: "JSON",
            data: {
                "registeredEmail": registeredEmail
            },
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                console.log(data);
                if (data.code === "200") {
                    toastr.info(data.msg);
                    var obj = $("#btn-registered-verification");
                    //  设置倒计时
                    setTime(obj);
                } else {
                    toastr.error(data.msg);
                }
            }
        })
    } else {
        toastr.error("邮箱格式不正确")
    }
});

//  注册
$("#btn-registered").click(function () {
    var registeredUsername = $("#registered-username").val().trim();
    var registeredEmail = $("#registered-email").val().trim();
    var registeredPassword = $("#registered-password").val().trim();
    var registeredRetypePassword = $("#registered-retype-password").val().trim();
    var registeredVerificationCode = $("#registered-verification-code").val().trim();
    if (checkEmailType(registeredEmail)) {
        $.ajax({
            url: "/api/user/registered",
            dataType: "JSON",
            data: {
                "registeredUsername": registeredUsername, "registeredEmail": registeredEmail,
                "registeredPassword": registeredPassword, "registeredRetypePassword": registeredRetypePassword,
                "registeredVerificationCode": registeredVerificationCode
            },
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                console.log(data);
                if (data.code === "200") {
                    toastr.info(data.msg);
                    setTimeout(function () {
                        window.location.href = "/login";
                    }, 2000);
                } else {
                    toastr.error(data.msg);
                }
            }
        })
    } else {
        toastr.error("邮箱格式不正确")
    }
});

//  注销
function userLogout() {
    $.ajax({
        url: "/api/user/logout",
        dataType: "JSON",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.code === "200") {
                toastr.info(data.msg);
                setTimeout(function () {
                    window.location.href = "/";
                }, 2000)
            } else {
                toastr.error(data.msg);
            }
        }
    })
}

//  头像上传
$("#common-upload-head").click(function () {

});

$("#message-board-send").click(function () {
    var messageContent = $("#message-add").val();
    $.ajax({
        url: "/api/message/insertMessage",
        dataType: "JSON",
        data:{
          "messageContent":messageContent
        },
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.code === "200") {
                toastr.info(data.msg);
                setTimeout(function () {
                    window.location.reload();
                }, 2000)
            } else {
                toastr.error(data.msg);
            }
        }
    })
});

$("#message-board-clear").click(function () {
    $("#message-add").val("");
    $("#message-add").focus();
});