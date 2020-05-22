(function($) {
  "use strict";
  $(window).on('load', function() {
    /* 
   MixitUp
   ========================================================================== */
  $('#portfolio').mixItUp();
  /* 
   One Page Navigation & wow js
   ========================================================================== */
    var OnePNav = $('.onepage-nev');
    var top_offset = OnePNav.height() - -0;
    OnePNav.onePageNav({
      currentClass: 'active',
      scrollOffset: top_offset,
    });
  /*Page Loader active
    ========================================================*/
    $('#preloader').fadeOut();
  // Sticky Nav
    $(window).on('scroll', function() {
        if ($(window).scrollTop() > 200) {
            $('.scrolling-navbar').addClass('top-nav-collapse');
        } else {
            $('.scrolling-navbar').removeClass('top-nav-collapse');
        }
    });
    /* slicknav mobile menu active  */
    $('.mobile-menu').slicknav({
        prependTo: '.navbar-header',
        parentTag: 'liner',
        allowParentLinks: true,
        duplicate: true,
        label: '',
        closedSymbol: '<i class="icon-arrow-right"></i>',
        openedSymbol: '<i class="icon-arrow-down"></i>',
      });
      /* WOW Scroll Spy
    ========================================================*/
     var wow = new WOW({
      //disabled for mobile
        mobile: false
    });
    wow.init();
    /* Nivo Lightbox 
    ========================================================*/
    $('.lightbox').nivoLightbox({
        effect: 'fadeScale',
        keyboardNav: true,
      });
    /* Counter
    ========================================================*/
    $('.counterUp').counterUp({
     delay: 10,
     time: 1000
    });
    /* Back Top Link active
    ========================================================*/
      var offset = 200;
      var duration = 500;
      $(window).scroll(function() {
        if ($(this).scrollTop() > offset) {
          $('.back-to-top').fadeIn(400);
        } else {
          $('.back-to-top').fadeOut(400);
        }
      });

      $('.back-to-top').on('click',function(event) {
        event.preventDefault();
        $('html, body').animate({
          scrollTop: 0
        }, 600);
        return false;
      });
  });
}(jQuery));

/*common*/
function back() {
    window.location.href = "/user/login";
}

/*login.html*/
function login() {
    var username = $("#username").val().trim();
    var password = $("#password").val().trim();

    $.ajax({
        url: "/loginUserByAjax",
        dataType: "JSON",
        data: {
            "username": username, "password": password
        },
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.result === "1") {
                $("#message-box-text").html(data.msg);
                $("#message-box").css("color", "#ffffff");
                $("#message-box").css("background", "#1a95ff");
                setTimeout(function () {
                    window.location.href = "/";
                }, 2000)
            } else if (data.result === "0") {
                //登录失败，用户名或密码错误
                $("#message-box-text").html(data.msg);
                $("#message-box").css("color", "#a94442");
                $("#message-box").css("background", "#f2dede");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert(errorThrown)
        }
    })
}

/*registered.html*/
function registered() {
    var username = $("#username").val().trim();
    var password = $("#password").val().trim();
    var nickname = $("#nickname").val();
    var phone = $("#phone").val();
    var email = $("#email").val();
    var sex = $("#sex").val();
    var birthday = $("#birthday").val();
    var wechat = $("#wechat").val();
    var qq = $("#qq").val();
    var weibo = $("#weibo").val();
    var question = $("#question").val();
    var answer = $("#answer").val();

    $.ajax({
        url: "/registeredUserByAjax",
        dataType: "JSON",
        data: {
            "username": username, "password": password, "nickname": nickname, "phone": phone,
            "email": email, "sex": sex, "birthday": birthday, "wechat": wechat, "qq": qq,
            "weibo": weibo, "question": question, "answer": answer
        },
        /*data:$("#form1").serialize(),*/  //序列化表单
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.result === "1") {
                //提交成功
                $("#message-box-text").html(data.msg)
                $("#message-box").css("color", "#d7f7ff");
                $("#message-box").css("background", "#1a95ff");
                setTimeout(function () {
                    window.location.href = "/user/login";
                }, 2000)
            } else if (data.result === "0") {
                //提交失败，用户名存在
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
        },
    })
}

/*message.html*/
function addMessage() {
    var messageText = $("#message-text").val();

    alert(messageText);
    $.ajax({
        url: "/message/addMessageByAjax",
        dataType: "JSON",
        data: {
            "messageText": messageText
        },
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.result === "1") {
                alert("存取成功");
                window.location.reload();
            } else if (data.result === "error") {
                alert("异常错误");
            } else if (data.result === "0") {
                alert("未登录");
            }
        }
    })
}