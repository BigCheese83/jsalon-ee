$(document).ready(function() {

    var searchMessage = $('#searchMessage');
    var contentTable = $('.content-table');
    hideElements();

    $('#searchButton').button().click(function(){
        var postParams = "";
        var isAllEmpty = true;
        hideElements();
        searchMessage.empty();
        $('#search').find('input[type="text"]').each(function(i, elem){
            postParams = postParams + $(elem).serialize() + "&";
            if($(elem).val() != "") {
                isAllEmpty = false;
            }
        });
        if (isAllEmpty) {
            alert('Введите минимум один параметр для поиска!');
            return false;
        }
        postParams = postParams + "searchRequest=true";
        $.ajax({
            url: "/jsalon/admin/user/ajax",
            type: "POST",
            data: postParams,
            success: function(data){
                if (data.hasOwnProperty('err')) {
                    data.err.forEach(function(item){
                        searchMessage.append(item + '<br/>');
                    });
                }
                if (data.length > 0) {
                    showElements();
                    var table = $.fn.dataTable.tables({ visible: true, api: true });
                    table.clear();
                    data.forEach(function(item){
                        var rowData = [item.id, item.login, item.lastName, item.firstName, item.middleName, item.role];
                        table.row.add(rowData).draw(false);
                    });
                } else {
                    searchMessage.append('Ничего не найдено :(' + '<br/>');
                }
            },
            error: function(jqXHR, status, thrown){
                searchMessage.html('' + status + ': ' + thrown);
            }
        });
    });

    function hideElements() {
        contentTable.hide();
        $('#editRadio').button("option", "disabled", true);
        $('#delRadio').button("option", "disabled", true);
    }
    function showElements() {
        contentTable.show();
        $('#editRadio').button("option", "disabled", false);
        $('#delRadio').button("option", "disabled", false);
    }
});
