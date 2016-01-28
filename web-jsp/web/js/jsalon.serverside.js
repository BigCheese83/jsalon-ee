$(document).ready(function() {
    var ajaxUrl = "";
    var colDef = [];
    switch (getLastPathFromHref(window.location.pathname, true)) {
        case 'admin':
        case 'users':
            ajaxUrl = "/jsalon/admin/users/ajax";
            colDef = [ { targets: [0,1,2,3,4], className: "dt-col-center" } ];
            break;
        case 'masters':
            ajaxUrl = "/jsalon/admin/masters/ajax";
            colDef = [
                { targets: [0], className: "details-control", orderable: false, searchable: false, data: null, defaultContent: "" },
                { targets: [1,2,3,4,5,6,7], className: "dt-col-center" },
                { targets: [1], data: "surname" },
                { targets: [2], data: "name" },
                { targets: [3], data: "patronymic" },
                { targets: [4], data: "birthDate" },
                { targets: [5], data: "hiringDate" },
                { targets: [6], data: "postInfo" },
                { targets: [7], data: "busy", render: function(data){
                        if (data === undefined || data === null) return '';
                        if (data === true) return '<i class="fa fa-lg fa-check-square-o"></i>';
                        else return '<i class="fa fa-lg fa-square-o"></i>';
                }} ];
            break;
    }
    var table = $('#datatable').DataTable({
        "processing": true,
        "autoWidth": false,
        "serverSide": true,
        "ajax": { "url": ajaxUrl, "type": "POST" },
        "language": { "url": "/jsalon/json/datatables_ru.json" },
        "columnDefs": colDef
    });
    table.on('click', 'td.details-control', function (){
        var tr = $(this).closest('tr');
        var row = table.row(tr);
        if (row.child.isShown()) {
            row.child.hide();
            tr.removeClass('shown');
        } else {
            row.child(getDetailInfo(ajaxUrl, row.data())).show();
            tr.addClass('shown');
        }
    });
});