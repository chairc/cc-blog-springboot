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
    var UserRegisteredEntity = {
        "registeredUsername": registeredUsername, "registeredEmail": registeredEmail,
        "registeredPassword": registeredPassword, "registeredRetypePassword": registeredRetypePassword,
        "registeredVerificationCode": registeredVerificationCode
    };
    $.ajax({
        url: "/api/user/registered",
        dataType: "JSON",
        data: JSON.stringify(UserRegisteredEntity),
        type: "POST",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
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
});

//  找回密码验证码
$("#btn-forgot-password-verification").click(function () {
    var forgotEmail = $("#forgot-password-email").val().trim();
    if (checkEmailType(forgotEmail)) {
        $.ajax({
            url: "/api/verification/forgotPassword",
            dataType: "JSON",
            data: {
                "forgotEmail": forgotEmail
            },
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                if (data.code === "200") {
                    toastr.info(data.msg);
                    var obj = $("#btn-forgot-password-verification");
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

//  找回密码
$("#btn-forgot-password").click(function () {
    var forgotEmail = $("#forgot-password-email").val().trim();
    var forgotPassword = $("#forgot-password-password").val().trim();
    var forgotRetypePassword = $("#forgot-password-retype-password").val().trim();
    var forgotVerificationCode = $("#forgot-password-verification-code").val().trim();
    var userUpdateForgotPasswordEntity = {
        "forgotEmail": forgotEmail, "forgotPassword": forgotPassword,
        "forgotRetypePassword": forgotRetypePassword, "forgotVerificationCode": forgotVerificationCode
    };
    $.ajax({
        url: "/api/user/updateForgotPassword",
        dataType: "JSON",
        data: JSON.stringify(userUpdateForgotPasswordEntity),
        contentType: "application/json; charset=utf-8",
        type: "POST",
        success: function (data) {
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

$("#message-board-send").click(function () {
    var messageContent = $("#message-add").val();
    var messageInsertEntity = {
        "messageContent": messageContent
    };
    $.ajax({
        url: "/api/message/insertMessage",
        dataType: "JSON",
        data: JSON.stringify(messageInsertEntity),
        type: "POST",
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

$("#comment-message-board-send").click(function () {
    var messagePrivateId = $("#comment-message-board-send").attr("message-id");
    var commentMessageContent = $("#comment-message-add").val();
    var commentMessageInsertEntity = {
        "messagePrivateId": messagePrivateId, "commentMessageContent": commentMessageContent
    };
    $.ajax({
        url: "/api/comment/insertCommentMessage",
        dataType: "JSON",
        data: JSON.stringify(commentMessageInsertEntity),
        type: "POST",
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

$("#comment-message-board-clear").click(function () {
    $("#comment-message-add").val("");
    $("#comment-message-add").focus();
});

$("#friend-send").click(function () {
    var friendWebTitle = $("#friend-web-title").val();
    var friendWebsite = $("#friend-website").val();
    var friendIntroduction = $("#friend-introduction").val();
    var friendHeadUrl = $("#friend-head-url").val();
    var friendInsertEntity = {
        "friendWebTitle": friendWebTitle, "friendWebsite": friendWebsite,
        "friendIntroduction": friendIntroduction, "friendHeadUrl": friendHeadUrl
    };
    $.ajax({
        url: "/api/friend/insertFriendApplication",
        dataType: "JSON",
        data: JSON.stringify(friendInsertEntity),
        type: "POST",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.code === "200") {
                toastr.info(data.msg);
            } else {
                toastr.error(data.msg);
            }
        }
    })
});

$("#friend-clear").click(function () {
    $("#friend-web-title").val("");
    $("#friend-website").val("");
    $("#friend-introduction").val("");
    $("#friend-head-url").val("");
    $("#friend-web-title").focus();
});