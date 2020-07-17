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
            } else if (data.code === "1") {
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
    $.ajax({
        url: "/superAdmin/showUserList",
        dataType: "JSON",
        data: {
            "pageNum": value
        },
        success: function (data) {
            console.log(data);
            if (value > data.page_total) {
                warningLastPage();
            } else if (value === 0) {
                warningFirstPage();
            } else {
                var html =
                    '<div class="education wow fadeInRight animated" data-wow-delay="0.3s"' +
                    'style="visibility: visible;-webkit-animation-delay: 0.3s; -moz-animation-delay: 0.3s; animation-delay: 0.3s;">' +
                    '<table cellpadding="0" cellspacing="0" style="width: 100%;table-layout: fixed;border: #000000 solid 1px;' +
                    'text-align: center;word-break:break-all;" ' +
                    '<tr class="border" style="text-align: center;">' +
                    '<td>序号</td>' +
                    '<td>个人识别号</td>' +
                    '<td>OpenId</td>' +
                    '<td>用户名</td>' +
                    '<td>密码</td>' +
                    '<td>昵称</td>' +
                    '<td>最后登录时间</td>' +
                    '<td>IP</td>' +
                    '<td>密保问题</td>' +
                    '<td>密保答案</td>' +
                    '<td>系统</td>' +
                    '<td>浏览器</td>' +
                    '<td>权重</td>' +
                    '<td>角色</td>' +
                    '<td>权限</td>' +
                    '<td>更多</td>' +
                    '</tr>';
                for (var i = 0; i < 10; i++) {
                    try {
                        html += '<tr class="border admin-table">' +
                            '<td title="' + data.data[i]["user_common_id"] + '">' + data.data[i]["user_common_id"] + '</td>' +
                            '<td title="' + data.data[i]["user_common_private_id"] + '">' + data.data[i]["user_common_private_id"] + '</td>' +
                            '<td title="' + data.data[i]["user_common_open_id"] + '">' + data.data[i]["user_common_open_id"] + '</td>' +
                            '<td title="' + data.data[i]["user_common_username"] + '">' + data.data[i]["user_common_username"] + '</td>' +
                            '<td title="' + data.data[i]["user_common_password"] + '">' + data.data[i]["user_common_password"] + '</td>' +
                            '<td title="' + data.data[i]["user_common_nickname"] + '">' + data.data[i]["user_common_nickname"] + '</td>' +
                            '<td title="' + data.data[i]["user_safe_logtime"] + '">' + data.data[i]["user_safe_logtime"] + '</td>' +
                            '<td title="' + data.data[i]["user_safe_ip"] + '">' + data.data[i]["user_safe_ip"] + '</td>' +
                            '<td title="' + data.data[i]["user_safe_question"] + '">' + data.data[i]["user_safe_question"] + '</td>' +
                            '<td title="' + data.data[i]["user_safe_answer"] + '">' + data.data[i]["user_safe_answer"] + '</td>' +
                            '<td title="' + data.data[i]["user_safe_system"] + '">' + data.data[i]["user_safe_system"] + '</td>' +
                            '<td title="' + data.data[i]["user_safe_browser"] + '">' + data.data[i]["user_safe_browser"] + '</td>' +
                            '<td title="' + data.data[i]["user_safe_weight"] + '">' + data.data[i]["user_safe_weight"] + '</td>' +
                            '<td title="' + data.data[i]["user_safe_role"] + '">' + data.data[i]["user_safe_role"] + '</td>' +
                            '<td title="' + data.data[i]["user_safe_permission"] + '">' + data.data[i]["user_safe_permission"] + '</td>' +
                            '<td><button class="btn btn-info" style="height: 100%;width: 100%;text-align: center;padding: 10%;"' +
                            ' id="more-info-click" onclick="moreUserInfoClick(this.value)" value="' + data.data[i]["user_common_id"] + '" ">' +
                            '详情</button></td>' +
                            '</tr>';
                    } catch (e) {
                        //异常处理，如果遍历不够十次则中断遍历直接输出
                        break;
                    }
                }
                html += '</table>' +
                    '</div>' +
                    '<div class="btn-container">' +
                    '<div class="btn-prev-or-next">' +
                    '<button class="btn btn-primary" id="userPrev"' +
                    'onclick="selectUserList(' + (data.page_num - 1) + ')">上一页</button>' +
                    '</div>' +
                    '<div class="btn-prev-or-next">' +
                    '<button class="btn btn-primary" id="userNext"' +
                    'onclick="selectUserList(' + (data.page_num + 1) + ')">下一页</button>' +
                    '</div>' +
                    '<div class="btn-prev-or-next">' +
                    '<p>当前页：</p>' +
                    '<p id="userPageNow">' + data.page_num + '</p>' +
                    '<p>/总页数：</p>' +
                    '<p id="userPageTotal">' + data.page_total + '</p>' +
                    '</div>' +
                    '</div>';
                $("#showUserData").html(html);
            }
        },
    })
}

