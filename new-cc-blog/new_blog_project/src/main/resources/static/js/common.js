function updatePassword() {
    var oldPassword = $("#old-password").val().trim();
    var newPassword = $("#new-password").val().trim();
    var newPasswordAgain = $("#new-password-again").val().trim();
    $.ajax({
        url: "/api/user/updateUserPassword",
        dataType: "JSON",
        data: {
            "oldPassword": oldPassword, "newPassword": newPassword, "newPasswordAgain": newPasswordAgain
        },
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.code === "200") {
                toastr.info(data.msg);
                setTimeout(function () {
                    parent.location.href = "/login";
                }, 2000);
            } else {
                toastr.error(data.msg);
            }
        }
    });
}

function updateHeadImg() {
    var formData = new FormData();
    formData.append("headFile", $("#setting-head-file")[0].files[0]);
    $.ajax({
        type: "post",
        url: "/api/upload/uploadHeadFile",
        dataType: "JSON",
        data: formData,
        processData: false, // 使数据不做处理
        contentType: false, // 不要设置Content-Type请求头
        success: function (data) {
            console.log(data);
            if (data.code === "200") {
                toastr.info(data.msg);
            } else {
                toastr.error(data.msg);
            }
        }
    });
}

function updateUserInfo() {
    
}