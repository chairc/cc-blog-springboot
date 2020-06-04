var userPageNum = 1;
var articlePageNum = 1;
var messagePageNum = 1;
var selectPages;

//查找单一用户
function selectUser() {
    var userId = $("#userId").val().trim();
    var test = $('#p_test');

    $.ajax({
        url: "/superAdmin/showUser",
        dataType: "JSON",
        data: {
            "userId": userId
        },
        success: function (data) {
            console.log(data);
            if (data.code === "0") {
                $("#message-box-text").html(data.msg);
                $("#message-box").css("color", "#a94442");
                $("#message-box").css("background", "#f2dede");
                setTimeout(function () {
                    $('#message-box').slideUp(300);
                }, 1000);
                if ($("#message-box").is(":hidden")) {
                    $('#message-box').slideDown(300);
                } else {
                    $('#message-box').slideUp(300);
                }
            } else if(data.code === "1") {
                test.html('<div id="p_test">' + data.data + '</div>');
                //var jsonVal = data.result;
                //data1 = (new Function("", "return " + jsonVal))();

                $("#showData").html(
                    '<li id="showData">' +
                    '<div class="content-text" style="display: inline-block;width: 100%;align-content: center">' +
                    '<div style="float: left;">' +
                    '<p>公有ID:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'th:id="id' + data.data['user_common_id'] + '" type="text"' +
                    'value="' + data.data['user_common_id'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>私有ID:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="privateId" type="text"' +
                    'value="' + data.data['user_common_private_id'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>OpenID:' +
                    '<label>' +
                    '<input style="background: transparent;border: none" id="openId" type="text"' +
                    'value="' + data.data['user_common_open_id'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>用户名:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="username" type="text"' +
                    'value="' + data.data['user_common_username'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>密码:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="password" type="text"' +
                    'value="' + data.data['user_common_password'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>昵称:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="nickname" type="text"' +
                    'value="' + data.data['user_common_nickname'] + '">' +
                    '</label>' +
                    '</p>' +
                    '</div>' +
                    '<div style="float: left;">' +
                    '<p>真实姓名:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="realname" type="text"' +
                    'value="' + data.data['user_secret_real_name'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>电话:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="phone" type="text"' +
                    'value="' + data.data['user_secret_phone'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>电子邮箱:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="email" type="text"' +
                    'value="' + data.data['user_secret_email'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>生日:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="birthday" type="text"' +
                    'value="' + data.data['user_secret_birthday'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>性别:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="sex" type="text"' +
                    'value="' + data.data['user_secret_sex'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>年龄:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="age" type="text"' +
                    'value="' + data.data['user_secret_age'] + '">' +
                    '</label>' +
                    '</p>' +
                    '</div>' +
                    '<div style="float: left">' +
                    '<p>微信:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="wechat" type="text"' +
                    'value="' + data.data['user_secret_wechat'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>QQ:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="qq" type="text"' +
                    'value="' + data.data['user_secret_qq'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>微博:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="weibo" type="text"' +
                    'value="' + data.data['user_secret_weibo'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>地址:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="address" type="text"' +
                    'value="' + data.data['user_secret_address'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>登录时间:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="logtime" type="text"' +
                    'value="' + data.data['user_safe_logtime'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>IP地址:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="ip" type="text"' +
                    'value="' + data.data['user_safe_ip'] + '">' +
                    '</label>' +
                    '</p>' +
                    '</div>' +
                    '<div style="float: left">' +
                    '<p>安全问题:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="question" type="text"' +
                    'value="' + data.data['user_safe_question'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>安全答案:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="answer" type="text"' +
                    'value="' + data.data['user_safe_answer'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>操作系统:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="system" type="text"' +
                    'value="' + data.data['user_safe_system'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>浏览器:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="browser" type="text"' +
                    'value="' + data.data['user_safe_browser'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>权重:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="weight" type="text"' +
                    'value="' + data.data['user_safe_weight'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>权限:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="permission" type="text"' +
                    'value="' + data.data['user_safe_permission'] + '">' +
                    '</label>' +
                    '</p>' +
                    '</div>' +
                    '<div style="float: left;">' +
                    '<p>角色:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="role' + data.data['user_safe_role'] + '" type="text"' +
                    'value="' + data.data['user_safe_role'] + '">' +
                    '</label>' +
                    '</p>' +
                    '</div>' +
                    '</div>' +
                    '<div class="btn-container">' +
                    '<button class="btn btn-info" onclick="updateUser()">更新</button>' +
                    '<button class="btn btn-danger" onclick="deleteUser(this.value)" ' +
                    'value="' + data.data['user_common_id'] + '">删除' +
                    '</button>' +
                    '</div>' +
                    '</li>');
            }
        },
    })
}

//遍历输出用户表
function selectUserList(value) {
    let list = "userList";
    let userPageMax = pageNum(list);
    if (value === '-1') {
        if (userPageNum > 1) {
            userPageNum--;
        } else {
            warningFirstPage();
        }
    } else if (value === '+1') {
        if (userPageNum < userPageMax) {
            userPageNum++;
        } else {
            warningLastPage();
        }

    } else {
        userPageNum = 1;
    }
    console.log(userPageNum);

    $.ajax({
        url: "/superAdmin/showUserList",
        dataType: "JSON",
        data: {
            "pageNum": userPageNum
        },
        success: function (data) {
            // var jsonVal = data.result;
            // data1 = (new Function("", "return " + jsonVal))();
            //console.log(JSON.stringify(data));
            console.log(data);
            var html =
                '<div class="education wow fadeInRight animated" data-wow-delay="0.3s"' +
                'style="visibility: visible;-webkit-animation-delay: 0.3s; -moz-animation-delay: 0.3s; animation-delay: 0.3s;">' +
                '<ul class="timeline">' +
                '<li>' +
                '<i class="icon-graduation"></i>' +
                '<h2 class="timelin-title">用户信息</h2>' +
                '</li>';
            for (var i = 0; i < 10; i++) {
                try {
                    html += '<li>' +
                        '<div class="content-text" style="display: inline-block;width: 100%;align-content: center">' +
                        '<div style="float: left;">' +
                        '<p>公有ID:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'th:id="id' + data.data[i]['user_common_id'] + '" type="text"' +
                        'value="' + data.data[i]['user_common_id'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>私有ID:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="privateId" type="text"' +
                        'value="' + data.data[i]['user_common_private_id'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>OpenID:' +
                        '<label>' +
                        '<input style="background: transparent;border: none" id="openId" type="text"' +
                        'value="' + data.data[i]['user_common_open_id'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>用户名:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="username" type="text"' +
                        'value="' + data.data[i]['user_common_username'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>密码:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="password" type="text"' +
                        'value="' + data.data[i]['user_common_password'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>昵称:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="nickname" type="text"' +
                        'value="' + data.data[i]['user_common_nickname'] + '">' +
                        '</label>' +
                        '</p>' +
                        '</div>' +
                        '<div style="float: left;">' +
                        '<p>真实姓名:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="realname" type="text"' +
                        'value="' + data.data[i]['user_secret_real_name'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>电话:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="phone" type="text"' +
                        'value="' + data.data[i]['user_secret_phone'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>电子邮箱:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="email" type="text"' +
                        'value="' + data.data[i]['user_secret_email'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>生日:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="birthday" type="text"' +
                        'value="' + data.data[i]['user_secret_birthday'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>性别:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="sex" type="text"' +
                        'value="' + data.data[i]['user_secret_sex'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>年龄:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="age" type="text"' +
                        'value="' + data.data[i]['user_secret_age'] + '">' +
                        '</label>' +
                        '</p>' +
                        '</div>' +
                        '<div style="float: left">' +
                        '<p>微信:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="wechat" type="text"' +
                        'value="' + data.data[i]['user_secret_wechat'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>QQ:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="qq" type="text"' +
                        'value="' + data.data[i]['user_secret_qq'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>微博:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="weibo" type="text"' +
                        'value="' + data.data[i]['user_secret_weibo'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>地址:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="address" type="text"' +
                        'value="' + data.data[i]['user_secret_address'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>登录时间:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="logtime" type="text"' +
                        'value="' + data.data[i]['user_safe_logtime'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>IP地址:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="ip" type="text"' +
                        'value="' + data.data[i]['user_safe_ip'] + '">' +
                        '</label>' +
                        '</p>' +
                        '</div>' +
                        '<div style="float: left">' +
                        '<p>安全问题:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="question" type="text"' +
                        'value="' + data.data[i]['user_safe_question'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>安全答案:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="answer" type="text"' +
                        'value="' + data.data[i]['user_safe_answer'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>操作系统:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="system" type="text"' +
                        'value="' + data.data[i]['user_safe_system'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>浏览器:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="browser" type="text"' +
                        'value="' + data.data[i]['user_safe_browser'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>权重:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="weight" type="text"' +
                        'value="' + data.data[i]['user_safe_weight'] + '">' +
                        '</label>' +
                        '</p>' +
                        '<p>权限:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="permission" type="text"' +
                        'value="' + data.data[i]['user_safe_permission'] + '">' +
                        '</label>' +
                        '</p>' +
                        '</div>' +
                        '<div style="float: left;">' +
                        '<p>角色:' +
                        '<label>' +
                        '<input style="background: transparent;border: none"' +
                        'id="role' + data.data[i]['user_safe_role'] + '" type="text"' +
                        'value="' + data.data[i]['user_safe_role'] + '">' +
                        '</label>' +
                        '</p>' +
                        '</div>' +
                        '</div>' +
                        '<div class="btn-container">' +
                        '<button class="btn btn-info" onclick="updateUser()">更新</button>' +
                        '<button class="btn btn-danger" onclick="deleteUser(this.value)" ' +
                        'value="' + data.data[i]['user_common_id'] + '">删除' +
                        '</button>' +
                        '</div>' +
                        '</li>'
                } catch (e) {
                    //异常处理，如果遍历不够十次则中断遍历直接输出
                    break;
                }
            }
            html += '</ul>' +
                '</div>';
            $("#showData2").html(html);
        },
    })
}

//遍历输出文章表
function selectArticleList(value) {
    let list = "articleList";
    let articlePageMax = pageNum(list);
    if (value === '-1') {
        if (articlePageNum > 1) {
            articlePageNum--;
        } else {
            warningFirstPage();
        }
    } else if (value === '+1') {
        if (articlePageNum < articlePageMax) {
            articlePageNum++;
        } else {
            warningLastPage();
        }

    } else {
        articlePageNum = 1;
    }
    console.log(articlePageNum);

    $.ajax({
        url: "/superAdmin/showArticleList",
        dataType: "JSON",
        data: {
            "pageNum": articlePageNum
        },
        success: function (data) {
            // var jsonVal = data.result;
            // data1 = (new Function("", "return " + jsonVal))();
            console.log(data);
            var html =
                '<div class="education wow fadeInRight animated" data-wow-delay="0.3s"' +
                'style="visibility: visible;-webkit-animation-delay: 0.3s; -moz-animation-delay: 0.3s; animation-delay: 0.3s;">' +
                '<table cellpadding="0" cellspacing="0" width="100%"' +
                'style="border: #000000 solid 1px;text-align: center;table-layout: fixed;word-break:break-all;">' +
                '<tr class="border">' +
                '<td>序号</td>' +
                '<td>文章识别号</td>' +
                '<td>文章题目</td>' +
                '<td>作者</td>' +
                '<td>写作时间</td>' +
                '<td>点击数</td>' +
                '<td>IP地址</td>' +
                '<td>系统</td>' +
                '<td>浏览器</td>' +
                '</tr>';
            for (var i = 0; i < 10; i++) {
                try {
                    html += '<tr class="border">' +
                        '<td>' + data.data[i]["article_id"] + '</td>' +
                        '<td>' + data.data[i]["article_private_id"] + '</td>' +
                        '<td>' + data.data[i]["article_title"] + '</td>' +
                        '<td>' + data.data[i]["article_author"] + '</td>' +
                        '<td>' + data.data[i]["article_time"] + '</td>' +
                        '<td>' + data.data[i]["article_click_num"] + '</td>' +
                        '<td>' + data.data[i]["article_ip"] + '</td>' +
                        '<td>' + data.data[i]["article_system"] + '</td>' +
                        '<td>' + data.data[i]["article_browser"] + '</td>' +
                        '</tr>';
                } catch (e) {
                    //异常处理，如果遍历不够十次则中断遍历直接输出
                    break;
                }
            }
            html += '</table>' +
                '</div>';
            $("#showArticleData").html(html);
        },
    })
}

//遍历输出留言表
function selectMessageList(value) {
    let list = "messageList";
    let messagePageMax = pageNum(list);
    if (value === '-1') {
        if (messagePageNum > 1) {
            messagePageNum--;
        } else {
            warningFirstPage();
        }
    } else if (value === '+1') {
        if (messagePageNum < messagePageMax) {
            messagePageNum++;
        } else {
            warningLastPage();
        }

    } else {
        messagePageNum = 1;
    }
    console.log(messagePageNum);

    $.ajax({
        url: "/superAdmin/showMessageList",
        dataType: "JSON",
        data: {
            "pageNum": messagePageNum
        },
        success: function (data) {
            // var jsonVal = data.result;
            // data1 = (new Function("", "return " + jsonVal))();
            console.log(data);
            var html =
                '<div class="education wow fadeInRight animated" data-wow-delay="0.3s"' +
                'style="visibility: visible;-webkit-animation-delay: 0.3s; -moz-animation-delay: 0.3s; animation-delay: 0.3s;">' +
                '<table cellpadding="0" cellspacing="0" width="100%"' +
                'style="border: #000000 solid 1px;text-align: center;table-layout: fixed;word-break:break-all;">' +
                '<tr class="border">' +
                '<td>序号</td>' +
                '<td>留言识别号</td>' +
                '<td>用户名</td>' +
                '<td>留言内容</td>' +
                '<td>留言时间</td>' +
                '<td>IP地址</td>' +
                '<td>系统</td>' +
                '<td>浏览器</td>' +
                '<td>权重</td>' +
                '</tr>';
            for (var i = 0; i < 10; i++) {
                try {
                    html += '<tr class="border">' +
                        '<td>' + data.data[i]["message_id"] + '</td>' +
                        '<td>' + data.data[i]["message_private_id"] + '</td>' +
                        '<td>' + data.data[i]["message_username"] + '</td>' +
                        '<td>' + data.data[i]["message_main"] + '</td>' +
                        '<td>' + data.data[i]["message_time"] + '</td>' +
                        '<td>' + data.data[i]["message_ip"] + '</td>' +
                        '<td>' + data.data[i]["message_system"] + '</td>' +
                        '<td>' + data.data[i]["message_browser"] + '</td>' +
                        '<td>' + data.data[i]["message_weight"] + '</td>' +
                        '</tr>';
                } catch (e) {
                    //异常处理，如果遍历不够十次则中断遍历直接输出
                    break;
                }
            }
            html += '</table>' +
                '</div>';
            $("#showMessageData").html(html);
        },
    })
}

//每个表总页数以及当前页数
function pageNum(listName) {
    $.ajax({
        url: "/superAdmin/selectPageNumByPageName",
        contentType: "application/json",
        dataType: "JSON",
        data: {
            "pageName": listName
        },
        success: function (data) {
            selectPages = data.data;
            if (listName === "userList") {
                $("#userPageNow").html('<p id="userPageNow">' + userPageNum + '</p>');
                $("#userPageTotal").html('<p id="userPageTotal">' + selectPages + '</p>');
            } else if (listName === "articleList") {
                $("#articlePageNow").html('<p id="articlePageNow">' + articlePageNum + '</p>');
                $("#articlePageTotal").html('<p id="articlePageTotal">' + selectPages + '</p>');
            } else if (listName === "messageList") {
                $("#messagePageNow").html('<p id="messagePageNow">' + messagePageNum + '</p>');
                $("#messagePageTotal").html('<p id="messagePageTotal">' + selectPages + '</p>');
            }
        }
    });
    return selectPages;
}

//小于第一页警告
function warningFirstPage() {
    $("#message-box-text").html("已经是第一页了！")
    $("#message-box").css("color", "#a94442");
    $("#message-box").css("background", "#f2dede");
    setTimeout(function () {
        $('#message-box').slideUp(300);
    }, 1000);
    if ($("#message-box").is(":hidden")) {
        $('#message-box').slideDown(300);
    } else {
        $('#message-box').slideUp(300);
    }
}

//大于最后一页警告
function warningLastPage() {
    $("#message-box-text").html("已经是最后一页了！")
    $("#message-box").css("color", "#a94442");
    $("#message-box").css("background", "#f2dede");
    setTimeout(function () {
        $('#message-box').slideUp(300);
    }, 1000);
    if ($("#message-box").is(":hidden")) {
        $('#message-box').slideDown(300);
    } else {
        $('#message-box').slideUp(300);
    }
}

//删除用户
function deleteUser(value) {
    $.ajax({
        url: "/superAdmin/delete",
        dataType: "JSON",
        data: {
            "deleteId": value
        },
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.result === "1") {
                $("#message-box-text").html(data.msg);
                $("#message-box").css("color", "#d7f7ff");
                $("#message-box").css("background", "#1a95ff");
                selectUserList();
            } else {
                $("#message-box-text").html(data.msg);
                $("#message-box").css("color", "#a94442");
                $("#message-box").css("background", "#f2dede");
            }
            setTimeout(function () {
                $('#message-box').slideUp(300);
            }, 1000);
            if ($("#message-box").is(":hidden")) {
                $('#message-box').slideDown(300);
            } else {
                $('#message-box').slideUp(300);
            }
        },
    })
}

//更新用户
function updateUser() {
    alert(1);
    var id = $("#id").val();
    var privateId = $("#privateId").val();
    var openId = $("#openId").val();
    var username = $("#username").val().trim();
    var password = $("#password").val().trim();
    var nickname = $("#nickname").val();
    var realname = $("#realname").val();
    var phone = $("#phone").val();
    var email = $("#email").val();
    var birthday = $("#birthday").val();
    var sex = $("#sex").val();
    var age = $("#age").val();
    var wechat = $("#wechat").val();
    var qq = $("#qq").val();
    var weibo = $("#weibo").val();
    var address = $("#address").val();
    var logtime = $("#logtime").val();
    var ip = $("#ip").val();
    var question = $("#question").val();
    var answer = $("#answer").val();
    var system = $("#system").val();
    var browser = $("#browser").val();
    var weight = $("#weight").val();


    $.ajax({
        url: "/superAdmin/update",
        dataType: "JSON",
        data: {
            "id": id, "privateId": privateId, "openId": openId, "username": username, "password": password,
            "nickname": nickname, "realname": realname, "phone": phone, "email": email, "sex": sex,
            "birthday": birthday, "age": age, "wechat": wechat, "qq": qq, "weibo": weibo, "address": address,
            "logtime": logtime, "ip": ip, "question": question, "answer": answer, "system": system,
            "browser": browser, "weight": weight
        },
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.result === "1") {
                $("#message-box-text").html("更新成功");
                $("#message-box").css("color", "#d7f7ff");
                $("#message-box").css("background", "#1a95ff");
                selectUserList();
            } else {
                $("#message-box-text").html("更新失败");
                $("#message-box").css("color", "#a94442");
                $("#message-box").css("background", "#f2dede");
            }
            setTimeout(function () {
                $('#message-box').slideUp(300);
            }, 1000);
            if ($("#message-box").is(":hidden")) {
                $('#message-box').slideDown(300);
            } else {
                $('#message-box').slideUp(300);
            }

        },
    })
}