//遍历输出用户表
function selectUserMobileList(value) {
    $.ajax({
        url: "/superAdmin/showUserList",
        dataType: "JSON",
        data: {
            "pageNum": value
        },
        success: function (data) {
            // var jsonVal = data.result;
            // data1 = (new Function("", "return " + jsonVal))();
            //console.log(JSON.stringify(data));
            console.log(data);
            if (value > data.page_total) {
                warningLastPage();
            } else if (value === 0) {
                warningFirstPage();
            } else {
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
                    '</div><div class="btn-container">\n' +
                    '<div class="btn-prev-or-next">\n' +
                    '<button class="btn btn-primary" id="userMobilePrev"\n' +
                    'onclick="selectUserMobileList(' + (data.page_num - 1) + ')" value="-1">上一页</button></div>\n' +
                    '<div class="btn-prev-or-next">\n' +
                    '<button class="btn btn-primary" id="userMobileNext"\n' +
                    'onclick="selectUserMobileList(' + (data.page_num + 1) + ')" value="+1">下一页</button></div>\n' +
                    '<div class="btn-prev-or-next">\n' +
                    '<p>当前页：</p>\n' +
                    '<p id="userPageMobileNow">' + data.page_num + '</p>\n' +
                    '<p>/总页数：</p>\n' +
                    '<p id="userPageMobileTotal">' + data.page_total + '</p>\n' +
                    '</div>\n' +
                    '</div>';
                $("#showData2").html(html);
            }
        },
    })
}

//遍历输出文章表
function selectArticleList(value) {
    $.ajax({
        url: "/superAdmin/showArticleList",
        dataType: "JSON",
        data: {
            "pageNum": value
        },
        success: function (data) {
            // var jsonVal = data.result;
            // data1 = (new Function("", "return " + jsonVal))();
            console.log(data);
            if (value > data.page_total) {
                warningLastPage();
            } else if (value === 0) {
                warningFirstPage();
            } else {
                var html =
                    '<div class="education wow fadeInRight animated" data-wow-delay="0.3s"' +
                    'style="visibility: visible;-webkit-animation-delay: 0.3s; -moz-animation-delay: 0.3s; animation-delay: 0.3s;">' +
                    '<table cellpadding="0" cellspacing="0" style="width: 100%;table-layout: fixed;border: #000000 solid 1px;' +
                    'text-align: center;word-break:break-all;" ' +
                    '<tr class="border" style="text-align: center;">' +
                    '<td>序号</td>' +
                    '<td>文章识别号</td>' +
                    '<td>文章题目</td>' +
                    '<td>作者</td>' +
                    '<td>写作时间</td>' +
                    '<td>点击数</td>' +
                    '<td>IP地址</td>' +
                    '<td>系统</td>' +
                    '<td>浏览器</td>' +
                    '<td>编辑</td>' +
                    '<td>更多</td>' +
                    '</tr>';
                for (var i = 0; i < 10; i++) {
                    try {
                        html += '<tr class="border admin-table">' +
                            '<td title="' + data.data[i]["article_id"] + '">' + data.data[i]["article_id"] + '</td>' +
                            '<td title="' + data.data[i]["article_private_id"] + '">' + data.data[i]["article_private_id"] + '</td>' +
                            '<td title="' + data.data[i]["article_title"] + '">' + data.data[i]["article_title"] + '</td>' +
                            '<td title="' + data.data[i]["article_author"] + '">' + data.data[i]["article_author"] + '</td>' +
                            '<td title="' + data.data[i]["article_time"] + '">' + data.data[i]["article_time"] + '</td>' +
                            '<td title="' + data.data[i]["article_click_num"] + '">' + data.data[i]["article_click_num"] + '</td>' +
                            '<td title="' + data.data[i]["article_ip"] + '">' + data.data[i]["article_ip"] + '</td>' +
                            '<td title="' + data.data[i]["article_system"] + '">' + data.data[i]["article_system"] + '</td>' +
                            '<td title="' + data.data[i]["article_browser"] + '">' + data.data[i]["article_browser"] + '</td>' +
                            '<td><a class="btn btn-danger" style="height: 100%;width: 100%;text-align: center;padding: 10%;"' +
                            'href="/superAdmin/editArticle/' + data.data[i]["article_private_id"] + '" target="_blank">' +
                            '编辑</a></td>' +
                            '<td><button class="btn btn-info" style="height: 100%;width: 100%;text-align: center;padding: 10%;"' +
                            ' id="more-info-click" onclick="moreArticleInfoClick(this.value)" value="' + data.data[i]["article_private_id"] + '" ">' +
                            '详情</button></td>' +
                            '</tr>';
                    } catch (e) {
                        //异常处理，如果遍历不够十次则中断遍历直接输出
                        break;
                    }
                }
                html += '</table>' +
                    '</div><div class="btn-container">' +
                    '<div class="btn-prev-or-next">' +
                    '<button class="btn btn-primary" id="articlePrev"' +
                    'onclick="selectArticleList(' + (data.page_num - 1) + ')" value="-1">上一页</button></div>' +
                    '<div class="btn-prev-or-next">' +
                    '<button class="btn btn-primary" id="articleNext"' +
                    'onclick="selectArticleList(' + (data.page_num + 1) + ')" value="+1">下一页</button></div>' +
                    '<div class="btn-prev-or-next">' +
                    '<p>当前页：</p>' +
                    '<p id="articlePageNow">' + data.page_num + '</p>' +
                    '<p>/总页数：</p>' +
                    '<p id="articlePageTotal">' + data.page_total + '</p>' +
                    '</div>' +
                    '</div>';
                $("#showArticleData").html(html);
            }
        },
    })
}

