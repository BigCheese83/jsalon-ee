$(function(){
    /* JSalon navigation menu */
    var $menuLinks = $('#main-menu').find('li a');
    $menuLinks.each(function(){
       if ($(this).attr('href').localeCompare(window.location.pathname)==0) {
           $(this).parents('ul').show();
           $(this).addClass('active');
           return false;
       }
    });
    $menuLinks.on('click', function(){
        if ($(this).next().is(':visible')) {
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
        $('#liveAddressCountry').val($('#regAddressCountry').val());
        $('#liveAddressZip').val($('#regAddressZip').val());
        $('#liveAddressDistrict').val($('#regAddressDistrict').val());
        $('#liveAddressCity').val($('#regAddressCity').val());
        $('#liveAddressStreet').val($('#regAddressStreet').val());
        $('#liveAddressHouse').val($('#regAddressHouse').val());
        $('#liveAddressSection').val($('#regAddressSection').val());
        $('#liveAddressFlat').val($('#regAddressFlat').val());
    }
}

function getAjaxUrlCrud(page) {
    var currPage = page || getLastPathFromHref(window.location.pathname, true);
    switch (currPage) {
        case 'discounts': return "/jsalon/admin/discounts/ajax";
        case 'posts': return "/jsalon/admin/posts/ajax";
        case 'services': return "/jsalon/admin/services/ajax";
        case 'user': return "/jsalon/admin/user/ajax";
        case 'master': return "/jsalon/admin/master/ajax";
    }
}

function getDetailInfo(url, data) {
    //get person detail info
    if (strStartsWith(url, "/jsalon/admin/master")) {
        return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;" class="nested-table">' +
            '<tr>'+'<td>Паспорт:</td>'+'<td>'+ getPassportInfo(data.passport) +'</td>'+'</tr>' +
            '<tr>'+'<td>Адрес регистрации:</td>'+'<td>'+ getAddressInfo(data.regAddress) +'</td>'+'</tr>' +
            '<tr>'+'<td>Адрес проживания:</td>'+'<td>'+ getAddressInfo(data.liveAddress) +'</td>'+'</tr>' +
            '<tr>'+'<td>Контакты:</td>'+'<td>'+ getContactInfo(data.contact) +'</td>'+'</tr>' +
            '</table>';
    }
}

function getPassportInfo(data) {
    return defaultStr(data.series,' ') + defaultStr(data.number) + ' выдан ' +
        defaultStr(data.issueDate,' ') + defaultStr(data.issuedBy);
}
function getAddressInfo(data) {
    return defaultStr(data.country,' ') + defaultStr(data.zip,' ') + defaultStr(data.district,' ') +
        defaultStr(data.city,' ','г.') + defaultStr(data.street,' ','ул.') +
        defaultStr(data.house,null,'д.') + defaultStr(data.section,' ') + ' ' + defaultStr(data.flat,null,'кв.');
}
function getContactInfo(data) {
    return defaultStr(data.phone,' ','<i class="fa fa-phone"></i> ') +
        defaultStr(data.email,' ','<i class="fa fa-envelope-o"></i> ') +
        defaultStr(data.vk,' ','<i class="fa fa-vk"></i> ') +
        defaultStr(data.skype,' ','<i class="fa fa-skype"></i> ') +
        defaultStr(data.facebook,' ','<i class="fa fa-facebook"></i> ') +
        defaultStr(data.twitter,' ','<i class="fa fa-twitter"></i> ') +
        defaultStr(data.icq,null,'icq ');
}

function renderCheckbox(data) {
    if (data === undefined || data == null) return '';
    if (data === true) {
        return '<i class="fa fa-lg fa-check-square-o"></i>';
    } else {
        return '<i class="fa fa-lg fa-square-o"></i>';
    }
}

function strStartsWith(str, prefix) {
    return str.slice(0, prefix.length) == prefix;
}

function defaultStr(str, suffix, prefix, def) {
    if (str) {
        return (prefix ? prefix : '') + str + (suffix ? suffix : '');
    } else {
        return def ? def : '';
    }
}

function getPropertyOfObject(obj, name) {
    if (!name) return null;
    var prop = null, prev = obj;
    name.split('.').forEach(function(token, i){
        prop = prev[token];
        prev = prop;
    });
    return prop;
}