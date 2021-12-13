function comparePageAndInput(pageNum, min, max) {
    return parseInt(min) <= parseInt(pageNum) && parseInt(max) >= parseInt(pageNum);
}

function jumpToPage(data) {
    var min = 1;
    switch (data.id) {
        case "admin-visitor-jump": {
            var pageNum = $("#admin-visitor-log-page-number").val().trim();
            var max = $("#admin-visitor-log-page-number").attr("max");
            if (comparePageAndInput(pageNum, min, max)) {
                window.location.href = "/admin/logVisitorData/" + pageNum + "#paginate";
            } else {
                toastr.warning("请检查输入页数格式");
            }
            break;
        }
        case "admin-user-jump": {
            var pageNum = $("#admin-user-page-number").val().trim();
            var max = $("#admin-user-page-number").attr("max");
            if (comparePageAndInput(pageNum, min, max)) {
                window.location.href = "/admin/userData/" + pageNum + "#paginate";
            } else {
                toastr.warning("请检查输入页数格式");
            }
            break;
        }
        case "admin-permission-jump": {
            var pageNum = $("#admin-permission-page-number").val().trim();
            var max = $("#admin-permission-page-number").attr("max");
            if (comparePageAndInput(pageNum, min, max)) {
                window.location.href = "/admin/userPermissionsData/" + pageNum + "#paginate";
            } else {
                toastr.warning("请检查输入页数格式");
            }
            break;
        }
        case "admin-role-jump": {
            var pageNum = $("#admin-role-page-number").val().trim();
            var max = $("#admin-role-page-number").attr("max");
            if (comparePageAndInput(pageNum, min, max)) {
                window.location.href = "/admin/userRolesData/" + pageNum + "#paginate";
            } else {
                toastr.warning("请检查输入页数格式");
            }
            break;
        }
        case "admin-article-jump": {
            var pageNum = $("#admin-article-page-number").val().trim();
            var max = $("#admin-article-page-number").attr("max");
            var type = $("#admin-article-page-number").attr("data-type");
            var searchType = $("#admin-article-page-number").attr("data-search-type");
            if (comparePageAndInput(pageNum, min, max)) {
                window.location.href = "/admin/articleData?page=" + pageNum + "&type=" + type + "&searchType=" + searchType;
            } else {
                toastr.warning("请检查输入页数格式");
            }
            break;
        }
        case "admin-friend-jump": {
            var pageNum = $("#admin-friend-page-number").val().trim();
            var max = $("#admin-friend-page-number").attr("max");
            var search = $("#admin-friend-page-number").attr("data-search");
            var searchType = $("#admin-friend-page-number").attr("data-search-type");
            if (comparePageAndInput(pageNum, min, max)) {
                window.location.href = "/admin/friendData?page=" + pageNum + "&search=" + search + "&searchType=" + searchType;
            } else {
                toastr.warning("请检查输入页数格式");
            }
            break;
        }
        default:
            break;
    }
}

function addUserDialog() {
    $("#admin-users-add").modal("show");
}

function addPermissionDialog() {
    $("#admin-permissions-add").modal("show");
}

function updatePermissionDialog() {
    $("#admin-permissions-edit").modal("show");
}

function addRoleDialog() {
    $("#admin-role-add").modal("show");
}

function updateRoleDialog() {
    $("#admin-role-edit").modal("show");
}

function selectArticleDialog() {
    $("#admin-article-search").modal("show");
}

function selectArticle() {
    var val = $("input[name='check-select']:checked").val();
    var searchType;
    switch (val) {
        case "all":
            window.location.href = "/admin/articleData?page=1&type=all&searchType=all";
            break;
        case "type":
            searchType = $("#article-select-type").val();
            window.location.href = "/admin/articleData?page=1&type=articleType&searchType=" + searchType;
            break;
        case "label":
            searchType = $("#article-select-label").val();
            window.location.href = "/admin/articleData?page=1&type=labelType&searchType=" + searchType;
            break;
    }
}

function selectFriend() {
    var val = $("input[name='check-select']:checked").val();
    switch (val) {
        case "all":
            window.location.href = "/admin/friendData?page=1&search=all&searchType=all";
            break;
        case "select":
            var search = $("#friend-search-input").val();
            var searchType = $("#friend-select-label").val();
            window.location.href = "/admin/friendData?page=1&search="+ search +"&searchType=" + searchType;
            break;
    }
}

