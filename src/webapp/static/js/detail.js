$(document).ready(function (){
  $("#getListButton").on("click", function (){
    const url = window.location.origin + "/search";
    window.location.assign(url);
  })
  $("#deleteButton").on("click", function (){
    if(window.confirm("삭제하시겠습니까?")) {
      alert("삭제되었습니다.");
      const url = window.location.origin + "/detail/delete/" + $(this).val();
      window.location.assign(url);
    }
  })
})