//遍历输出留言表
function selectMessageList(value) {
    $.ajax({
        url: "/superAdmin/showMessageList",
        dataType: "JSON",
        data: {
            "pageNum": value
        },
        success: function (data) {
            // var jsonVal = data.result;
            // data1 = (new Function("", "return " + jsonVal))();
            console.log(data);
            if (value > data.page_total) {
                warningLastPage();
            } else if (value === 0) {
                warningFirstPage();
            } else {
                var html =
                    '<div class="education wow fadeInRight animated" data-wow-delay="0.3s"' +
                    'style="visibility: visible;-webkit-animation-delay: 0.3s; -moz-animation-delay: 0.3s; animation-delay: 0.3s;">' +
                    '<table cellpadding="0" cellspacing="0" style="width: 100%;table-layout: fixed;border: #000000 solid 1px;' +
                    'text-align: center;word-break:break-all;" ' +
                    '<tr class="border" style="text-align: center;">' +
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
                        html += '<tr class="border admin-table">' +
                            '<td title="' + data.data[i]["message_id"] + '">' + data.data[i]["message_id"] + '</td>' +
                            '<td title="' + data.data[i]["message_private_id"] + '">' + data.data[i]["message_private_id"] + '</td>' +
                            '<td title="' + data.data[i]["message_username"] + '">' + data.data[i]["message_username"] + '</td>' +
                            '<td title="' + data.data[i]["message_main"] + '">' + data.data[i]["message_main"] + '</td>' +
                            '<td title="' + data.data[i]["message_time"] + '">' + data.data[i]["message_time"] + '</td>' +
                            '<td title="' + data.data[i]["message_ip"] + '">' + data.data[i]["message_ip"] + '</td>' +
                            '<td title="' + data.data[i]["message_system"] + '">' + data.data[i]["message_system"] + '</td>' +
                            '<td title="' + data.data[i]["message_browser"] + '">' + data.data[i]["message_browser"] + '</td>' +
                            '<td title="' + data.data[i]["message_weight"] + '">' + data.data[i]["message_weight"] + '</td>' +
                            '</tr>';
                    } catch (e) {
                        //异常处理，如果遍历不够十次则中断遍历直接输出
                        break;
                    }
                }
                html += '</table>' +
                    '</div><div class="btn-container">' +
                    '<div class="btn-prev-or-next">' +
                    '<button class="btn btn-primary" id="messagePrev"' +
                    'onclick="selectMessageList(' + (data.page_num - 1) + ')" value="-1">上一页</button></div>' +
                    '<div class="btn-prev-or-next">' +
                    '<button class="btn btn-primary" id="messageNext"' +
                    'onclick="selectMessageList(' + (data.page_num + 1) + ')" value="+1">下一页</button></div>' +
                    '<div class="btn-prev-or-next">' +
                    '<p>当前页：</p>' +
                    '<p id="messagePageNow">' + data.page_num + '</p>' +
                    '<p>/总页数：</p>' +
                    '<p id="messagePageTotal">' + data.page_total + '</p>' +
                    '</div>' +
                    '</div>';
                $("#showMessageData").html(html);
            }
        },
    })
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

function closeMoreInfoDialog() {
    $("#more-info-dialog").css("display", "none");
    $("#more-info-dialog-background").css("display", "none");
    $("#target").css("overflow", "auto");
}

function moreUserInfoClick(value) {
    $("#more-info-dialog").css("display", "block");
    $("#more-info-dialog-background").css("display", "block");
    $("#target").css("overflow", "hidden");
    var userId = value;
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
            } else if (data.code === "1") {
                $("#moreInfoData").html(
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
                    'value="' + data.data['user_common_id'] + '">删除</button>' +
                    '</div>');
            }
        },
    })
}

