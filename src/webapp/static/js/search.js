const btnFindAll = document.getElementById("btnFindAll");
btnFindAll.addEventListener("click", function (){
  const postItem = document.getElementById("postItem");
  const inputPostItemValue = document.getElementById("inputPostItemValue");
  postItem.options[postItem.selectedIndex].value = "postAll";
  inputPostItemValue.value = "";
  document.forms["searchForm"].submit();
})
$(document).ready(function () {
  $("#postTbodyId > tr").on("click", function () {
    const url = window.location.origin + "/detail/" + $(this).find("td").eq(0).text();
    window.location.assign(url);
  })
})
