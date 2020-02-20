$(function () {
    if(!location.href.includes("resultDevice")){
        var ua = navigator.userAgent;
        if (ua.indexOf('iPhone') > 0 || ua.indexOf('Android') > 0 && ua.indexOf('Mobile') > 0) {

        } else if (ua.indexOf('iPad') > 0 || ua.indexOf('Android') > 0) {
            window.location.href = "resultDevice";
        } else {
            window.location.href = "resultDevice";
        }
    }
});