function moreArticleInfoClick(value) {
    $("#more-info-dialog").css("display", "block");
    $("#more-info-dialog-background").css("display", "block");
    $("#target").css("overflow", "hidden");
    var articlePrivateId = value;
    $.ajax({
        url: "/superAdmin/showArticle",
        dataType: "JSON",
        data: {
            "articlePrivateId": articlePrivateId
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
            } else if (data.code === "1") {
                $("#moreInfoData").html(
                    '<div class="content-text" style="display: inline-block;width: 100%;align-content: center">' +
                    '<div style="float: left;width: 33.3%;">' +
                    '<p>序号:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'th:id="articleId' + data.data['article_id'] + '" type="text"' +
                    'value="' + data.data['article_id'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>文章识别号:' +
                    '<label>' +
                    '<input style="background: transparent;border: none" id="articlePrivateId" type="text"' +
                    'value="' + data.data['article_private_id'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>文章题目:' +
                    '<label>' +
                    '<input style="background: transparent;border: none" id="articleTitle" type="text"' +
                    'value="' + data.data['article_title'] + '">' +
                    '</label>' +
                    '</p>' +
                    '</div>' +
                    '<div style="float: left;width: 33.3%;">' +
                    '<p>作者:' +
                    '<label>' +
                    '<input style="background: transparent;border: none" id="articleAuthor" type="text"' +
                    'value="' + data.data['article_author'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>写作时间:' +
                    '<label>' +
                    '<input style="background: transparent;border: none" id="articleTime" type="text"' +
                    'value="' + data.data['article_time'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>点击数:' +
                    '<label>' +
                    '<input style="background: transparent;border: none" id="articleClickNum" type="text"' +
                    'value="' + data.data['article_click_num'] + '">' +
                    '</label>' +
                    '</p>' +
                    '</div>' +
                    '<div style="float: left;width: 33.3%;">' +
                    '<p>IP地址:' +
                    '<label>' +
                    '<input style="background: transparent;border: none" id="articleIp" type="text"' +
                    'value="' + data.data['article_ip'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>系统:' +
                    '<label>' +
                    '<input style="background: transparent;border: none" id="articleSystem" type="text"' +
                    'value="' + data.data['article_system'] + '">' +
                    '</label>' +
                    '</p>' +
                    '<p>浏览器:' +
                    '<label>' +
                    '<input style="background: transparent;border: none"' +
                    'id="articleBrowser" type="text"' +
                    'value="' + data.data['article_browser'] + '">' +
                    '</label>' +
                    '</p>' +
                    '</div>' +
                    '<div class="btn-container">' +
                    '<button class="btn btn-info" onclick="updateArticle()">更新</button>' +
                    '<button class="btn btn-danger" onclick="deleteArticle(this.value)" ' +
                    'value="' + data.data['article_private_id'] + '">删除</button>' +
                    '</div>' +
                    '</div>');
            }
        },
    })
}

//删除用户
function deleteUser(value) {
    $.ajax({
        url: "/superAdmin/deleteUser",
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
        url: "/superAdmin/updateUser",
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

//删除文章
function deleteArticle(value) {
    $.ajax({
        url: "/superAdmin/deleteArticle",
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

//更新文章资料
function updateArticle() {
    alert(1);
    var id = $("#articleId").val();
    var privateId = $("#articlePrivateId").val();
    var title = $("#articleTitle").val();
    var author = $("#articleAuthor").val().trim();
    var time = $("#articleTime").val().trim();
    var click = $("#articleClickNum").val();
    var ip = $("#articleIp").val();
    var system = $("#articleSystem").val();
    var browser = $("#articleBrowser").val();
    $.ajax({
        url: "/superAdmin/updateArticle",
        dataType: "JSON",
        data: {
            "id": id, "privateId": privateId, "title": title, "author": author, "time": time,
            "click": click, "ip": ip, "system": system, "browser": browser
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

//更新文章
function editArticle(value) {
    var articlePrivateId = value;
    var articleTitle = $("#edit-article-title").val();
    var articleAuthor = $("#edit-article-author").val();
    var articleText = $("#edit-article-text").val();
    alert(articlePrivateId);
    alert(articleTitle);
    alert(articleAuthor);
    alert(articleText);
    $.ajax({
        url: "/superAdmin/editArticleByAjax",
        dataType: "JSON",
        data: {
            "articlePrivateId": articlePrivateId, "articleText": articleText, "articleAuthor": articleAuthor,
            "articleTitle": articleTitle
        },
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.code === "1") {
                //提交成功
                $("#message-box-text").html(data.msg)
                $("#message-box").css("color", "#d7f7ff");
                $("#message-box").css("background", "#1a95ff");
                setTimeout(function () {
                    window.close();
                }, 2000)
            } else {
                $("#message-box-text").html(data.msg)
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
        }
    })
}

