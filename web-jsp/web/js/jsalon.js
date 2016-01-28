$(document).ready(function(){

    /* JSalon navigation menu */
    var menuLinks = $('#main-menu').find('li a');
    menuLinks.each(function(){
       if ($(this).attr('href').localeCompare(window.location.pathname)==0) {
           $(this).parents('ul').show();
           $(this).addClass('active');
           return false;
       }
    });
    menuLinks.click(function(){
        if ($(this).next().is(":visible")) {
            $(this).next().slideUp();
        } else {
            $(this).next().slideDown();
        }
    });

    $('.datepicker').datepicker({
        showOtherMonths: true,
        selectOtherMonths: true,
        changeMonth: true,
        changeYear: true,
        dateFormat: $.datepicker.ISO_8601
    });
});

function getLastPathFromHref(href, excludeSep) {
    var idx = href.lastIndexOf("/");
    if (excludeSep) ++idx;
    return href.slice(idx - href.length);
}

function copyRegToLiveAddress(checked) {
    if (checked) {
        $('#LiveCountry').val($('#RegCountry').val());
        $('#LiveZip').val($('#RegZip').val());
        $('#LiveDistrict').val($('#RegDistrict').val());
        $('#LiveCity').val($('#RegCity').val());
        $('#LiveStreet').val($('#RegStreet').val());
        $('#LiveHouse').val($('#RegHouse').val());
        $('#LiveSection').val($('#RegSection').val());
        $('#LiveFlat').val($('#RegFlat').val());
    }
}

function getAjaxUrlCrud() {
    switch (getLastPathFromHref(window.location.pathname, true)) {
        case 'discounts': return "/jsalon/admin/discounts/ajax";
        case 'posts': return "/jsalon/admin/posts/ajax";
        case 'services': return "/jsalon/admin/services/ajax";
        case 'user': return "/jsalon/admin/user/ajax";
        case 'master': return "/jsalon/admin/master/ajax";
    }
}

function getDetailInfo(url, data) {
    if (url == "/jsalon/admin/masters/ajax" || url == "/jsalon/admin/master/ajax") {
        return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;" class="nested-table">' +
            '<tr>' + '<td>Паспорт:</td>' + '<td>' + data.passportInfo + '</td>' + '</tr>' +
            '<tr>' + '<td>Адрес регистрации:</td>' + '<td>' + data.regAddressInfo + '</td>' + '</tr>' +
            '<tr>' + '<td>Адрес проживания:</td>' + '<td>' + data.liveAddressInfo + '</td>' + '</tr>' +
            '<tr>' + '<td>Контакты:</td>' + '<td>' + iconReplacer(data.contactInfo) + '</td>' + '</tr>' +
            '</table>';
    }
}

function iconReplacer(str) {
    var res = str.replace(/phone /g, '<i class="fa fa-phone"></i> ');
    res = res.replace(/email /g, '<i class="fa fa-envelope-o"></i> ');
    res = res.replace(/vk /g, '<i class="fa fa-vk"></i> ');
    res = res.replace(/skype /g, '<i class="fa fa-skype"></i> ');
    res = res.replace(/facebook /g, '<i class="fa fa-facebook"></i> ');
    res = res.replace(/twitter /g, '<i class="fa fa-twitter"></i> ');
    return res;
}