<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="<c:url value="/js/jquery.dataTables.js"/>"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#datatable').DataTable({
        "processing": true,
        "autoWidth": false,
        "serverSide": true,
        "ajax": { "url": "/jsalon/admin/users/ajax", "type": "POST" },
        "language": { "url": "/jsalon/json/datatables_ru.json" },
        "columnDefs": [
            { targets: [0,1,2,3,4], className: "dt-col-center" }
        ]
    });
});
</script>