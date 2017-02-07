(function () {
    "use strict";

    // custom scrollbar

    $("html").niceScroll({
        styler: "fb",
        cursorcolor: "#65cea7",
        cursorwidth: '6',
        cursorborderradius: '0px',
        background: '#424f63',
        spacebarenabled: false,
        cursorborder: '0',
        zindex: '1000'
    });

    $(".left-side").niceScroll({
        styler: "fb",
        cursorcolor: "#65cea7",
        cursorwidth: '3',
        cursorborderradius: '0px',
        background: '#424f63',
        spacebarenabled: false,
        cursorborder: '0'
    });

    $(".left-side").getNiceScroll();
    if ($('body').hasClass('left-side-collapsed')) {
        $(".left-side").getNiceScroll().hide();
    }

    // Toggle Left Menu
    jQuery('.menu-list > a').click(function () {

        var parent = jQuery(this).parent();
        var sub = parent.find('> ul');

        if (!jQuery('body').hasClass('left-side-collapsed')) {
            if (sub.is(':visible')) {
                sub.slideUp(200, function () {
                    parent.removeClass('nav-active');
                    // jQuery('.main-content').css({height: ''});
                    // mainContentHeightAdjust();
                });
            } else {
                visibleSubMenuClose();
                parent.addClass('nav-active');
                sub.slideDown(200, function () {
                    // mainContentHeightAdjust();
                });
            }
        }
        return false;
    });

    function visibleSubMenuClose() {
        jQuery('.menu-list').each(function () {
            var t = jQuery(this);
            if (t.hasClass('nav-active')) {
                t.find('> ul').slideUp(200, function () {
                    t.removeClass('nav-active');
                });
            }
        });
    }

    // Active Sun Menu
    jQuery('.menu-list > .sub-menu-list > li > a').click(function () {
        //更换被点击的子菜单
        jQuery('.active').removeClass('active');
        var parent = jQuery(this).parent();
        parent.addClass('active');

        // return false;
    });

    /*function mainContentHeightAdjust() {
     return;
     // Adjust main content height
     var docHeight = jQuery(document).height();
     if(docHeight > jQuery('.main-content').height())
     jQuery('.main-content').height(docHeight);
     }*/

    //  class add mouse hover
    jQuery('.custom-nav > li').hover(function () {
        jQuery(this).addClass('nav-hover');
    }, function () {
        jQuery(this).removeClass('nav-hover');
    });


    //localStorage缓存的样式
    var ls = localStorage;
    var left_side_collapsed = ls.getItem("left-side-collapsed");

    if(left_side_collapsed){
        jQuery('body').addClass('left-side-collapsed');
        jQuery('.custom-nav ul').attr('style', '');

        jQuery(this).addClass('menu-collapsed');
    }else {
        jQuery('body').removeClass('left-side-collapsed chat-view');
        jQuery('.custom-nav li.active ul').css({display: 'block'});

        jQuery(this).removeClass('menu-collapsed');
    }



    // Menu Toggle
    jQuery('.toggle-btn').click(function () {
        $(".left-side").getNiceScroll().hide();

        if ($('body').hasClass('left-side-collapsed')) {
            $(".left-side").getNiceScroll().hide();
        }
        var body = jQuery('body');
        var bodyposition = body.css('position');

        if (bodyposition != 'relative') {
            //屏幕较大的情况

            if (!body.hasClass('left-side-collapsed')) {
                //把当前样式设置进localStorage
                ls.setItem('left-side-collapsed', 'on');
                console.log("left-side-collapsed:ON");

                body.addClass('left-side-collapsed');
                jQuery('.custom-nav ul').attr('style', '');

                jQuery(this).addClass('menu-collapsed');

            } else {
                //localStorage去除该
                ls.removeItem('left-side-collapsed');
                console.log("left-side-collapsed:OFF");

                body.removeClass('left-side-collapsed chat-view');
                jQuery('.custom-nav li.active ul').css({display: 'block'});

                jQuery(this).removeClass('menu-collapsed');

            }
        } else {

            if (body.hasClass('left-side-show'))
                body.removeClass('left-side-show');
            else
                body.addClass('left-side-show');

            // mainContentHeightAdjust();
        }

    });


    searchform_reposition();
    //iFrameHeight();

    jQuery(window).resize(function () {

        if (jQuery('body').css('position') == 'relative') {
            jQuery('body').removeClass('left-side-collapsed');
        } else {
            jQuery('body').css({left: '', marginRight: ''});
        }

        searchform_reposition();

        //iFrameHeight();
    });

    //iframe高度设置
    /*function iFrameHeight() {
     var ifm = document.getElementById("contentpage");
     var WINDOW_HEIGHT = document.documentElement.clientHeight;
     console.log("WINDOW_HEIGHT:"+WINDOW_HEIGHT);
     if (ifm != null) {
     ifm.height = WINDOW_HEIGHT - 50;
     }
     }*/

    function searchform_reposition() {
        if (jQuery('.searchform').css('position') == 'relative') {
            jQuery('.searchform').insertBefore('.left-side-inner .logged-user');
        } else {
            jQuery('.searchform').insertBefore('.menu-right');
        }
    }

    // panel collapsible 面板打开和折叠
    $('.panel .tools .fa').click(function () {
        var el = $(this).parents(".panel").children(".panel-body");
        if ($(this).hasClass("fa-chevron-down")) {
            $(this).removeClass("fa-chevron-down").addClass("fa-chevron-up");
            el.slideUp(200);
        } else {
            $(this).removeClass("fa-chevron-up").addClass("fa-chevron-down");
            el.slideDown(200);
        }
    });

    // panel close 关闭面板
    $('.panel .tools .fa-times').click(function () {
        $(this).parents(".panel").parent().remove();
    });

    //在数据面板页面,暂时没用上
    $('.todo-check label').click(function () {
        $(this).parents('li').children('.todo-title').toggleClass('line-through');
    });

    $(document).on('click', '.todo-remove', function () {
        $(this).closest("li").remove();
        return false;
    });


})(jQuery);