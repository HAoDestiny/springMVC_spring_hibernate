//jq方法返回url中的参数
(function($){
    $.getUrlParam = function(name)
    {
        var reg = new RegExp("(^|&;)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r!=null) return unescape(r[2]); return null;
    }
})(jQuery);


/***
 * 通过url获取参数
 */
// $("#msg_test_url").html("这是通过url传递得到的数据：" + $.getUrlParam("message"));