function showUserInfo(data) {
    $.ajax({
        url: "/api/admin/getUserByPrivateId",
        dataType: "JSON",
        data: {
            "userPrivateId": data.id
        },
        success: function (data) {
            var userInfo = '<div class="modal-body">\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-edit-private-id">用户私有ID</label>\n' +
                '                    <input type="text" class="form-control" id="admin-user-edit-private-id" value="' + data.data["userPrivateId"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-edit-username">用户名</label>\n' +
                '                    <input type="text" class="form-control" id="admin-user-edit-username" value="' + data.data["username"] + '" placeholder="请输入用户名">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-edit-email">邮箱</label>\n' +
                '                    <input type="email" class="form-control" id="admin-user-edit-email" value="' + data.data["userEmail"] + '" placeholder="请输入邮箱">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-edit-password">密码</label>\n' +
                '                    <input type="password" class="form-control" id="admin-user-edit-password" value="' + data.data["password"] + '" placeholder="请输入密码">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-edit-ip">最近登录IP</label>\n' +
                '                    <input type="text" class="form-control" id="admin-user-edit-ip" value="' + data.data["userIp"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-edit-browser">最近登录浏览器</label>\n' +
                '                    <input type="email" class="form-control" id="admin-user-edit-browser" value="' + data.data["userBrowser"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-edit-system">最近登录系统</label>\n' +
                '                    <input type="email" class="form-control" id="admin-user-edit-system" value="' + data.data["userSystem"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-edit-create-time">账号信息创建时间</label>\n' +
                '                    <input type="email" class="form-control" id="admin-user-edit-create-time" value="' + data.data["userCreateTime"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-edit-update-time">账号信息最近更新时间</label>\n' +
                '                    <input type="email" class="form-control" id="admin-user-edit-update-time" value="' + data.data["userUpdateTime"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '            </div>\n' +
                '            <div class="modal-footer justify-content-between">\n' +
                '               <button type="button" class="btn btn-danger" onclick="deleteUser(this)" id="' + data.data["userPrivateId"] + '">删除</button>';
            if (data.data["userIsBanned"] === 1) {
                userInfo +=
                    '           <button type="button" class="btn btn-danger" onclick="banUser()">封禁</button>\n';
            } else {
                userInfo +=
                    '           <button type="button" class="btn btn-primary" onclick="removeBanUser()">解禁</button>\n';
            }
            if (data.data["userIsActivity"] === 1) {
                userInfo +=
                    '           <button type="button" class="btn btn-danger" onclick="cancelUser()">注销</button>\n';
            } else {
                userInfo +=
                    '           <button type="button" class="btn btn-primary" onclick="activeUser()">激活</button>\n';
            }
            userInfo +=
                '           <button type="button" class="btn btn-primary" onclick="updateUser()">保存</button>\n' +
                '       </div>';
            $("#admin-users-info-dialog").html(userInfo);
        }
    });
    $("#admin-users-edit").modal("show");
}

function showUserPermissionInfo(data) {
    $.ajax({
        url: "/api/admin/getUserPermissionByPrivateId",
        dataType: "JSON",
        data: {
            "userPrivateId": data.id
        },
        success: function (data) {
            var userPermissionInfo = '<div class="modal-body">\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-permission-edit-private-id">用户私有ID</label>\n' +
                '                    <input type="text" class="form-control" id="admin-user-permission-edit-private-id" value="' + data.data.userPermission["userPrivateId"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-permission-edit-username">用户名</label>\n' +
                '                    <input type="text" class="form-control" id="admin-user-permission-edit-username" value="' + data.data.userPermission["username"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-permission-edit-permission-name">用户权限</label>\n' +
                '                    <select type="text" class="form-control" id="admin-user-permission-edit-permission-name">\n'+
                '                    <option value="' + data.data.userPermission["userPermission"] + '" selected = selected>' + data.data.userPermission["userPermission"] + '</option>\n';
            for(var i=0;i<data.data.permissionList.length;i++){
                if(data.data.permissionList[i]["permissionName"] !== data.data.userPermission["userPermission"]){
                    userPermissionInfo +=
                        '<option value="' + data.data.permissionList[i]["permissionName"] + '">' + data.data.permissionList[i]["permissionName"] + '</option>\n';
                }
            }
            userPermissionInfo +=
                '                    </select>'+
                '                </div>\n'+
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-permission-edit-status">用户状态</label>\n';
            if(data.data.userPermission["userPermissionStatus"] === 1){
                userPermissionInfo +=
                    '                    <input type="email" class="form-control" id="admin-user-permission-edit-status" value="正常" disabled="disabled">\n';
            }else {
                userPermissionInfo +=
                    '                    <input type="email" class="form-control" id="admin-user-permission-edit-status" value="注销" disabled="disabled">\n';
            }
            userPermissionInfo +=
                '                </div>\n'+
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-permission-edit-create-time">账号权限信息创建时间</label>\n' +
                '                    <input type="email" class="form-control" id="admin-user-permission-edit-create-time" value="' + data.data.userPermission["userPermissionCreateTime"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-permission-edit-update-time">账号权限信息最近更新时间</label>\n' +
                '                    <input type="email" class="form-control" id="aadmin-user-permission-edit-update-time" value="' + data.data.userPermission["userPermissionUpdateTime"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '            </div>\n' +
                '            <div class="modal-footer justify-content-between">\n' +
                '               <button type="button" class="btn btn-danger" onclick="deleteUserPermission(this)" id="' + data.data.userPermission["userPrivateId"] + '">删除</button>'+
                '           <button type="button" class="btn btn-primary" onclick="updateUserPermission()">保存</button>\n' +
                '       </div>';
            $("#admin-user-permission-info-dialog").html(userPermissionInfo);
            console.log(userPermissionInfo);
        }
    });
    $("#admin-user-permission-edit").modal("show");
}

function showUserRoleInfo(data) {
    $.ajax({
        url: "/api/admin/getUserRoleByPrivateId",
        dataType: "JSON",
        data: {
            "userPrivateId": data.id
        },
        success: function (data) {
            var userRoleInfo = '<div class="modal-body">\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-role-edit-private-id">用户私有ID</label>\n' +
                '                    <input type="text" class="form-control" id="admin-user-role-edit-private-id" value="' + data.data.userRole["userPrivateId"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-role-edit-username">用户名</label>\n' +
                '                    <input type="text" class="form-control" id="admin-user-role-edit-username" value="' + data.data.userRole["username"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-role-edit-permission-name">用户角色</label>\n' +
                '                    <select type="text" class="form-control" id="admin-user-role-edit-permission-name">\n'+
                '                    <option value="' + data.data.userRole["userRole"] + '" selected = selected>' + data.data.userRole["userRole"] + '</option>\n';
            for(var i=0;i<data.data.roleList.length;i++){
                if(data.data.roleList[i]["roleName"] !== data.data.userRole["userRole"]){
                    userRoleInfo +=
                        '<option value="' + data.data.roleList[i]["roleName"] + '">' + data.data.roleList[i]["roleName"] + '</option>\n';
                }
            }
            userRoleInfo +=
                '                    </select>'+
                '                </div>\n'+
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-role-edit-status">用户状态</label>\n';
            if(data.data.userRole["userRoleStatus"] === 1){
                userRoleInfo +=
                    '                    <input type="email" class="form-control" id="admin-user-role-edit-status" value="正常" disabled="disabled">\n';
            }else {
                userRoleInfo +=
                    '                    <input type="email" class="form-control" id="admin-user-edit-browser" value="注销" disabled="disabled">\n';
            }
            userRoleInfo +=
                '                </div>\n'+
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-role-edit-create-time">账号角色信息创建时间</label>\n' +
                '                    <input type="email" class="form-control" id="admin-user-role-edit-create-time" value="' + data.data.userRole["userRoleCreateTime"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="admin-user-role-edit-update-time">账号角色信息最近更新时间</label>\n' +
                '                    <input type="email" class="form-control" id="admin-user-role-edit-update-time" value="' + data.data.userRole["userRoleUpdateTime"] + '" disabled="disabled">\n' +
                '                </div>\n' +
                '            </div>\n' +
                '            <div class="modal-footer justify-content-between">\n' +
                '               <button type="button" class="btn btn-danger" onclick="deleteUserRole(this)" id="' + data.data.userRole["userPrivateId"] + '">删除</button>'+
                '           <button type="button" class="btn btn-primary" onclick="updateUserRole()">保存</button>\n' +
                '       </div>';
            $("#admin-user-roles-info-dialog").html(userRoleInfo);
        }
    });
    $("#admin-user-roles-edit").modal("show");
}

function deleteUser(data) {
    var adminDeleteUserPrivateId = data.id;
    var flag = confirm("是否删除？");
    if (flag === true) {
        $.ajax({
            url: "/api/admin/deleteUser",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: {
                "adminDeleteUserPrivateId": adminDeleteUserPrivateId
            },
            success: function (data) {
                console.log(data);
                if (data.code === "200") {
                    toastr.info(data.msg);
                } else {
                    toastr.error(data.msg);
                }
            }
        })
    }
}

function banUser() {
    var adminBanUserPrivateId = $("#admin-user-edit-private-id").val();
    var flag = confirm("是否封禁？");
    if (flag === true) {
        $.ajax({
            url: "/api/admin/banUser",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: {
                "adminBanUserPrivateId": adminBanUserPrivateId
            },
            success: function (data) {
                if (data.code === "200") {
                    toastr.info(data.msg);
                } else {
                    toastr.error(data.msg);
                }
            }
        })
    }
}

function removeBanUser() {
    var adminRemoveBanUserPrivateId = $("#admin-user-edit-private-id").val();
    var flag = confirm("是否解禁？");
    if (flag === true) {
        $.ajax({
            url: "/api/admin/removeBanUser",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: {
                "adminRemoveBanUserPrivateId": adminRemoveBanUserPrivateId
            },
            success: function (data) {
                if (data.code === "200") {
                    toastr.info(data.msg);
                } else {
                    toastr.error(data.msg);
                }
            }
        })
    }
}

function cancelUser() {
    var adminCancelUserPrivateId = $("#admin-user-edit-private-id").val();
    var flag = confirm("是否注销？");
    if (flag === true) {
        $.ajax({
            url: "/api/admin/cancelUser",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: {
                "adminCancelUserPrivateId": adminCancelUserPrivateId
            },
            success: function (data) {
                if (data.code === "200") {
                    toastr.info(data.msg);
                } else {
                    toastr.error(data.msg);
                }
            }
        })
    }
}

function activeUser() {
    var adminActiveUserUserPrivateId = $("#admin-user-edit-private-id").val();
    var flag = confirm("是否激活？");
    if (flag === true) {
        $.ajax({
            url: "/api/admin/activeUser",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: {
                "adminActiveUserUserPrivateId": adminActiveUserUserPrivateId
            },
            success: function (data) {
                console.log(data);
                if (data.code === "200") {
                    toastr.info(data.msg);
                } else {
                    toastr.error(data.msg);
                }
            }
        })
    }
}

function updateUser() {
    var adminEditUserPrivateId = $("#admin-user-edit-private-id").val();
    var adminEditUsername = $("#admin-user-edit-username").val();
    var adminEditUserEmail = $("#admin-user-edit-email").val();
    var adminEditUserPassword = $("#admin-user-edit-password").val();
    var flag = confirm("确认更新？");
    if (flag === true) {
        $.ajax({
            url: "/api/admin/updateUser",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: {
                "adminEditUserPrivateId": adminEditUserPrivateId, "adminEditUsername": adminEditUsername,
                "adminEditUserEmail": adminEditUserEmail, "adminEditUserPassword": adminEditUserPassword
            },
            success: function (data) {
                console.log(data);
                if (data.code === "200") {
                    toastr.info(data.msg);
                } else {
                    toastr.error(data.msg);
                }
            }
        })
    }
}

function insertPermission() {
    var permissionName = $("#admin-permission-add-permission-name").val();
    var permissionDescription = $("#admin-permission-add-description").val();
    var flag = confirm("确认新增权限？");
    if (flag){
        $.ajax({
            url: "/api/admin/insertPermission",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: {
                "permissionName": permissionName, "permissionDescription": permissionDescription
            },
            success: function (data) {
                console.log(data);
                if (data.code === "200") {
                    toastr.info(data.msg);
                } else {
                    toastr.error(data.msg);
                }
            }
        })
    }
}

function updatePermission() {
    var permissionNameBefore = $("#admin-permission-edit-permission-name-before").val();
    var permissionName = $("#admin-permission-edit-permission-name").val();
    var permissionDescription = $("#admin-permission-edit-description").val();
    var flag = confirm("确认更新权限？");
    if (flag){
        $.ajax({
            url: "/api/admin/updatePermission",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: {
                "permissionNameBefore": permissionNameBefore,"permissionName": permissionName,
                "permissionDescription": permissionDescription
            },
            success: function (data) {
                console.log(data);
                if (data.code === "200") {
                    toastr.info(data.msg);
                } else {
                    toastr.error(data.msg);
                }
            }
        })
    }
}


function insertRole() {
    var roleName = $("#admin-role-add-role-name").val();
    var roleDescription = $("#admin-role-add-description").val();
    var flag = confirm("确认新增角色？");
    if (flag){
        $.ajax({
            url: "/api/admin/insertRole",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: {
                "roleName": roleName, "roleDescription": roleDescription
            },
            success: function (data) {
                console.log(data);
                if (data.code === "200") {
                    toastr.info(data.msg);
                } else {
                    toastr.error(data.msg);
                }
            }
        })
    }
}

function updateRole() {
    var roleNameBefore = $("#admin-role-edit-role-name-before").val();
    var roleName = $("#admin-role-edit-role-name").val();
    var roleDescription = $("#admin-role-edit-description").val();
    var flag = confirm("确认更新角色？");
    if (flag){
        $.ajax({
            url: "/api/admin/updateRole",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: {
                "roleNameBefore": roleNameBefore,"roleName": roleName,
                "roleDescription": roleDescription
            },
            success: function (data) {
                console.log(data);
                if (data.code === "200") {
                    toastr.info(data.msg);
                } else {
                    toastr.error(data.msg);
                }
            }
        })
    }
}

function insertArticle() {
    var articleAddPrivateId = $("#article-add-private-id").val().trim();
    var articleAddTitle = $("#article-add-title").val().trim();
    var articleAddIntroduction = $("#article-add-introduction").val().trim();
    var articleAddAuthor = $("#article-add-author").val().trim();
    var articleAddIsHide = $("#article-add-is-hide").val().trim();
    var articleAddType = $("#article-add-type").val().trim();
    var articleAddLabel1 = $("#article-add-label-1").val().trim();
    var articleAddLabel2 = $("#article-add-label-2").val().trim();
    var articleAddContext = $("#article-add-context").val();
    $.ajax({
        url: "/api/article/insertArticle",
        dataType: "JSON",
        contentType: "application/json; charset=utf-8",
        data: {
            "articleAddPrivateId": articleAddPrivateId, "articleAddTitle": articleAddTitle,
            "articleAddIntroduction": articleAddIntroduction, "articleAddAuthor": articleAddAuthor,
            "articleAddIsHide": articleAddIsHide, "articleAddType": articleAddType,
            "articleAddLabel1": articleAddLabel1, "articleAddLabel2": articleAddLabel2,
            "articleAddContext": articleAddContext
        },
        success: function (data) {
            console.log(data);
            if (data.code === "200") {
                toastr.info(data.msg);
            } else {
                toastr.error(data.msg);
            }
        }
    })
}

function updateArticle() {
    var articleEditPrivateId = $("#article-edit-private-id").val().trim();
    var articleEditTitle = $("#article-edit-title").val().trim();
    var articleEditIntroduction = $("#article-edit-introduction").val().trim();
    var articleEditAuthor = $("#article-edit-author").val().trim();
    var articleEditIsHide = $("#article-edit-is-hide").val().trim();
    var articleEditType = $("#article-edit-type").val().trim();
    var articleEditLabel1 = $("#article-edit-label-1").val().trim();
    var articleEditLabel2 = $("#article-edit-label-2").val().trim();
    var articleEditContext = $("#article-edit-context").val();
    $.ajax({
        url: "/api/article/updateArticle",
        dataType: "JSON",
        contentType: "application/json; charset=utf-8",
        data: {
            "articleEditPrivateId": articleEditPrivateId, "articleEditTitle": articleEditTitle,
            "articleEditIntroduction": articleEditIntroduction, "articleEditAuthor": articleEditAuthor,
            "articleEditIsHide": articleEditIsHide, "articleEditType": articleEditType,
            "articleEditLabel1": articleEditLabel1, "articleEditLabel2": articleEditLabel2,
            "articleEditContext": articleEditContext
        },
        success: function (data) {
            if (data.code === "200") {
                toastr.info(data.msg);
            } else {
                toastr.error(data.msg);
            }
        }
    })
}

function adminAddUser() {
    var addUsername = $("#admin-user-add-username").val();
    var addUserEmail = $("#admin-user-add-email").val().trim();
    var addUserPassword = $("#admin-user-add-password").val();
    var addUserIsActivity = $("#admin-user-add-activity").val();
    $.ajax({
        url: "/api/user/insertUser",
        dataType: "JSON",
        contentType: "application/json; charset=utf-8",
        data: {
            "addUsername": addUsername, "addUserEmail": addUserEmail,
            "addUserPassword": addUserPassword, "addUserIsActivity": addUserIsActivity
        },
        success: function (data) {
            if (data.code === "200") {
                toastr.info(data.msg);
            } else {
                toastr.error(data.msg);
            }
        }
    })
}
