$(document).ready(function () {
  $("#btnFindAll").on("click", function () {
    $("#postItem > option:selected").val("postAll");
    $("#inputPostItemValue").val("");
    $("#searchForm").submit();
  })
  $("#postTbodyId > tr").on("click", function () {
    const url = window.location.origin + "/detail/" + $(this).find("td").eq(0).text();
    window.location.assign(url);
  })
})
