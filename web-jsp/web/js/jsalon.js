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
});

function getLastPathFromHref(href, excludeSep) {
    var idx = href.lastIndexOf("/");
    if (excludeSep) ++idx;
    return href.slice(idx - href.length);
}